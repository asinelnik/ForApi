package tests;

import io.restassured.response.Response;
import models.rest.AuthorizationModel;
import org.awaitility.Awaitility;
import org.json.JSONObject;
import org.testng.annotations.Test;
import services.BaseStep;
import services.RandomStringGenerator;
import steps.ApiSteps;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetCustomerWithIdTest extends BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    @Test(testName = "Получение информации о владельце", description = "Получение информации о владельце", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void getCustomerInfo(String login, String pass) {
        AuthorizationModel authorizationModel = new AuthorizationModel(login, pass);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        String id = createNewCustomer(getEmptyPhoneWhile(token), token, randomStringGenerator.generateRandomString(5),
                randomStringGenerator.generateRandomString(10)).getBody().jsonPath().getString("id");
        Awaitility.await().atMost(2, TimeUnit.MINUTES).with().pollInterval(15, TimeUnit.SECONDS)
                .until(() -> apiSteps.getCustomerById(id, token).jsonPath().getString("return.status").equals("ACTIVATE"));
        Response res = apiSteps.getCustomerById(id, token);
        JSONObject jsonObject = new JSONObject(res.jsonPath().getString("return.pd"));
        assertThat(jsonObject.getString("passportNumber").length()).as("Длина номера паспорта не соответствыет ожидаемому").isEqualTo(6);
        assertThat(jsonObject.getString("passportSeries").length()).as("Длина серии паспорта не соответствыет ожидаемому").isEqualTo(4);
        assertThat(res.jsonPath().getString("return.status")).as("Статус н соответствует ожидаемому").isEqualTo("ACTIVATE");
    }
}

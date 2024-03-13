package tests;

import io.restassured.response.Response;
import models.rest.AuthorizationModel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import services.BaseStep;
import services.RandomStringGenerator;
import steps.ApiSteps;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.awaitility.Awaitility.await;

public class GetCustomerWithIdTest extends BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    @Test(testName = "Получение информации о владельце", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void getCustomerInfo(String login, String pass) {
        AuthorizationModel authorizationModel = new AuthorizationModel(login, pass);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        String id = createNewCustomer(getEmptyPhoneWhile(token), token, randomStringGenerator.generateRandomString(5),
                randomStringGenerator.generateRandomString(10)).getBody().jsonPath().getString("id");
        await().atMost(3, TimeUnit.MINUTES).with().pollInterval(20, TimeUnit.SECONDS).until(() -> {
            Response res = apiSteps.getCustomerById(id, token);
            if (res.jsonPath().getString("return.status").equals("ACTIVE")) {
                return true;
            }
            return false;
        });
        JSONObject jsonObject = new JSONObject(apiSteps.getCustomerById(id, token).jsonPath().getString("return.pd"));
        assertThat(jsonObject.getString("passportNumber").length()).as("Длина номера паспорта не соответствыет ожидаемому").isEqualTo(6);
        assertThat(jsonObject.getString("passportSeries").length()).as("Длина серии паспорта не соответствыет ожидаемому").isEqualTo(4);
    }
}

package tests;

import io.restassured.response.Response;
import models.rest.AuthorizationModel;
import org.testng.annotations.Test;
import services.BaseStep;
import services.RandomStringGenerator;
import steps.ApiSteps;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateNewCustomerTest extends BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    @Test(description = "Создание нового владельца номера", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void createCustomer(String login, String pass) {
        AuthorizationModel authorizationModel = new AuthorizationModel(login, pass);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        List<Long> num = getEmptyPhoneWhile(token);
        Response res = createNewCustomer(num, token, randomStringGenerator.generateRandomString(5), randomStringGenerator.generateRandomString(10));
        assertThat(res.getStatusCode()).as("Статус код ответа от сервера не соответствует ожидаемому").isEqualTo(200);
        assertThat(res.jsonPath().getString("id")).as("").isNotEmpty();
    }
}

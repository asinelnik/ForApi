package tests;

import models.rest.AuthorizationModel;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.ApiSteps;
import steps.PostCustomerStep;

import java.util.List;

public class CreateNewCustomerTest extends BaseStep {
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    ApiSteps apiSteps = new ApiSteps();

    @Test(description = "Создание нового владельца номера", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void createCustomer(String login, String pass) {
        AuthorizationModel authorizationModel = new AuthorizationModel(login, pass);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        List<Long> num = getEmptyPhoneWhile(token);
        postCustomerStep.createCustomer(num, token);
    }
}

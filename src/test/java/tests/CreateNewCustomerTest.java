package tests;

import models.rest.AuthorizationModel;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.ApiSteps;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;

import java.util.List;

public class CreateNewCustomerTest {
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    AuthorizationModel authorizationModel = new AuthorizationModel();
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    ApiSteps apiSteps = new ApiSteps();

    @Test(description = "Создание нового владельца номера", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void createCustomer(String login, String pass) {
        authorizationModel.setPassword(pass);
        authorizationModel.setLogin(login);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        List<Long> num = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        postCustomerStep.createCustomer(num, token);
    }
}

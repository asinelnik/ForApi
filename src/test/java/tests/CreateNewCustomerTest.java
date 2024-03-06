package tests;

import org.testng.annotations.Test;
import services.BaseStep;
import steps.ApiSteps;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;

import java.util.List;

public class CreateNewCustomerTest {
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    ApiSteps apiSteps = new ApiSteps();

    @Test (description = "Создание нового владельца номера", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void createCustomer(String login, String pass) {
        String token = apiSteps.getToken(login, pass).jsonPath().getString("token");
        List<Long> num = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        postCustomerStep.createCustomer(num,token);
    }
}

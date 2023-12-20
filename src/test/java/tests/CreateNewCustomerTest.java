package tests;

import org.testng.annotations.Test;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;

import java.util.List;

public class CreateNewCustomerTest {
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();

    @Test //(description = "Создание нового владельца номера", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void createCustomer(String token) {
        List<Long> num = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        postCustomerStep.createCustomer(num,token);
    }
}

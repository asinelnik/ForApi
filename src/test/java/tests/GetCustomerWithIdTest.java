package tests;

import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetCustomerStep;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;

public class GetCustomerWithIdTest extends BaseStep {
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    GetCustomerStep getCustomerStep = new GetCustomerStep();

    @Test(description = "Получение информации о владельце")
    public void getCustomerInfo(String token) throws InterruptedException {
        forSpecification();
        String passport = getCustomerStep.getIdCustomer(postCustomerStep.createNewCustomer(getEmptyPhoneStep.getEmptyPhoneWhile(token), token), token);
    }
}

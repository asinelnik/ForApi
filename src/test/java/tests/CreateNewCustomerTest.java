package tests;

import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetEmptyPhone;
import steps.PostCustomer;

import java.util.List;

public class CreateNewCustomerTest {
    PostCustomer postCustomer = new PostCustomer();
    BaseStep baseStep = new BaseStep();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test
    public void createCustomer() {

        List<Long> num = getEmptyPhone.getEmptyPhoneWhile();
        postCustomer.createCustomer(num);
    }
}

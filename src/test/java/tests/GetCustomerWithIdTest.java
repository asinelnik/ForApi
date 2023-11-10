package tests;

import org.testng.annotations.Test;
import steps.GetCustomer;
import steps.GetEmptyPhone;

public class GetCustomerWithIdTest extends GetCustomer {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test
    public void getCustomerInfo() throws InterruptedException {
        String customerId = getIdFromNewCustomer(getEmptyPhone.getEmptyPhoneWhile());
        Thread.sleep(120000);
        getIdCustomer(customerId);
    }

}

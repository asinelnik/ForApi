package tests;

import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetCustomerStep;
import steps.PostCustomerStep;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetCustomerWithIdTest extends BaseStep {
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetCustomerStep getCustomerStep = new GetCustomerStep();

    @Test(description = "Получение информации о владельце")
    public void getCustomerInfo() {
        String token = getTokenAdmin();
        Object passport = getCustomerStep.getIdCustomer(postCustomerStep.createNewCustomer(getEmptyPhoneWhile(token), token), token);
        System.out.println(passport);
        //assertThat(passport.getString("passportNumber").length()).as("Длина номера паспорта не соответствыет ожидаемому").isEqualTo(6);
        //assertThat(passport.getString("passportSeries").length()).as("Длина серии паспорта не соответствыет ожидаемому").isEqualTo(4);
    }
}

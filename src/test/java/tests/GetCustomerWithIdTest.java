package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetCustomer;
import steps.GetEmptyPhone;

public class GetCustomerWithIdTest extends GetCustomer {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test(description = "Получение информации о владельце", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void getCustomerInfo(String Login, String Password) throws InterruptedException {
        RestAssured.filters(new AllureRestAssured());
        String customerId = getIdFromNewCustomer(getEmptyPhone.getEmptyPhoneWhile(Login, Password));
        Thread.sleep(120000);
        getIdCustomer(customerId);
    }
}

package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetEmptyPhone;
import steps.PostCustomer;

import java.util.List;

public class CreateNewCustomerTest {
    PostCustomer postCustomer = new PostCustomer();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test(description = "Создание нового владельца номера", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)

    public void createCustomer(String Login, String Password)  {
        List<Long> num = getEmptyPhone.getEmptyPhoneWhile(Login, Password);
        postCustomer.createCustomer(num);
    }
}

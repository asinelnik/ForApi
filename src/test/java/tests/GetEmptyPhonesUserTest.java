package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetEmptyPhone;

import java.util.List;

public class GetEmptyPhonesUserTest {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test(description = "Получение списка свободных номеров", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)

    public void getEmptyPhoneNumberUserAwait(String Login, String Password) {
        RestAssured.filters(new AllureRestAssured());
        List<Long> telnum = getEmptyPhone.getEmptyPhoneWhile(Login, Password);
        System.out.println(telnum);
        Assertions.assertThat(telnum.size() > 0);
    }
}
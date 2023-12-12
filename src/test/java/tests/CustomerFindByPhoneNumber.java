package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.SoapFindByPhone;

import javax.xml.bind.JAXBException;

public class CustomerFindByPhoneNumber {
    SoapFindByPhone soapFindByPhone = new SoapFindByPhone();

    @Test(description = "Проверка регистрации владельца в SOAP", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)

    public void customerFindByPhoneNumber(String Login, String Password) throws JAXBException {
        RestAssured.filters(new AllureRestAssured());
        soapFindByPhone.soapFindByPhone(Login, Password);
        System.out.println();
    }
}

package tests;

import org.testng.annotations.Test;
import services.BaseStep;
import steps.SoapFindByPhone;

import javax.xml.bind.JAXBException;

public class CustomerFindByPhoneNumber {
    SoapFindByPhone soapFindByPhone = new SoapFindByPhone();

   /* @Test//(description = "Проверка регистрации владельца в SOAP", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void customerFindByPhoneNumber(String token) throws JAXBException {
        soapFindByPhone.soapFindByPhone(token);
    }*/
}

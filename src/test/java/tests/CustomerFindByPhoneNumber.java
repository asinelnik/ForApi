package tests;

import org.testng.annotations.Test;
import steps.SoapFindByPhone;

import javax.xml.bind.JAXBException;

@Test
public class CustomerFindByPhoneNumber {
    SoapFindByPhone soapFindByPhone = new SoapFindByPhone();
    public void customerFindByPhoneNumber() throws JAXBException {
        soapFindByPhone.soapFindByPhone();
        System.out.println();
    }
}

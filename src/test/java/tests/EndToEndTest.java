package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetCustomerStep;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;
import steps.SoapFindByPhone;

import javax.xml.bind.JAXBException;
import java.util.List;

@Test
public class EndToEndTest {
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetCustomerStep getCustomerStep = new GetCustomerStep();
    BaseStep baseStep = new BaseStep();
    SoapFindByPhone soapFindByPhone = new SoapFindByPhone();


    public void endToEnd() throws JAXBException {
        baseStep.forSpecification();

        SoftAssertions softAssertions = new SoftAssertions();

        String token = "9b28f94b619f46ccb8a7d8de6768f12a";

        List<Long> numbers = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        String id = postCustomerStep.createNewCustomer(numbers, token);
        Response res = getCustomerStep.getCustomerInfo(id, token);
        String passport = res.jsonPath().get("return.pd");
        Long phoneNumber = (res.jsonPath().get("return.phone"));
        softAssertions.assertThat(JsonPath.from(passport).getString("passportSeries").length())
                .as("Некорректное количество цифр в серии паспорта").isEqualTo(4);
        softAssertions.assertThat(JsonPath.from(passport).getString("passportNumber").length())
                .as("Некорректное количество цифр в номере паспорта").isEqualTo(6);
        softAssertions.assertThat(soapFindByPhone.soapFindByPhone(token, phoneNumber)).as("Id владельца soap не соответствует id владельца rest").isEqualTo(id);
        softAssertions.assertAll();
    }
}

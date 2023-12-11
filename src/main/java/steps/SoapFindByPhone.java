package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.soap.EnvelopeResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseStep;
import services.JaxbWorker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static io.restassured.RestAssured.given;

public class SoapFindByPhone {
    BaseStep baseStep = new BaseStep();
    JaxbWorker jaxbWorker = new JaxbWorker();
    GetNumberPhoneAfterCreate getNumberPhoneAfterCreate = new GetNumberPhoneAfterCreate();

    @Step
    public void soapFindByPhone(String Login, String Password) throws JAXBException {
        baseStep.forSpecification();
        Response registrationCustomer = getNumberPhoneAfterCreate.phoneNumber(Login, Password);
        Long phone = registrationCustomer.getBody().jsonPath().getLong("return.phone");
        String restCustomerId = registrationCustomer.getBody().jsonPath().getString("return.customerId");
        String token = baseStep.getTokenUser();
        String body = jaxbWorker.soapRequestBody(token, phone);
        System.out.println(body);
        Response response = given()
                .when()
                .contentType("application/xml")
                .body(body)
                .post("/customer/findByPhoneNumber")
                .then()
                .log().all()
                .extract().response();
        String xmlBody = response.body().asString().toString();
        JAXBContext context = JAXBContext.newInstance(EnvelopeResponse.class);
        StringReader reader = new StringReader(xmlBody);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        EnvelopeResponse unmarshelledEnvelope = (EnvelopeResponse) unmarshaller.unmarshal(reader);
        String soapCustomerId = unmarshelledEnvelope.getResponseBodyXml().getCustomerId();
        Assertions.assertThat(soapCustomerId).as("Customer id пустой").isNotEmpty();
        Assertions.assertThat(soapCustomerId).as("Id в старом сервисе не совпадает с Id нового сервиса").isEqualTo(restCustomerId);
    }
}



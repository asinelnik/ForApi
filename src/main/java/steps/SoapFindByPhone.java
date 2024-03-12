package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.soap.EnvelopeResponse;
import org.assertj.core.api.Assertions;
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
    ApiSteps apiSteps = new ApiSteps();
    GetNumberPhoneAfterCreate getNumberPhoneAfterCreate = new GetNumberPhoneAfterCreate();

   /*@Step("Проверка регистрации владельца в сервисе Soap")
    public String soapFindByPhone(String token, Long phone) throws JAXBException {
        baseStep.forSpecification();
        String body = jaxbWorker.soapRequestBody(token, phone);
        Response response = apiSteps.soapFindByPhone(body);
        String xmlBody = response.body().asString().toString();
        JAXBContext context = JAXBContext.newInstance(EnvelopeResponse.class);
        StringReader reader = new StringReader(xmlBody);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        EnvelopeResponse unmarshelledEnvelope = (EnvelopeResponse) unmarshaller.unmarshal(reader);
        String soapCustomerId = unmarshelledEnvelope.getResponseBodyXml().getCustomerId();
        return soapCustomerId;
        //Assertions.assertThat(soapCustomerId).as("Customer id пустой").isNotEmpty();
        //Assertions.assertThat(soapCustomerId).as("Id в старом сервисе не совпадает с Id нового сервиса").isEqualTo(restCustomerId);
    }*/
}



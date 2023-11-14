package steps;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.response.Response;
import models.soap.BodyXml;
import models.soap.EnvelopeXml;
import models.soap.HeaderXml;
import org.testng.annotations.Test;
import services.BaseStep;
import services.JaxbWorker;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class SoapFindByPhone {
    BaseStep baseStep = new BaseStep();
    JaxbWorker jaxbWorker = new JaxbWorker();
    GetNumberPhoneAfterCreate getNumberPhoneAfterCreate = new GetNumberPhoneAfterCreate();

@Test
    public void soapFindByPhone() throws JAXBException {
        baseStep.forSpecification();
        Long phone = getNumberPhoneAfterCreate.phoneNumber();
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

    }
}



package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.BaseStep;

import static io.restassured.RestAssured.given;

public class FindByPhoneNumberXML {
    BaseStep baseStep = new BaseStep();

    @Test
    public void findByPhoneNumber() {

        String xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ns3:Envelope xmlns:ns2=\"soap\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/envelope\">\n" +
                "    <ns2:Header>\n" +
                "        <authToken>" + baseStep.getTokenUser() + "</authToken>\n" +
                "    </ns2:Header>\n" +
                "    <ns2:Body>\n" +
                "        <phoneNumber>" + "79287765946" + "</phoneNumber>\n" +
                "    </ns2:Body>\n" +
                "</ns3:Envelope>";


        Response response = given()
                .when()
                .body(xmlBody)
                .contentType("application/xml")
                .post("/customer/findByPhoneNumber")
                .then().log().all()
                .extract().response();
        System.out.println(response);
    }
}

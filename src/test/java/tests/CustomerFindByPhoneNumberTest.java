package tests;

import io.restassured.response.Response;
import models.rest.AdditionalParameters;
import models.rest.AuthorizationModel;
import models.rest.CreateCustomerModel;
import models.soap.BodyXml;
import models.soap.EnvelopeXml;
import models.soap.HeaderXml;
import org.testng.annotations.Test;
import services.BaseStep;
import services.JaxbWorker;
import services.RandomStringGenerator;
import steps.ApiSteps;
import steps.GetEmptyPhoneStep;

import javax.xml.bind.JAXBException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerFindByPhoneNumberTest {
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
    JaxbWorker jaxbWorker = new JaxbWorker();
    ApiSteps apiSteps = new ApiSteps();

    @Test(description = "Проверка регистрации владельца в SOAP", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void customerFindByPhoneNumber(String login, String pass) throws JAXBException {
        Response response;
        int i = 0;
        AuthorizationModel authorizationModel = new AuthorizationModel(login, pass);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        System.out.println(token);
        List<Long> num = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        Long phoneNum = num.get(i);
        AdditionalParameters additionalParameters = new AdditionalParameters(randomStringGenerator.generateRandomString(10));
        CreateCustomerModel createCustomerModel = new CreateCustomerModel(phoneNum, randomStringGenerator.generateRandomString(7), additionalParameters);
        do {
            response = apiSteps.postCustomer(token, createCustomerModel);
            ++i;
        } while (response.getStatusCode() != 200 && i < num.size());
        String idCustomerRest = response.jsonPath().getString("id");
        HeaderXml headerXml = new HeaderXml(token);
        BodyXml bodyXml = new BodyXml(phoneNum);
        EnvelopeXml envelopeXml = new EnvelopeXml(headerXml, bodyXml);
        String body = jaxbWorker.soapRequestBody(envelopeXml, EnvelopeXml.class);
        System.out.println(body);
        String bodyResponse = apiSteps.soapFindByPhone(body).getBody().asString();
        String idCustomerSoap = jaxbWorker.unmarshallerEnvelopeBody(bodyResponse);
        System.out.println(idCustomerRest = idCustomerSoap);
        assertThat(idCustomerSoap).as("Id customer не соответствует id в Rest").isEqualTo(idCustomerRest);
    }
}

package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.rest.AdditionalParameters;
import models.rest.AuthorizationModel;
import models.rest.ChangeStatusModel;
import models.rest.CreateCustomerModel;
import services.JaxbWorker;

import javax.xml.bind.JAXBException;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    AuthorizationModel authorizationModel = new AuthorizationModel();
    ChangeStatusModel changeStatusModel = new ChangeStatusModel();
    CreateCustomerModel createCustomerModel = new CreateCustomerModel();
    JaxbWorker jaxbWorker = new JaxbWorker();

    @Step("Получение токена")
    public Response getToken(String login, String pass) {
        authorizationModel.setLogin(login);
        authorizationModel.setPassword(pass);
        Response response = given()
                .filter(new AllureRestAssured())
                .when()
                .contentType(ContentType.JSON)
                .body(authorizationModel)
                .post("/login")
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    @Step("Получение списка свободных номеров")
    public Response getEmptyPhone(String token) {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .get("/simcards/getEmptyPhone")
                .then()
                .extract().response();
        return response;
    }

    @Step("Регистрация владельца номера телефона")
    public Response postCustomer(String name, Long phoneNum, String param, String token) {
        createCustomerModel.setName(name);
        createCustomerModel.setPhone(phoneNum);
        AdditionalParameters additionalParameters = new AdditionalParameters();
        additionalParameters.setString(param);
        createCustomerModel.setAdditionalParameters(additionalParameters);
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .body(createCustomerModel)
                .post("/customer/postCustomer")
                .then().log().all()
                .extract().response();
        return response;
    }

    @Step("Получение владельца по id")
    public Response phoneNumber(String id, String token) {
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .param("customerId", id)
                .get("/customer/getCustomerById")
                .then().log().all()
                .extract().response();
        return response;
    }

    @Step("Изменение статуса владельца телефона")
    public Response postChangeCustomerStatus(String token, String customerId) {
        changeStatusModel.setStatus("Disable");
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .body(changeStatusModel)
                .post("/customer/" + customerId + "/changeCustomerStatus")
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    @Step("Получение id владельца в сервисе SOAP")
    public Response soapFindByPhone(String token, Long phone) throws JAXBException {
        String body = jaxbWorker.soapRequestBody(token, phone);
        Response response = given()
                .when()
                .contentType("application/xml")
                .body(body)
                .post("/customer/findByPhoneNumber")
                .then()
                .log().all()
                .extract().response();
        return response;
    }
}

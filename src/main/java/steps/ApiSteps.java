package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiSteps {

    @Step("Получение токена")
    public Response getToken(Object body) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/login")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Получение списка свободных номеров")
    public Response getEmptyPhone(String token) {
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .get("/simcards/getEmptyPhone")
                .then()
                .extract().response();
    }

    @Step("Регистрация владельца номера телефона")
    public Response postCustomer(String token, Object body) {
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .body(body)
                .post("/customer/postCustomer")
                .then().log().all()
                .extract().response();
    }

    @Step("Получение владельца по id")
    public Response getCustomerById(String id, String token) {
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .param("customerId", id)
                .get("/customer/getCustomerById")
                .then().log().all()
                .extract().response();
    }

    @Step("Изменение статуса владельца телефона")
    public Response postChangeCustomerStatus(String token, String customerId, Object body) {
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .body(body)
                .post("/customer/" + customerId + "/changeCustomerStatus")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Получение id владельца в сервисе SOAP")
    public Response soapFindByPhone(String body) {
        return RestAssured.given()
                .when()
                .contentType("application/xml")
                .body(body)
                .post("/customer/findByPhoneNumber")
                .then()
                .log().all()
                .extract().response();
    }
}

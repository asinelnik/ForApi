package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.BaseStep;

public class GetNumberPhoneAfterCreate {
    BaseStep baseStep = new BaseStep();
    GetCustomer getCustomer = new GetCustomer();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Step ("Создание нового кастомера")
    public Response phoneNumber() {
        baseStep.forSpecification();
        String id = getCustomer.getIdFromNewCustomer(getEmptyPhone.getEmptyPhoneWhile());
        String token = baseStep.getTokenUser();
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
}

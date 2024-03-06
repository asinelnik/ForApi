package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.BaseStep;

public class GetNumberPhoneAfterCreate {
    BaseStep baseStep = new BaseStep();
    GetCustomerStep getCustomerStep = new GetCustomerStep();
    GetEmptyPhoneStep getEmptyPhone = new GetEmptyPhoneStep();

   /*@Step ("Создание нового кастомера")
    public Response phoneNumber(String token) {
        baseStep.forSpecification();
        String id = getCustomerStep.createNewCustomer(getEmptyPhone.getEmptyPhoneWhile(token));
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .param("customerId", id)
                .get("/customer/getCustomerById")
                .then().log().all()
                .extract().response();
        return response;
    }*/
}

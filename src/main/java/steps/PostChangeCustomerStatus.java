package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.rest.ChangeStatusModel;
import services.BaseStep;

import static io.restassured.RestAssured.given;

public class PostChangeCustomerStatus {
    GetCustomerStep getCustomerStep = new GetCustomerStep();
    GetEmptyPhoneStep getEmptyPhone = new GetEmptyPhoneStep();
    BaseStep baseStep = new BaseStep();
    ChangeStatusModel changeStatusModel = new ChangeStatusModel();


  /*  @Step("Смена статуса владельца")
    public Response postChangeCustomerStatus(String token) {
        baseStep.forSpecification();
        changeStatusModel.setStatus("Disable");
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .body(changeStatusModel)
                .post("/customer/" + getCustomerStep.getIdFromNewCustomer(getEmptyPhone.getEmptyPhoneWhile(token)) + "/changeCustomerStatus")
                .then()
                .log().all()
                .extract().response();
        return response;
    }*/
}


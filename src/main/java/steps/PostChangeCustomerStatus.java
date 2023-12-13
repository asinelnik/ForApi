package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.rest.ChangeStatusModel;
import org.assertj.core.api.Assertions;
import services.BaseStep;

import static io.restassured.RestAssured.given;

public class PostChangeCustomerStatus {
    GetCustomer getCustomer = new GetCustomer();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();
    BaseStep baseStep = new BaseStep();
    ChangeStatusModel changeStatusModel = new ChangeStatusModel();


    @Step("Смена статуса владельца")
    public Response postChangeCustomerStatus(String token) {
        baseStep.forSpecification();
        changeStatusModel.setStatus("Disable");
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .body(changeStatusModel)
                .post("/customer/" + getCustomer.getIdFromNewCustomer(getEmptyPhone.getEmptyPhoneWhile(token)) + "/changeCustomerStatus")
                .then()
                .log().all()
                .extract().response();
        return response;
    }
}


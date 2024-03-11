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
    ApiSteps apiSteps = new ApiSteps();


    /*@Step("Смена статуса владельца")
    public Response postChangeCustomerStatus(String token) {
        baseStep.forSpecification();
        changeStatusModel.setStatus("Disable");
        Response res = apiSteps.postChangeCustomerStatus(baseStep.getTokenAdmin(), ,changeStatusModel);
    }*/
}


package tests;

import io.restassured.response.Response;
import models.rest.ChangeStatusModel;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.ApiSteps;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChangeCustomerStatusTest {
    BaseStep baseStep = new BaseStep();
    ApiSteps apiSteps = new ApiSteps();
    ChangeStatusModel changeStatusModel = new ChangeStatusModel();
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();

    @Test(description = "Смена статуса владельца телефона администратором")
    public void changeCustomerStatus() {
        String newStatus = "DISABLE";
        changeStatusModel.setStatus(newStatus);
        String token = baseStep.getTokenAdmin();
        String idCustomer = postCustomerStep.createNewCustomer(getEmptyPhoneStep.getEmptyPhoneWhile(token), token);
        Response changeStatus = apiSteps.postChangeCustomerStatus(token, idCustomer, changeStatusModel);
        assertThat(changeStatus.getStatusCode()).as("Некорректный статус код ответа сервера").isEqualTo(200);
        Response getCustomer = apiSteps.getCustomerById(idCustomer, token);
        assertThat(getCustomer.getStatusCode()).as("Некорректный статус код ответа сервера").isEqualTo(200);
        String statusCustomer = getCustomer.jsonPath().getString("return.status");
        assertThat(statusCustomer.equals(newStatus)).as("Статус кастомера не изменился").isTrue();
    }
}

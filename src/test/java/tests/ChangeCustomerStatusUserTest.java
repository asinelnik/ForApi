package tests;

import io.restassured.response.Response;
import models.rest.ChangeStatusModel;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.ApiSteps;
import steps.PostCustomerStep;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChangeCustomerStatusUserTest extends BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    PostCustomerStep postCustomerStep = new PostCustomerStep();

    @Test(description = "Смена статуса владельца телефона администратором")
    public void changeCustomerStatus() {
        String newStatus = "DISABLE";
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(newStatus);
        String token = getTokenUser();
        String idCustomer = postCustomerStep.createNewCustomer(getEmptyPhoneWhile(token), token);
        Response changeStatus = apiSteps.postChangeCustomerStatus(token, idCustomer, changeStatusModel);
        assertThat(changeStatus.getStatusCode()).as("Некорректный статус код ответа сервера").isEqualTo(401);
        assertThat(changeStatus.jsonPath().getString("errorMessage")).as("Текст ошибки не соответствует заданному")
                .isEqualTo("У пользователя не хватает прав на выполнение команды");
    }
}

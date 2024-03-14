package tests;

import io.restassured.response.Response;
import models.rest.ChangeStatusModel;
import org.testng.annotations.Test;
import services.BaseStep;
import services.RandomStringGenerator;
import steps.ApiSteps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChangeCustomerStatusAdminTest extends BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    @Test(testName = "Смена статуса владельца телефона администратором", description = "Смена статуса владельца телефона администратором")
    public void changeCustomerStatus() {
        String newStatus = "DISABLE";
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(newStatus);
        String token = getTokenAdmin();
        String idCustomer = createNewCustomer(getEmptyPhoneWhile(token), token, randomStringGenerator.generateRandomString(5), randomStringGenerator.generateRandomString(10)).getBody().jsonPath().getString("id");
        Response changeStatus = apiSteps.postChangeCustomerStatus(token, idCustomer, changeStatusModel);
        assertThat(changeStatus.getStatusCode()).as("Некорректный статус код ответа сервера").isEqualTo(200);
        Response getCustomer = apiSteps.getCustomerById(idCustomer, token);
        assertThat(getCustomer.getStatusCode()).as("Некорректный статус код ответа сервера").isEqualTo(200);
        String statusCustomer = getCustomer.jsonPath().getString("return.status");
        assertThat(statusCustomer.equals(newStatus)).as("Статус кастомера не изменился").isTrue();
    }
}

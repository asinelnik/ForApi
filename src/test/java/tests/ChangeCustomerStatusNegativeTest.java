package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.PostChangeCustomerStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChangeCustomerStatusNegativeTest {
    BaseStep baseStep = new BaseStep();
    PostChangeCustomerStatus postChangeCustomerStatus = new PostChangeCustomerStatus();

    @Test(description = "Смена статуса владельца телефона обычным пользователем")
    public void changeCustomerStatus() {
        String token = baseStep.getTokenUser();
        Response res = postChangeCustomerStatus.postChangeCustomerStatus(token);
        assertThat(res.getStatusCode()).as("Получен некорректный статус кода").isEqualTo(401);
        assertThat(res.getBody().jsonPath().get("errorMessage").toString()).as("Некорректное сообщение об ошибке").isEqualTo("У пользователя не хватает прав на выполнение команды");
    }
}

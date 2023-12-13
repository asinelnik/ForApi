package tests;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.PostChangeCustomerStatus;

public class ChangeCustomerStatusTest {
    BaseStep baseStep = new BaseStep();
    PostChangeCustomerStatus postChangeCustomerStatus = new PostChangeCustomerStatus();

    @Test(description = "Смена статуса владельца телефона администратором")
    public void changeCustomerStatus() {
        String token = baseStep.getTokenAdmin();
        Response res = postChangeCustomerStatus.postChangeCustomerStatus(token);
        Assertions.assertThat(res.getStatusCode()).as("").isEqualTo(200);
    }
}

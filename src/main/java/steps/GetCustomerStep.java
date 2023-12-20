package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import services.BaseStep;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public class GetCustomerStep extends BaseStep {

    ApiSteps apiSteps = new ApiSteps();

    @Step("Получение владельца телефона по id с проверкой серии и номера паспорта")
    public String getIdCustomer(String id, String token) {
        await().atMost(2, TimeUnit.MINUTES).with().pollInterval(5, TimeUnit.SECONDS).until(() -> {
            Response res = apiSteps.getCustomerById(id, token);
            if (res.jsonPath().getString("return.status").equals("ACTIVE")) {
                return true;
            }
            return false;
        });
        Response response = apiSteps.getCustomerById(id, token);
        return response.jsonPath().get("return.pd");
    }
}

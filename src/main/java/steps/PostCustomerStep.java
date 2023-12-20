package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import services.BaseStep;

import java.util.List;

public class PostCustomerStep extends BaseStep {
    ApiSteps apiSteps = new ApiSteps();

    @Step("Регистрация владельца номера телефона")
    public String createNewCustomer(List<Long> listPhone, String token) {
        int i = 0;
        Response response;
        do {
            response = apiSteps.postCustomer("Alex", listPhone.get(i), "param", token);
            ++i;
        } while (response.getStatusCode() != 200 && i < listPhone.size());
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("All attempts to create a new customer failed.");
        }
        return response.getBody().jsonPath().getString("id");
    }

    @Step("Создание кастомера")
    public void createCustomer(List<Long> listPhone, String token) {
        Response response;
        List<Long> phones = listPhone;
        int size = phones.size();
        for (int i = 0; i < size; i++) {
            response = apiSteps.postCustomer("Alex", phones.get(i), "param", token);
            if (response.getStatusCode() == 200) {
                Assertions.assertThat(response.getBody().jsonPath().getString("id").isEmpty()).as("Новый кастомер не создан").isFalse();
                break;
            }
        }
    }


}


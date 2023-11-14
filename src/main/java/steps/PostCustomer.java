package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import services.BaseStep;

import java.util.List;

public class PostCustomer extends BaseStep {

    @Step("Создание кастомера")
    public void createCustomer(List<Long> listPhone) {
        Response response;
        List<Long> phones = listPhone;
        System.out.println(phones);
        int size = phones.size();
        for (int i = 0; i < size; i++) {
            response = postCustomer("Alex", phones.get(i), "param");
            if (response.getStatusCode() == 200) {
                Assertions.assertThat(response.getBody().jsonPath().getString("id").isEmpty()).as("Новый кастомер не создан").isFalse();
                break;
            }
        }
    }
}


package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.rest.AdditionalParameters;
import models.rest.CreateCustomerModel;
import org.assertj.core.api.Assertions;
import services.BaseStep;
import services.RandomStringGenerator;

import java.util.List;

public class PostCustomerStep extends BaseStep {

    RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
    CreateCustomerModel createCustomerModel = new CreateCustomerModel();
    ApiSteps apiSteps = new ApiSteps();

    @Step("Регистрация владельца номера телефона")
    public String createNewCustomer(List<Long> listPhone, String token) {
        int i = 0;
        Response response;
        String name = randomStringGenerator.generateRandomString(5);
        String param = randomStringGenerator.generateRandomString(10);
        createCustomerModel.setName(name);
        createCustomerModel.setPhone(listPhone.get(i));
        AdditionalParameters additionalParameters = new AdditionalParameters();
        additionalParameters.setString(param);
        createCustomerModel.setAdditionalParameters(additionalParameters);
        do {
            response = apiSteps.postCustomer(token, createCustomerModel);
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
            String name = randomStringGenerator.generateRandomString(5);
            String param = randomStringGenerator.generateRandomString(10);
            createCustomerModel.setName(name);
            createCustomerModel.setPhone(listPhone.get(i));
            AdditionalParameters additionalParameters = new AdditionalParameters();
            additionalParameters.setString(param);
            createCustomerModel.setAdditionalParameters(additionalParameters);
            response = apiSteps.postCustomer(token, createCustomerModel);
            if (response.getStatusCode() == 200) {
                Assertions.assertThat(response.getBody().jsonPath().getString("id").isEmpty()).as("Новый кастомер не создан").isFalse();
                break;
            }
        }
    }
}


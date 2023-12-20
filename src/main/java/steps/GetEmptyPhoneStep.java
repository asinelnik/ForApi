package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.rest.Phones;

import java.util.List;
import java.util.stream.Collectors;

public class GetEmptyPhoneStep {
    ApiSteps apiSteps = new ApiSteps();

    @Step("Получение свободных номеров")
    public List<Long> getEmptyPhoneWhile(String token) {
        Response response;
        do {
            response = apiSteps.getEmptyPhone(token);
        } while (response.getBody().jsonPath().getList("phones", Phones.class)
                .stream().map(Phones::getPhone).collect(Collectors.toList()).size() < 5);
        return response.getBody().jsonPath().getList("phones", Phones.class).stream().map(Phones::getPhone).collect(Collectors.toList());
    }
}

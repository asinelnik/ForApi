package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.rest.Phones;
import services.BaseStep;

import java.util.List;
import java.util.stream.Collectors;

public class GetEmptyPhone {
    BaseStep baseStep = new BaseStep();

    @Step("Получение свободных номеров")
    public List<Long> getEmptyPhoneWhile() {
        Response response;
        String tkn = baseStep.getTokenUser();
        do {
            response = baseStep.getEmptyPhone(tkn);
        } while (response.getBody().jsonPath().getList("phones", Phones.class)
                .stream().map(Phones::getPhone).collect(Collectors.toList()).size() < 3);
        return response.getBody().jsonPath().getList("phones", Phones.class).stream().map(Phones::getPhone).collect(Collectors.toList());
    }
}

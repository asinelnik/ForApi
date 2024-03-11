package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import models.rest.AuthorizationModel;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.ApiSteps;
import steps.GetEmptyPhoneStep;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetEmptyPhonesTest {
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    AuthorizationModel authorizationModel = new AuthorizationModel();
    ApiSteps apiSteps = new ApiSteps();

    @Test(description = "Получение списка свободных номеров", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)
    public void getEmptyPhoneNumberUserAwait(String login, String pass) {
        RestAssured.filters(new AllureRestAssured());
        authorizationModel.setLogin(login);
        authorizationModel.setPassword(pass);
        String token = apiSteps.getToken(authorizationModel).jsonPath().getString("token");
        List<Long> phoneNumber = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        assertThat(!phoneNumber.isEmpty()).as("Список номеров пуст").isTrue();
    }
}
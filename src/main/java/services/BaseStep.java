package services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.rest.AdditionalParameters;
import models.rest.AuthorizationModel;
import models.rest.CreateCustomerModel;
import models.rest.Phones;
import org.testng.annotations.DataProvider;
import steps.ApiSteps;

import java.util.List;
import java.util.stream.Collectors;

public class BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    private final String URL = ConfigProvider.URL;
    static String USER_LOGIN = ConfigProvider.USER_LOGIN;
    static String USER_PASSWORD = ConfigProvider.USER_PASSWORD;
    static String ADMIN_LOGIN = ConfigProvider.ADMIN_LOGIN;
    static String ADMIN_PASSWORD = ConfigProvider.ADMIN_PASSWORD;

    @DataProvider(name = "authParamForGetToken")
    public static Object[][] authParamForGetToken() {
        return new Object[][]{
                {USER_LOGIN, USER_PASSWORD},
                {ADMIN_LOGIN, ADMIN_PASSWORD}
        };
    }

    @Step("Получение токена для User")
    public String getTokenUser() {
        AuthorizationModel authorizationModel = new AuthorizationModel(USER_LOGIN, USER_PASSWORD);
        return apiSteps.getToken(authorizationModel).jsonPath().getString("token");
    }

    @Step("Получение токена для Admin")
    public String getTokenAdmin() {
        AuthorizationModel authorizationModel = new AuthorizationModel(ADMIN_LOGIN, ADMIN_PASSWORD);
        return apiSteps.getToken(authorizationModel).jsonPath().getString("token");
    }

    @Step("Получение свободных номеров")
    public List<Long> getEmptyPhoneWhile(String token) {
        Response response;
        do {
            response = apiSteps.getEmptyPhone(token);
        } while (response.getBody().jsonPath().getList("phones", Phones.class)
                .stream().map(Phones::getPhone).count() < 7);
        return response.getBody().jsonPath().getList("phones", Phones.class).stream().map(Phones::getPhone).collect(Collectors.toList());
    }

    @Step("Регистрация владельца номера телефона")
    public Response createNewCustomer(List<Long> listPhone, String token, String name, String param) {
        int i = 0;
        Response response;
        AdditionalParameters additionalParameters = new AdditionalParameters(param);
        CreateCustomerModel createCustomerModel = new CreateCustomerModel(listPhone.get(i), name, additionalParameters);
        do {
            response = apiSteps.postCustomer(token, createCustomerModel);
            ++i;
        } while (response.getStatusCode() != 200 && i < listPhone.size());
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("All attempts to create a new customer failed.");
        }
        return response;
    }
}


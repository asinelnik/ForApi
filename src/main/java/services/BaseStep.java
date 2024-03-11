package services;

import io.qameta.allure.Step;
import models.rest.AuthorizationModel;
import org.testng.annotations.DataProvider;
import specs.Specifications;
import steps.ApiSteps;

public class BaseStep {
    ApiSteps apiSteps = new ApiSteps();
    AuthorizationModel authorizationModel = new AuthorizationModel();
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

    public void forSpecification() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
    }

    @Step
    public String getTokenUser() {
        authorizationModel.setLogin(USER_LOGIN);
        authorizationModel.setPassword(USER_PASSWORD);
        return apiSteps.getToken(authorizationModel).jsonPath().getString("token");
    }

    @Step
    public String getTokenAdmin() {
        authorizationModel.setLogin(ADMIN_LOGIN);
        authorizationModel.setPassword(ADMIN_PASSWORD);
        return apiSteps.getToken(authorizationModel).jsonPath().getString("token");
    }
}


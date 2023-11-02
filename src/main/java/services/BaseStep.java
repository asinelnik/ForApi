package services;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthorizationModel;
import org.awaitility.Awaitility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import specs.Specifications;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseStep {
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

    @BeforeMethod
    public void setup() {
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, SECONDS);
        Awaitility.setDefaultTimeout(7, SECONDS);
    }

    public void forSpecification() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
    }

    public String getToken(String login, String pass) {
        authorizationModel.setLogin(login);
        authorizationModel.setPassword(pass);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(authorizationModel)
                .post("/login");
        String token = response.jsonPath().getString("token");
        return token;
    }

    @Step
    public String getTokenUser() {
        return getToken(USER_LOGIN, USER_PASSWORD);
    }

    @Step
    public String getTokenAdmin() {
        return getToken(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    public Response getEmptyPhone(String token) {
        forSpecification();
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .get("/simcards/getEmptyPhone")
                .then()
                .extract().response();
        return response;

    }
}


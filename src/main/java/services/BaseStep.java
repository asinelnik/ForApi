package services;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.rest.AdditionalParameters;
import models.rest.AuthorizationModel;
import models.rest.CreateCustomerModel;
import org.testng.annotations.DataProvider;
import specs.Specifications;

import static io.restassured.RestAssured.given;

public class BaseStep {
    CreateCustomerModel createCustomerModel = new CreateCustomerModel();
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

    public String getToken(String login, String pass) {
        authorizationModel.setLogin(login);
        authorizationModel.setPassword(pass);
        Response response = given()
                .filter(new AllureRestAssured())
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
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", token)
                .get("/simcards/getEmptyPhone")
                .then()
                .extract().response();
        return response;
    }

    public Response postCustomer(String name, Long phoneNum, String param) {
        forSpecification();
        createCustomerModel.setName(name);
        createCustomerModel.setPhone(phoneNum);
        AdditionalParameters additionalParameters = new AdditionalParameters();
        additionalParameters.setString(param);
        createCustomerModel.setAdditionalParameters(additionalParameters);
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", getTokenUser())
                .body(createCustomerModel)
                .post("/customer/postCustomer")
                .then().log().all()
                .extract().response();
        return response;
    }
}


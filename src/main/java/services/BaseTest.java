package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthModel;
import models.Phone;
import specs.RequestSpec;
import specs.Specifications;

import java.util.List;
import java.util.stream.Collectors;

public class BaseTest {

    String URL = ConfigProvider.URL;
    String USER_LOGIN = ConfigProvider.USER_LOGIN;
    String USER_PASSWORD = ConfigProvider.USER_PASSWORD;
    String ADMIN_LOGIN = ConfigProvider.ADMIN_LOGIN;
    String ADMIN_PASSWORD = ConfigProvider.ADMIN_PASSWORD;



    public void forSpecification() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
    }

    public String getTokenUser() {
        AuthModel authModel = new AuthModel();
        authModel.setLogin(USER_LOGIN);
        authModel.setPassword(USER_PASSWORD);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(authModel)
                .post("/login");
        String token = response.jsonPath().getString("token");
        return token;
    }
    public String getTokenAdmin(){
        AuthModel authModel = new AuthModel();
        authModel.setLogin(ADMIN_LOGIN);
        authModel.setPassword(ADMIN_PASSWORD);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(authModel)
                .post("/login");
        String token = response.jsonPath().getString("token");
        return token;
    }
}

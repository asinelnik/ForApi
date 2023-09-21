package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Phone;
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

    public String getToken (String a, String b) {
        forSpecification();
        String body = String.format("{\"login\": \"%s\", \"password\": \"%s\"}", a, b);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/login");
        String token = response.jsonPath().getString("token");
        return token;
    }

    public String getTokenUser () {
        forSpecification();
        String body = String.format("{\"login\": \"%s\", \"password\": \"%s\"}", USER_LOGIN, USER_PASSWORD);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/login");
        String token = response.jsonPath().getString("token");
        return token;
    }

    public void getPhoneNumberUser() {
        forSpecification();
        List<Phone> phones = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", getToken(USER_LOGIN, USER_PASSWORD))
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phone.class);
        List<Long> phoneNumber = phones.stream().map(Phone::getPhone).collect(Collectors.toList());
    }
}

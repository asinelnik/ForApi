package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Phone;
import org.testng.annotations.Test;
import services.BaseTest;
import services.ConfigProvider;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllPhoneNumber {
    BaseTest baseTest = new BaseTest();
    String LOGIN = ConfigProvider.USER_LOGIN;
    String PASSWORD = ConfigProvider.USER_PASSWORD;

    public Long getEmptyPhoneNumber() {
        baseTest.forSpecification();
        List<Phone> phones = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getToken(LOGIN, PASSWORD))
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phone.class);
        List<Long> phoneNumber = phones.stream().map(Phone::getPhone).collect(Collectors.toList());
        Long testPhone = phoneNumber.get(1);
    return testPhone;
    }
}

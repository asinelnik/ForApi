package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Phone;
import org.assertj.core.api.Assertions;
import services.BaseTest;
import services.ConfigProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.given;

public class GetEmptyPhone {
    BaseTest baseTest = new BaseTest();

    public List<Long> getEmptyPhoneNumberUser() {
        List<Phone> phones = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phone.class);
        List<Long> phoneNumber = phones.stream().map(Phone::getPhone).collect(Collectors.toList());
        return phoneNumber;
    }

    public void getEmptyPhoneNumberUserAwait() {
        List<Phone> phones = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phone.class);
        //await().with().pollInterval(2, TimeUnit.SECONDS).atMost(1, TimeUnit.MINUTES).until();
    }
    public List<Long> responseGetEmptyPhone(){
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response();
        await().pollInterval(2, TimeUnit.SECONDS).atMost(2, TimeUnit.MINUTES).until(()-> {
            if (response.statusCode() == 200 && response.getBody().jsonPath().getList("phones", Phone.class).size() > 0) {
                return true;
            }
            return null;
        });
        List<Long> phoneNumber = response.getBody().jsonPath().getList("phones", Phone.class).stream().map(Phone::getPhone).collect(Collectors.toList());
        return phoneNumber;
    }
}

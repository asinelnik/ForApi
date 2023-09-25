package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Phone;
import services.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Long> responseGetEmptyPhone() {
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
        List<Long> phoneNumber = response.getBody().jsonPath().getList("phones", Phone.class).stream().map(Phone::getPhone).collect(Collectors.toList());
        while (response.getStatusCode() != 200) {
            Response responseRef = RestAssured.given()
                    .when()
                    .contentType(ContentType.JSON)
                    .header("authToken", baseTest.getTokenUser())
                    .header("Connection", "keep-alive")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept", "*/*")
                    .get("/simcards/getEmptyPhone")
                    .then().log().all()
                    .extract().response();
            if (responseRef.getStatusCode() == 200 &&
                    !responseRef.getBody().jsonPath().getList("phones", Phone.class).stream().map(Phone::getPhone).collect(Collectors.toList()).isEmpty())
                break;
        }
        return phoneNumber;
    }
}

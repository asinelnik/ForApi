package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Phone;
import services.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.await;

public class GetEmptyPhone {
    BaseTest baseTest = new BaseTest();

    public List<Long> getEmptyPhoneNumberUser() {
        int i = 0;
        Response response;
        do {
            response = RestAssured.given()
                    .when()
                    .contentType(ContentType.JSON)
                    .header("authToken", baseTest.getTokenUser())
                    .header("Connection", "keep-alive")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept", "*/*")
                    .get("/simcards/getEmptyPhone")
                    .then().log().all()
                    .extract().response();
            i++;
        }
        while (response.getStatusCode() == 500 || response.getBody().jsonPath().getList("phones").isEmpty() || i > 5);
        if (i > 5) {
            System.out.println("Превышено количество выполнения цикла");
        }
        List<Long> phoneNumber = response.getBody().jsonPath()
                .getList("phones", Phone.class).stream().map(Phone::getPhone).collect(Collectors.toList());
        System.out.println(phoneNumber);
        return phoneNumber;
    }

    public List<Long> getEmptyPhoneNumberUserWithAwait() {
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
        await().with().pollInterval(200, TimeUnit.MILLISECONDS).atMost(2, TimeUnit.SECONDS)
                .until(() -> (response.getStatusCode() != 500 && !response.getBody().jsonPath().getList("phones").isEmpty()));
        List<Long> phoneNumber = response.getBody().jsonPath()
                .getList("phones", Phone.class).stream().map(Phone::getPhone).collect(Collectors.toList());
        System.out.println(phoneNumber);
        return phoneNumber;
    }
}

import Service.BaseTest;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EmptyPhone {
    BaseTest baseTest = new BaseTest();
    String URL = "http://localhost:8080/";

    @Test
    public void getEmptyPhone() {
        baseTest.forSpecification();
        List<Phone> phones = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getToken())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phone.class);
        Assertions.assertThat(phones).as("Свободный номер не найден").extracting(Phone::getPhone).isNotNull();
    }
}

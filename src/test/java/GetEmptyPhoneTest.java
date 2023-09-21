import services.BaseTest;
import services.ConfigProvider;
import models.Phone;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetEmptyPhoneTest {
    BaseTest baseTest = new BaseTest();
    String LOGIN = ConfigProvider.USER_LOGIN;
    String PASSWORD = ConfigProvider.USER_PASSWORD;

    @Test
    public void getEmptyPhone() {
        baseTest.forSpecification();
        List<Phone> phones = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getToken(LOGIN, PASSWORD))
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phone.class);
        Assertions.assertThat(phones).as("Свободный номер не найден").extracting(Phone::getPhone).isNotNull();
    }
}

import Service.BaseTest;
import Service.Specifications;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EmptyPhone {
    BaseTest baseTest = new BaseTest();
    String URL = "http://localhost:8080/";

    @Test
    public void getEmptyPhone() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        List<PhoneEmptyData> phoneEmptyData = given()
                .when()
                .header("authToken", baseTest.getToken())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().body().jsonPath().getList("phones", PhoneEmptyData.class);
        int i = 0;
    }
}

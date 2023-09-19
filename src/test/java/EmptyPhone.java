import Service.BaseTest;
import Service.Specifications;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EmptyPhone {
    BaseTest baseTest = new BaseTest();
    String URL = "http://localhost:8080/";

    @Test
    public void getEmptyPhone() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        List<Phone> phones = given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getToken())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("simcards/getEmptyPhone")
                .then().log().all()
                .extract().body().jsonPath().getList("phones", Phone.class);
        int i = 1;
    }
}

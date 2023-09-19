import Service.Specifications;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class EmptyPhone {
    public final static String URL = "http://localhost:8080/";

    public String getToken() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        Response response = given()
                .body("{\"login\": \"admin\", \"password\": \"password\"}")
                .when()
                .post("login")
                .then().log().all()
                .extract().response();
        String token = response.path("token");
        return token;
    }

    @Test
    public void getEmptyPhone() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        List<PhoneEmptyData> phoneEmptyData = given()
                .when()
                .header("authToken", getToken())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept", "*/*")
                .get("simcards/getEmptyPhone")
                .then().log().all()
                .extract().body().jsonPath().getList("phones", PhoneEmptyData.class);
    }
}

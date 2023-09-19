package Service;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public final static String URL = "http://localhost:8080/";

    public String getTokenTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        List<SuccessAuthData> successAuthData = RestAssured.given()
                .body("{\"login\": \"admin\", \"password\": \"password\"}")
                .when()
                .post("login")
                .then().log().all()
                .extract().response().body().path("token");
        return successAuthData.toString();
    }
}

package Service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public final static String URL = "http://localhost:8080/";

    public String getToken() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"login\": \"admin\", \"password\": \"password\"}")
                .post("login");
        String token = response.jsonPath().getString("token");
        return token;
    }
}

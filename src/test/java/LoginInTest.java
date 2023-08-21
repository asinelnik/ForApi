import Service.Specifications;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginInTest {
    public final static String URL = "http://localhost:8080/";

    @Test
    public void getTokenTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        UserData userData = new UserData("admin", "password");
        SucsesAuth sucsesAuth = given()
                .body(userData)
                .when()
                .post("login")
                .then().log().all()
                .extract().as(SucsesAuth.class);
        assertThat(sucsesAuth.getToken()).as("1654").isNotNull();
    }

    @Test
    public void getEmptyPhone(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());

    }
}

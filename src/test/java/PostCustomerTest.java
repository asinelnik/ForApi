import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseTest;
import steps.GetAllPhoneNumber;

public class PostCustomerTest {
    GetAllPhoneNumber getAllPhoneNumber = new GetAllPhoneNumber();
    BaseTest baseTest = new BaseTest();
    String URL = "http://localhost:8080/";
    @Test
    public void createCustomer(){
        baseTest.forSpecification();
        String body = String.format("{\"name\": \"Alex\", \"phone\": %s, \"additionalParameters\":{\"string\": \"test\"}", getAllPhoneNumber.getEmptyPhoneNumber());
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .body(body)
                .post("/customer/postCustomer");
        String customerId = response.jsonPath().getString("id");
        Assertions.assertThat(customerId).as("").isNotNull();

    }
}

package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.AdditionalParameters;
import models.Customer;
import org.assertj.core.api.Assertions;
import services.BaseTest;
import specs.RequestSpec;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class PostCustomer {
    BaseTest baseTest = new BaseTest();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();
    Customer customer = new Customer();
    AdditionalParameters additionalParameters = new AdditionalParameters();
    RequestSpec requestSpec = new RequestSpec();

    public void createCustomer() {
        baseTest.forSpecification();
        customer.setName("Alex");
        customer.setPhone(getEmptyPhone.responseGetEmptyPhone().get(0));
        additionalParameters.setString("param");
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .body(customer)
                .post("/customer/postCustomer");
        String customerId = response.jsonPath().getString("id");
        Assertions.assertThat(customerId).as("CustomerId не получен").isNotNull();
    }
}

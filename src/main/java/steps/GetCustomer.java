package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.BaseStep;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class GetCustomer {
    BaseStep baseStep = new BaseStep();

    public void getIdCustomer() {
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseStep.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .param("customerId", "")
                .get("/customer/getCustomerById")
                .then().log().all()
                .extract().response();
        String idcust = response.getBody().jsonPath().getString("pd");
        String status = response.getBody().jsonPath().getString("status");
        assertThat(status).as("Статус абонента не соответствует ожидаемому").isEqualTo("ACTIVE");
        assertThat(idcust).as("").isNotEmpty();

    }

}

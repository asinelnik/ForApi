package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import services.BaseStep;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class GetCustomer extends BaseStep {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();
    PostCustomer postCustomer = new PostCustomer();

    @Step
    public String getIdFromNewCustomer(List<Long> listPhone) {
        Response response;
        List<Long> phones = listPhone;
        System.out.println(phones);
        int size = phones.size();
        for (int i = 0; i < size; i++) {
            response = postCustomerB("Alex", phones.get(i), "param");
            while (response.getStatusCode() == 200)
                return response.getBody().jsonPath().getString("id");
        }
        return null;
    }

    @Step
    public void getIdCustomer(String id) {
        forSpecification();
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", getTokenUser())
                .param("customerId", id)
                .get("/customer/getCustomerById")
                .then().log().all()
                .extract().response();
        String pd = response.jsonPath().get("return.pd");
        Assertions.assertThat(response.jsonPath().getString("return.status")).as("Статус абонента не соответствует").isEqualTo("ACTIVE");
        Assertions.assertThat(JsonPath.from(pd).getString("passportNumber").length()).as("Некорректное количество цифр в номере паспорта").isEqualTo(6);
        Assertions.assertThat(JsonPath.from(pd).getString("passportSeries").length()).as("Некорректное количество цифр в серии паспорта").isEqualTo(4);
    }
}

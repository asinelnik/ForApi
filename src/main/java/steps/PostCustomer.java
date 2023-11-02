package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AdditionalParameters;
import models.CreateCustomerModel;
import org.assertj.core.api.Assertions;
import services.BaseStep;

import java.util.List;

public class PostCustomer extends BaseStep {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    CreateCustomerModel createCustomerModel = new CreateCustomerModel();

    public Response postCustomer(String name, Long phoneNum, String param) {
        List<Long> number = getEmptyPhone.getEmptyPhoneWhile();
        String tkn = getTokenUser();
        forSpecification();
        createCustomerModel.setName(name);
        createCustomerModel.setPhone(phoneNum);
        AdditionalParameters additionalParameters = new AdditionalParameters();
        additionalParameters.setString(param);
        createCustomerModel.setAdditionalParameters(additionalParameters);
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", tkn)
                .body(createCustomerModel)
                .post("/customer/postCustomer")
                .then().log().all()
                .extract().response();
        return response;
    }
    @Step("Создание кастомера")
    public void createCustomer(List<Long> listPhone) {
        Response response;
        List<Long> phones = listPhone;
        System.out.println(phones);
        int size = phones.size();
        for (int i = 0; i < size; i++) {
            response = postCustomer("Alex", phones.get(i), "param");
            if (response.getStatusCode() == 200) {
                Assertions.assertThat(response.getBody().jsonPath().getString("id").isEmpty()).as("Новый кастомер не создан").isFalse();
                break;
            }
            continue;
        }
    }
}


package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AdditionalParameters;
import models.Customer;
import org.assertj.core.api.Assertions;
import services.BaseTest;

import java.util.List;

public class PostCustomer {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();
    BaseTest baseTest = new BaseTest();
    Customer customer = new Customer();
    AdditionalParameters additionalParameters = new AdditionalParameters();

    public Response postCustomer(Long a){
        baseTest.forSpecification();
        customer.setName("Alex");
        customer.setPhone(a);
        additionalParameters.setString("param");
        Response response = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseTest.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .body(customer)
                .post("/customer/postCustomer")
                .then().log().all()
                .extract().response();
        return response;
    }
    public List<Long> listPhoneNumber(){
        List<Long> phones = getEmptyPhone.getEmptyPhoneNumberUser();
        return phones;
    }
    public void createCustomer() {
        List<Long> numberPhones = listPhoneNumber();
        Integer index=0;
        Long i = numberPhones.get(index);
        while(postCustomer(i).getStatusCode()!=200){
            if(postCustomer(i).getStatusCode()==400){
                index++;
                System.out.println(numberPhones.get(index));
            } else {
                continue;
            }
        }
        String customerId = postCustomer(i).getBody().jsonPath().getString("id");
        Assertions.assertThat(customerId).as("CustomerId не получен").isNotNull();
    }
}

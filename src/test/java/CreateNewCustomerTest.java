import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseTest;
import steps.GetAllPhoneNumber;
import steps.PostCustomer;

public class CreateNewCustomerTest {
    GetAllPhoneNumber getAllPhoneNumber = new GetAllPhoneNumber();
    PostCustomer postCustomer = new PostCustomer();
    BaseTest baseTest = new BaseTest();
    String URL = "http://localhost:8080/";
    @Test
    public void createCustomer(){
        baseTest.forSpecification();
        postCustomer.createCustomer();
    }
}

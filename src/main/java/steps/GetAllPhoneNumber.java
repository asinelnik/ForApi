package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Phones;
import services.BaseStep;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllPhoneNumber {
    BaseStep baseStep = new BaseStep();

    public Long getEmptyPhoneNumber() {
        baseStep.forSpecification();
        List<Phones> phones = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .header("authToken", baseStep.getTokenUser())
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept", "*/*")
                .get("/simcards/getEmptyPhone")
                .then().log().all()
                .extract().response().jsonPath().getList("phones", Phones.class);
        List<Long> phoneNumber = phones.stream().map(Phones::getPhone).collect(Collectors.toList());
        Long testPhone = phoneNumber.get(0);
        return testPhone;
    }
}

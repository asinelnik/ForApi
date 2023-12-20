package tests;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetCustomerStep;
import steps.GetEmptyPhoneStep;
import steps.PostCustomerStep;

import java.util.List;

@Test
public class EndToEndTest {
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();
    PostCustomerStep postCustomerStep = new PostCustomerStep();
    GetCustomerStep getCustomerStep = new GetCustomerStep();
    BaseStep baseStep = new BaseStep();

    public void endToEnd() {
        baseStep.forSpecification();

        SoftAssertions softAssertions = new SoftAssertions();

        String token = "9b28f94b619f46ccb8a7d8de6768f12a";
        List<Long> numbers = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        String id = postCustomerStep.createNewCustomer(numbers, token);
        String passport = getCustomerStep.getIdCustomer(id, token);
        softAssertions.assertThat(JsonPath.from(passport).getString("passportSeries").length())
                .as("Некорректное количество цифр в серии паспорта").isEqualTo(4);
        softAssertions.assertThat(JsonPath.from(passport).getString("passportNumber").length())
                .as("Некорректное количество цифр в номере паспорта").isEqualTo(6);

        softAssertions.assertAll();
    }
}

package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import steps.GetEmptyPhoneStep;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetEmptyPhonesTest {
    GetEmptyPhoneStep getEmptyPhoneStep = new GetEmptyPhoneStep();

    @Test (description = "Получение списка свободных номеров")
    public void getEmptyPhoneNumberUserAwait(String token) {
        RestAssured.filters(new AllureRestAssured());
        List<Long> telnum = getEmptyPhoneStep.getEmptyPhoneWhile(token);
        assertThat(!telnum.isEmpty()).as("Список номеров пуст").isTrue();
    }
}
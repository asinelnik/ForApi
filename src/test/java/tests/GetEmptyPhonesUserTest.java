package tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseStep;
import steps.GetEmptyPhone;

import java.util.List;

public class GetEmptyPhonesUserTest {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();
    @Test(description = "Получение списка свободных номеров", dataProvider = "authParamForGetToken", dataProviderClass = BaseStep.class)

    public void getEmptyPhoneNumberUserAwait(String Login, String Password) {
        List<Long> telnum = getEmptyPhone.getEmptyPhoneWhile(Login, Password);
        System.out.println(telnum);
        Assertions.assertThat(telnum.size() > 0);
    }
}
package Tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import steps.GetEmptyPhone;

import java.util.List;

public class GetEmptyPhonesUserTest {
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();


    @Test(description = "Получение списка свободных номеров")
    public void getEmptyPhoneNumberUserAwait() {
        List<Long> telnum = getEmptyPhone.getEmptyPhoneWhile();
        System.out.println(telnum);
        Assertions.assertThat(telnum.size() > 0);
    }
}
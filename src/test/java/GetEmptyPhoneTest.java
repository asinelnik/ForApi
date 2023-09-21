import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseTest;
import steps.GetEmptyPhone;

import java.util.List;

public class GetEmptyPhoneTest {
    BaseTest baseTest = new BaseTest();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test
    public void getEmptyPhone() {
        List<Long> phones = getEmptyPhone.getEmptyPhoneNumberUser();
        Assertions.assertThat(phones).as("Список номеров пуст").isNotEmpty();
    }
}

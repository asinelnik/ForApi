import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import services.BaseTest;
import steps.GetEmptyPhone;

import java.util.List;

public class GetEmptyPhonesUserTest {
    BaseTest baseTest = new BaseTest();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test
    public void getEmptyPhone() {
        baseTest.forSpecification();
        getEmptyPhone.responseGetEmptyPhone();
    }
}

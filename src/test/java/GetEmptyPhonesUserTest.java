import org.testng.annotations.Test;
import services.BaseTest;
import steps.GetEmptyPhone;

public class GetEmptyPhonesUserTest {
    BaseTest baseTest = new BaseTest();
    GetEmptyPhone getEmptyPhone = new GetEmptyPhone();

    @Test
    public void getEmptyPhoneNumberUserAwait() {
        baseTest.forSpecification();
        getEmptyPhone.getEmptyPhoneNumberUser();
    }
}
import Service.BaseTest;
import Service.Specifications;

public class PostCustomer {

    BaseTest baseTest = new BaseTest();
    String URL = "http://localhost:8080/";

    public void createCustomer(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());

    }
}

package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class Sellers_stepDefinitions {


    @Given("get all sellers with api path {string} and parameters: isArchived {string} , page {int}, size {int}")
    public void get_all_sellers_with_api_path_and_parameters_is_archived_page_size(String path, String isArchived, Integer page, Integer size) {
        Map<String,Object> params = new HashMap<>();
        params.put("isArchived",  isArchived);
        params.put("page", page);
        params.put("size",size);

        APIRunner.runGET( path , params);

    }


    @Then("user gets all phone numbers and validate is not null")
    public void user_gets_all_phone_numbers_and_validate_is_not_null() {


        int sizeOfArray = APIRunner.getCustomResponse().getResponses().length ;

        for (int i=0; i < sizeOfArray; i++){
            System.out.println("============::::::TEST STARTED::::::::::====================");
            System.out.println(  "Sellers phone number: " + APIRunner.getCustomResponse().getResponses()[i].getPhone_number());
            Assert.assertNotNull(  APIRunner.getCustomResponse().getResponses()[i].getPhone_number() );
            System.out.println("============::::::TEST PASSED::::::::::====================");
        }


    }


    Faker faker = new Faker();

    RequestBody requestBody = new RequestBody();
    static String path = "/api/myaccount/sellers";
    static int seller_id;
    static String seller_name;
    static String phone_number;
    static String address;

    @Given("I create all variables in class level, create seller and get seller_id")
    public void iCreateAllVariablesInClassLevelCreateSellerAndGetSeller_id() {

        requestBody.setCompany_name( faker.company().name() );
        requestBody.setSeller_name( faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress() );
        requestBody.setPhone_number( faker.phoneNumber().cellPhone() );
        requestBody.setAddress( faker.address().fullAddress() );

        APIRunner.runPOST(path,requestBody);

        System.out.println("CREATED: ");
        System.out.println("name = "  + APIRunner.getCustomResponse().getSeller_name());
        System.out.println("email = "  + APIRunner.getCustomResponse().getEmail());


        seller_id = APIRunner.getCustomResponse().getSeller_id();
        seller_name = APIRunner.getCustomResponse().getSeller_name();
        phone_number = APIRunner.getCustomResponse().getPhone_number();
        address = APIRunner.getCustomResponse().getAddress();
    }

    @Then("I get same seller by id and validate")
    public void iGetSameSellerByIdAndValidate() {
        APIRunner.runGET(path + "/" + seller_id);

        int actualSellerID = APIRunner.getCustomResponse().getSeller_id();

        Assert.assertNotEquals(0,seller_id);
        Assert.assertEquals(seller_id, actualSellerID);
        Assert.assertEquals(seller_name, APIRunner.getCustomResponse().getSeller_name());
        Assert.assertEquals(phone_number, APIRunner.getCustomResponse().getPhone_number());
        Assert.assertEquals(address, APIRunner.getCustomResponse().getAddress());

        System.out.println("GET: ");
        System.out.println("name = "  + APIRunner.getCustomResponse().getSeller_name());
        System.out.println("email = "  + APIRunner.getCustomResponse().getEmail());

    }
}

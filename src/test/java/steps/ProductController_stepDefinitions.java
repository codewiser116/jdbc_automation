package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.APIRunner;

public class ProductController_stepDefinitions {

    // declare your variable in class level
    static String product_id ="";
    static String product_title="";
    static double product_price;
    static String product_description="";

    static String apiPath ="";


    /**
         * {
         *     "product_title": "Toyota1",
         *     "product_price": 100000,
         *     "service_type_id": 2,
         *     "category_id": 805,
         *     "product_description": "Car"
         * }
     */

    @Given("use RequestBody and create new product {string}")
    public void useRequestBodyAndCreateNewProduct(String path) {
        System.out.println( path );

    }

    @When("get same product by id and validate data")
    public void getSameProductByIdAndValidateData() {


    }


    @Then("delete same product by id")
    public void delete_same_product_by_id() {

    }



}

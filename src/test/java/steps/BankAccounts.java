package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.RequestBody;
import utilities.APIRunner;

public class BankAccounts {

    static String path = "/api/myaccount/bankaccount";
    static String id;
    static String bankAccountName;
    RequestBody requestBody = new RequestBody();


    @Given("Create {string} and  {string} and {string} and {double}")
    public void createAndAndAnd(String bankAccountName, String description, String typeOfPay, double balance) {
    requestBody.setType_of_pay( typeOfPay );
    requestBody.setBank_account_name( bankAccountName );
    requestBody.setDescription( description );
   //  requestBody.setBalance(  Double.parseDouble( balance )  );
    requestBody.setBalance(   balance  );

        APIRunner.runPOST(path,  requestBody );
        id = APIRunner.getCustomResponse().getId();
        System.out.println("=======TEST PASSED===================");


    }



    @And("Get created Bank account by id and check status code is {int}")
    public void getCreatedBankAccountByIdAndCheckStatusCodeIs(int expectedStatusCode) {


    }


    @Then("Get same Bank account by id and delete")
    public void getSameBankAccountByIdAndDelete() {

    }



}

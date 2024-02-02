package day_5_recap_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //  STEP-1  It will run by ascending order
public class BankAccountPractice {
    Faker faker = new Faker();  //  STEP-2
    static String bankId= ""; //  STEP-2


    @Test
    public void test_1_createNewBankAccount() throws JsonProcessingException {
      //   getToken()   import static utilities.CashwiseAuthorization.getToken;  STEP-3

        // https://backend.cashwise.us   /api/myaccount/bankaccount    // STEP -==> set up your URL
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        /*
        Serialization ==> Java request body => JSON object
        RequestBody only for POST or PUT or PATCH
         */
        RequestBody requestBody = new RequestBody(); // STEP-4  go inside RequestBody class and declare your variables
        // STEP - 5 ==> set all RequestBody
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(  faker.company().name()+ " bank"  );
        requestBody.setDescription( faker.commerce().department()+ " company" );
        requestBody.setBalance( faker.number().numberBetween(200, 15000)  );

        /**
         * {
         *     "type_of_pay": "BANK",
         *     "bank_account_name": " Featurea bank03",
         *     "description": "Finacial company",
         *     "balance": 150090
         * }
         */

        // STEP - 6  ==> Hit api with RestAssured (POST)
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()  )
                .contentType( ContentType.JSON ) // Specify contentType() only for===> POST,PUT, PATCH
                .body( requestBody )  // Specify body() only for===> POST,PUT, PATCH
                .post( url );

        // STEP - 7  ==> Print out status code and make sure u have right status code (POST ==> 201)
        System.out.println("My status code: "+   response.statusCode() );
        // STEP - 8  ==> Assert your status code
        Assert.assertEquals("Status code is NOT correct",201,   response.statusCode() );

        // STEP - 9  ==> Print response body and make sure u created new BankAccount
        System.out.println( "=====RESPONSE BODY===============================");
        response.prettyPrint();


        System.out.println( "=====Use ObjectMapper and Get id ======DESERIALIZATION=========================");
        // STEP - 10  Use ObjectMapper to Read data from Response body
        ObjectMapper mapper = new ObjectMapper();

        // STEP - 11  Go inside CustomResponse class and specify your variables you want Read(Fetch data)
        CustomResponse customResponse = mapper.readValue(  response.asString(), CustomResponse.class  );

        /**  MY variables from CustomResponse class (Basically those are variables I want to Assert)
         *      private String id;
         *     private String bank_account_name;
         *     private double balance;
         */

        bankId = customResponse.getId();

        System.out.println( "My ID: " + bankId );


    }




}

package day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;


import java.util.HashMap;
import java.util.Map;

public class BankAccount {
    /**
     * Create new bank account
     * 1) I have to get token
     */



    @Test // Junit annotation
    public void test_1_tokenGenerator(){
        String  url ="https://backend.cashwise.us/api/myaccount/auth/login";

        String requestBody = "{\n" +
                "    \"email\": \"codewise_batch4_api@gmail.com\",\n" +
                "    \"password\": \"123456\"\n" +
                "}";

        Response response = RestAssured.given()
                .contentType( ContentType.JSON )
                .body(  requestBody  )
                .post(  url  );

        System.out.println( "Status code: " +   response.statusCode());

        response.prettyPrint();
        // jwt_token
        String token = response.jsonPath().getString( "jwt_token" );
        System.out.println( "My token: "+  token );


    }




    @Test
    public void test_2_createNewBankAccount(){

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb2Rld2lzZV9iYXRjaDRfYXBpQGdtYWlsLmNvbSIsImV4cCI6MTcwNzE4MjczOSwiaWF0IjoxNzA2NTc3OTM5fQ.3nn3yNK_Kxyr386igm4t3ZWYmr7Mh_hHX-0zDJgASSjndstEIykJ_3MaaonXNBbG7EdUqPYcUMVsuJzaN6550w";
        String url ="https://backend.cashwise.us/api/myaccount/bankaccount";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "BANK" );
        requestBody.put("bank_account_name", "Intellij Bank" );
        requestBody.put("description", "Financial company");
        requestBody.put("balance", 905 );

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .contentType( ContentType.JSON )
                .body(  requestBody )
                .post( url );

        System.out.println(response.statusCode());


    }

    /**
     * Get all banks data and Assert value is Not Null
     */
    @Test
    public void test_3_getListOfBankAccount(){
        String token = CashwiseAuthorization.getToken();
        //  https://backend.cashwise.us    /api/myaccount/bankaccount
        String url = Config.getProperty("baseUrl") +"/api/myaccount/bankaccount";

//        System.out.println( token );
//        System.out.println( url );

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .get(  url );

        System.out.println("My status code: "+response.statusCode());

        // response.prettyPrint();

        int sizeOfBankAccounts = response.jsonPath().getList("").size();
        System.out.println(sizeOfBankAccounts);

        for (int i=0; i < sizeOfBankAccounts; i++ ) {
            System.out.println("==========================================================");
            String id = response.jsonPath().getString("[" +i + "].id");
            String bankAccountName = response.jsonPath().getString("[" +i+  "].bank_account_name");
            String description = response.jsonPath().getString("["+i+"].description");
            String typeOfPay = response.jsonPath().getString("[" +i+"].type_of_pay");
            String balance = response.jsonPath().getString("["+i+"].balance");

            System.out.println("Bank id: " + id);
            System.out.println("Bank account name: " + bankAccountName);
            System.out.println("Bank description: " + description);
            System.out.println("Type of pay: " + typeOfPay);
            System.out.println("Balance: " + balance);


            Assert.assertNotNull( "Id is null" , id);
            Assert.assertNotNull( "Bank account is null" , bankAccountName);
            Assert.assertNotNull( "Description is null" , description);
            Assert.assertNotNull( "Type of pay is null" , typeOfPay);
            Assert.assertNotNull( "Balance is null" , balance);

            System.out.println("==========================================================");
        }


    }


    @Test
    public void test_4_getSingleBankAccount(){
        String url = Config.getProperty("baseUrl")+ "/api/myaccount/bankaccount/893";
        String token = CashwiseAuthorization.getToken();


        Response response = RestAssured.given()
                .auth().oauth2(  token  )
                .get( url );

        response.prettyPrint();

        // x.bank_account_name
        System.out.println("========TEST STARTED =========================");

        String bankAccountName = response.jsonPath().getString("bank_account_name");
        Assert.assertNotNull(bankAccountName);
        System.out.println(bankAccountName);

        System.out.println("========TEST PASSED =========================");




    }





}

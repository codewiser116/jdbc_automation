package day_6_api_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class GetSingleData {
    /** Get single bank account
     * Create Object mapper
     * Create CustomResponse
     * GET bankID in class level
     *  https://backend.cashwise.us   /api/myaccount/bankaccount/  1202
     */

    String bankID = "";


    @Test
    public void test_1_getSingleBankAccount() throws JsonProcessingException {
        bankID = "1202";
        System.out.println("====REGULAR method==============================");
        //Step - 1   // baseUrl                            path
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankID;

        //Step - 2 Hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .get( url );

        //response.prettyPrint();

        //Step - 3 create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //Step - 4 create CustomResponse class and Handle exception
        CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );

        System.out.println(  "My ID: "+  customResponse.getId()  );
        System.out.println(  "Bank account Name: "+  customResponse.getBank_account_name()  );
        System.out.println(  "Balance: "+  customResponse.getBalance() );


    }

    @Test
    public void test_2_getSingleBankAccount(){
        // https://backend.cashwise.us  /api/myaccount/bankaccount/1202
        System.out.println("===========APIRunner =================================================");
        String path = "/api/myaccount/bankaccount/1202";

        APIRunner.runGET(  path );

        String bankId =  APIRunner.getCustomResponse().getId();
        String bankAccountName = APIRunner.getCustomResponse().getBank_account_name();
        double balance = APIRunner.getCustomResponse().getBalance() ;

        System.out.println(  "My ID: "+  bankId  );
        System.out.println(  "Bank account Name: "+  bankAccountName  );
        System.out.println(  "Balance: "+  balance);


    }


    /** TASK
     *  Get single Seller
     * Create Object mapper
     * Create CustomResponse
     * GET seller_id
     *      seller_name
     *  https://backend.cashwise.us   /api/myaccount/sellers/3465
     */

    @Test
    public void test_3_getSingleSeller() throws JsonProcessingException {
        // https://backend.cashwise.us   /api/myaccount/sellers/3465
        String url  = Config.getProperty("baseUrl")+  "/api/myaccount/sellers/3465" ;

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .get(  url );

        ObjectMapper mapper = new ObjectMapper( );

        CustomResponse customResponse = mapper.readValue(  response.asString(), CustomResponse.class );

        System.out.println( "Seller id: "+  customResponse.getSeller_id()  );
        System.out.println(  "Seller name: "+  customResponse.getSeller_name()  );

        /**
         *     private int  seller_id;
         *     private String seller_name;
         *
         */

    }




  /*  @Test
    public void exampleCustomMethod(){
        int a = 1;
        int b = 4;

        int c ;
        c =  a +b;

        System.out.println( "Concatenation: " + c );

        System.out.println("======Custom Method==============");
        System.out.println(  "Concatenation: " +   APIRunner.concatTwoNums(a, b));

    }

   */



}

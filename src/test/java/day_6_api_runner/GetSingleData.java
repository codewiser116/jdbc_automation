package day_6_api_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
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

        //Step - 1
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankID;

        //Step - 2 Hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .get( url );

        // response.prettyPrint();

        //Step - 3 create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //Step - 4 create CustomResponse class and Handle exception
        CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );

        System.out.println(  "My ID: "+  customResponse.getId()  );



    }



}

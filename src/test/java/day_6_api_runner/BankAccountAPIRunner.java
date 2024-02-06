package day_6_api_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class BankAccountAPIRunner {

    @Test
    public void test_1_getListOfBankAccounts() throws JsonProcessingException {
        // https://backend.cashwise.us  /api/myaccount/bankaccount

        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        //  response.prettyPrint();
        ObjectMapper mapper = new ObjectMapper();

        CustomResponse[] customResponses = mapper.readValue(response.asString(), CustomResponse[].class);

        int sizeOfBankAccounts = customResponses.length;

        for (int i = 0; i < sizeOfBankAccounts; i++) {

            System.out.println("Bank ID" + customResponses[i].getId());
            Assert.assertNotNull(customResponses[i].getId());
            System.out.println("Bank account name: " + customResponses[i].getBank_account_name());
            Assert.assertNotNull(customResponses[i].getBank_account_name());

        }
    }


        @Test
        public void test_2_getListOfBankAccounts_ApiRunner(){
            String path = "/api/myaccount/bankaccount";
            APIRunner.runGET(path );
           // APIRunner.getCustomResponse()[0].


        }









}

package day_7_method_overloading_practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class PaymentPractice {

    @Test
    public void test_1_getSinglePayment() throws JsonProcessingException {
        // https://backend.cashwise.us   /api/myaccount/payments/213

        String url = Config.getProperty("baseUrl")+ "/api/myaccount/payments/213" ;

        Response response = RestAssured.given()
                .auth().oauth2(   getToken() )
                .get(url);

       //  response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("Invoice title: "  +   customResponse.getResponse().getInvoice_title()  );
        System.out.println( "Total pay: "  +  customResponse.getResponse().getTotal_pay()  );

        System.out.println("Category ID: " +    customResponse.getCategory_response()[0].getCategory_id());

        System.out.println( "Category title: "  +  customResponse.getCategory_response()[0].getCategory_title());


    }


}

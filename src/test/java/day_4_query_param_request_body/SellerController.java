package day_4_query_param_request_body;

import com.github.javafaker.Faker;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;


public class SellerController {
    Faker faker = new Faker();

    @Test
    public void test_1_createNewSeller(){
        // https://backend.cashwise.us/api/myaccount/sellers
        String url= Config.getProperty("baseUrl") + "/api/myaccount/sellers";

        Map<String , Object> requestBody = new HashMap<>();
        requestBody.put("company_name",  faker.company().name() );
        requestBody.put("seller_name" , faker.name().fullName() );
        requestBody.put("email" , faker.internet().emailAddress() );
        requestBody.put("phone_number" , faker.phoneNumber().cellPhone());
        requestBody.put("address" , faker.address().fullAddress());


       /*
                   {
                "company_name": "Apple1 inc",
                "seller_name": "Steve",
                "email": "jaysonq46@gmail.com",
                "phone_number": "1233454567",
                "address": "Monaco"
            }
        */

        Response response = RestAssured.given()
                .auth().oauth2(  getToken()  )
                .contentType(ContentType.JSON)
                .body(  requestBody )
                .post( url );

        Assert.assertEquals("Status code is not correct", 200, response.statusCode() );


    }


}

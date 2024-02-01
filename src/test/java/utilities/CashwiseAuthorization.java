package utilities;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class CashwiseAuthorization {

    private static String token; // Encapsulation
    public static String getToken(){

        String url = "https://backend.cashwise.us/api/myaccount/auth/login";
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(  Config.getProperty("email")   );
        requestBody.setPassword(  Config.getProperty("password")   );

        /* Map<String, Object> requestBody = new HashMap<>();
         requestBody.put("email",      Config.getProperty("email")   );
         requestBody.put("password", Config.getProperty("password"));

         */
         Response response = RestAssured.given()
                .contentType(ContentType.JSON).
                body(requestBody)
                .post(url);

        token = response.jsonPath().getString( "jwt_token" );
        return token;


        // response.prettyPrint();
        // jwt_token
        // System.out.println( "My token: "+  token );
    }


}

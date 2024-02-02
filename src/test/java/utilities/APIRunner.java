package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import pojo.CustomResponse;

import static utilities.CashwiseAuthorization.getToken;

public class APIRunner {

    @Getter
    private static CustomResponse customResponse ;


    /** Day_5 APIRunner (Description about this class)
     * APIRunner class contains custom methods for CRUD commands
     * With help of this class we can focus on test logic, instead of automation
     * script
     */

    public static CustomResponse runGET( String path  ) {

        String url = Config.getProperty("baseUrl") +  path;

        Response response = RestAssured.given()
                .auth().oauth2(   getToken()  )
                .get(  url );

        ObjectMapper mapper = new ObjectMapper();


                try {
                    customResponse = mapper.readValue(response.asString(), CustomResponse.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }


        return customResponse;

    }

//    public static CustomResponse getCustomResponse() {
//        return customResponse;
//    }


}

package day_7_method_overloading_practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class SellerIsArchived {

    @Test
    public void test_1_gerAllisArchivedSellers(){
        //  https://backend.cashwise.us   /api/myaccount/sellers    ?isArchived=false  &page=1&   size=10
        String url = Config.getProperty("baseUrl")+ "/api/myaccount/sellers";

        Map<String , Object> params = new HashMap<>();
        params.put( "isArchived", false );
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given()
                .auth().oauth2(  getToken()  )
                .contentType(ContentType.JSON)
                .params(  params  )
                .get(  url );


        response.prettyPrint();








    }


}

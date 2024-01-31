package Homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class SellerController {

    @Test
    public void getListOfSellers(){
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl")+"/api/myaccount/sellers/all";

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        response.prettyPrint();
        int sizeOfSellers = response.jsonPath().getList("").size();
        System.out.println("Size of sellers: " + sizeOfSellers);

        for(int i =0; i<sizeOfSellers; i++){
            String seller_id = response.jsonPath().getString("[" +i + "].id");
            String company_name = response.jsonPath().getString("[" +i+  "].company_name");

            String seller_name = response.jsonPath().getString("["+i+"].seller_name");




        }


    }
    @Test
    public void get_one_seller(){
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl")+"/api/myaccount/sellers/3188";

        Response response = RestAssured.given().auth().oauth2(token).get(url);

        String sellerId = response.jsonPath().getString("seller_id" );
        String companyName = response.jsonPath().getString("company_name");
        String sellerName = response.jsonPath().getString("seller_name");

        System.out.println("Seller id: " + sellerId);
        System.out.println("Company name: " + companyName);
        System.out.println("Seller name: " + sellerName);

        Assert.assertNotNull( "SellerId is null" , sellerId);

        Assert.assertNotNull( "Company name is null" , companyName);

        Assert.assertNotNull( "Seller name is null" , sellerName);



    }

}
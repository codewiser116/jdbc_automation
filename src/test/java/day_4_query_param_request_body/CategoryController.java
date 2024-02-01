package day_4_query_param_request_body;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryController {
    Faker faker = new Faker();

    static String category_id ="";


    @Test
    public void test_1_createNewCategory(){
       String url = Config.getProperty("baseUrl") + "/api/myaccount/categories";

       String category = faker.commerce().department();
       String description = category+ " company";


        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title(  category);
        requestBody.setCategory_description(  description  );
        requestBody.setFlag(false);


        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );

        response.prettyPrint();

        category_id = response.jsonPath().getString("category_id");

    }


    @Test
    public void test_2_getSingleCategory(){

        // https://backend.cashwise.us   /api/myaccount/categories/    883

        String url = Config.getProperty("baseUrl") + "/api/myaccount/categories/"+category_id;

       Response response = RestAssured.given()
               .auth().oauth2( getToken() )
               .get( url  );

        System.out.println("Status code: "+ response.statusCode());


    }





}

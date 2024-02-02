package day_4_query_param_request_body;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import Homework.CategoryPOJO;
import pojo.CustomResponse;
import pojo.RequestBody;
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
    public void test_2_getSingleCategory() throws JsonProcessingException {

        // https://backend.cashwise.us   /api/myaccount/categories/    883

        String url = Config.getProperty("baseUrl") + "/api/myaccount/categories/"+category_id;

       Response response = RestAssured.given()
               .auth().oauth2( getToken() )
               .get( url  );

        System.out.println("Status code: "+ response.statusCode());

        System.out.println("  =======  Get data by jsonPath ======================  ");
        System.out.println(   response.jsonPath().getString("category_id") );
        System.out.println(   response.jsonPath().getString("category_title") );
        System.out.println(   response.jsonPath().getString("category_description") );

        System.out.println("  ========= Get data by CustomResponse ====================================  ");
        // PLS ==>>  import com.fasterxml.jackson.databind.ObjectMapper;

        // Deserialization Json ==>  Java
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(  response.asString() ,  CustomResponse.class  );



        System.out.println(   customResponse.getCategory_id()     );
        System.out.println(   customResponse.getCategory_title()  );
        System.out.println(   customResponse.getCategory_description()   );

        System.out.println("=========TEST STARTED===========================");
            Assert.assertNotNull(   customResponse.getCategory_id()     );
            Assert.assertNotNull(   customResponse.getCategory_title()    );
            Assert.assertNotNull(  customResponse.getCategory_description()   );
        System.out.println("=========TEST PASSED===========================");



        System.out.println("=========DEDICATED CATEGORY Class==========================");
        CategoryPOJO categoryPOJO = mapper.readValue(  response.asString(), CategoryPOJO.class );

        System.out.println(  categoryPOJO.getCategory_id()  );
        System.out.println(  categoryPOJO.getCategory_title()  );
        System.out.println(  categoryPOJO.getCategory_description()  );

    }





}

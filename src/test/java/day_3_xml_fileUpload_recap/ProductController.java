package day_3_xml_fileUpload_recap;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductController {

    String token = CashwiseAuthorization.getToken();

    /** PRACTICE:
     * Generate new product and get ID
     * Use Faker()  to generate data
     */
    @Test
    public void test_1_createNewProduct(){
        // https://backend.cashwise.us  /api/myaccount/products

        String url = Config.getProperty("baseUrl")+ "/api/myaccount/products";
        //  token = CashwiseAuthorization.getToken();


        Faker faker = new Faker();

        String productTitle = faker.commerce().productName();
        double productPrice = faker.number().numberBetween(100, 5600);
        int serviceTypeID = 2;
        int categoryId = 805;
        String description = productTitle + " company";


        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("product_title", productTitle   );
        requestBody.put("product_price", productPrice );
        requestBody.put("service_type_id", serviceTypeID);
        requestBody.put("category_id", categoryId);
        requestBody.put("product_description", description);

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .contentType( ContentType.JSON )
                .body(  requestBody  )
                .post(  url );

        response.prettyPrint();

        String  productID = response.jsonPath().getString("product_id");


        System.out.println("================ TASK =====================================");
        /** TASK
         *  Get exact product you generated =>  by id
         *  Use GER REQUEST
         *  1) with jsonPath() get => productTitle  ==EX:  String  actualProductTitle = response.jsonPath().getString("product_title");
         *  2) with jsonPath() get => productPrice
         *  3) with jsonPath() get => description
         * 4)   Assert.assertEquals("Product Title is not correct ",productTitle,actualProductTitle );
         */

        //   https://backend.cashwise.us        /api/myaccount/products/        796
        url =  Config.getProperty("baseUrl") + "/api/myaccount/products/" + productID;

         response = RestAssured.given()
                 .auth().oauth2(token)
                 .get( url );



        String actualProductTitle = response.jsonPath().getString("product_title");
        String actualProductPrice = response.jsonPath().getString("product_price");
        String actualProductDescription = response.jsonPath().getString("product_description");

        System.out.println("==========TEST STARTED===============");
        Assert.assertEquals("Product Title is not correct",  productTitle,            actualProductTitle);
        Assert.assertEquals("Product Price is not correct", productPrice+"",  actualProductPrice);
        Assert.assertEquals("Product Description is not correct",description,  actualProductDescription);
        System.out.println("==========TEST PASSED!===============");

    }



}

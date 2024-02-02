package day_5_recap_pojo;
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
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;


import com.github.javafaker.Faker;
import org.junit.Test;

import static utilities.CashwiseAuthorization.getToken;

public class SellersPractice {
    /** TASK
     * CREATE NEW SELLER
     *  GET seller_id
     */
    Faker faker = new Faker();

    static String  seller_id ;


    /**
     * This test used by DataGenerator.sellerGenerator() class
     */
    @Test
    public void test_1_createSeller() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers" ;

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name( faker.company().name() );
        requestBody.setSeller_name( faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress() );
        requestBody.setPhone_number( faker.phoneNumber().cellPhone() );
        requestBody.setAddress( faker.address().fullAddress() );

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .contentType(ContentType.JSON)
                .body(requestBody )
                .post(url);

        response.prettyPrint();

        System.out.println("======================================");


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse =mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println( "Seller id is: "+ customResponse.getSeller_id() );


         seller_id =  customResponse.getSeller_id() + "" ;
       // seller_id =  customResponse.getSeller_id() ;
                /*
                Validate those:
                    private int seller_id;
                    private String seller_name;
                    private String phone_number;
                    private String address;

                 */

    }


}

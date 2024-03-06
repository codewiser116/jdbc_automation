package day_1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReqresInTest {

    @Test
    public void test_1_getSingleUser(){

        String url = "https://reqres.in/api/users/2";

        Response response = RestAssured.get( url );

        int statusCode = response.statusCode();

        System.out.println( "Status code: "+ statusCode  );
        response.prettyPrint();

        System.out.println("=====GET RESPONSE DATA =====================");

        String first_name = response.jsonPath().getString("data.first_name");
        String last_name =  response.jsonPath().getString("data.last_name");
        String email = response.jsonPath().getString("data.email");
        String id = response.jsonPath().getString("data.id");
        System.out.println("======== ASSERT DATA ==================================");
        // message will appear when our Assertion fails
        Assert.assertNotNull("First name is empty: ",first_name);
        Assert.assertNotNull("Lat name is empty: ",last_name);
        Assert.assertNotNull("Email  is empty: ",email);
        Assert.assertNotNull("ID is empty: ",id);
        System.out.println("==============:::::::: TEST PASSED! ::::::::::====================");
    }


    @Test
    public void test_2_getListOfUsers(){
       // String url = "https://reqres.in/api/users?page=2";

        String url = "https://reqres.in/api/users";

        Map<String, Object> params = new HashMap<>();
        params.put("page", 2);

        Response response = RestAssured.given().params(params).get( url );

        System.out.println("Status code: "+    response.statusCode() );

        response.prettyPrint();

        //
        int sizeListOfUsers = response.jsonPath().getList("data").size();
        System.out.println("Size of data: "+sizeListOfUsers);

        for (int i=0; i<sizeListOfUsers ; i++){
            System.out.println(response.jsonPath().getString("data[" + i + "].first_name"));

        }


    }

    @Test
    public void test_3_createUsers(){

        String url = "https://reqres.in/api/users";

        String requestBody = "{\n" +
                "    \"name\": \"Begimai\",\n" +
                "    \"job\": \"QA automation engineer\"\n" +
                "}";

        Response response = RestAssured.given()
                            .contentType( ContentType.JSON ) // make sure you gave content type
                            .body(  requestBody )
                            .post(url); //

        System.out.println("Status code: "+ response.statusCode() );

       // response.prettyPrint();



    }







}

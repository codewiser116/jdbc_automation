package day_3_xml_fileUpload_recap;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class XmlResponseValidation {
    static String id = "";

    @Test
    public void test_1_createTraveller(){
        String url = "http://restapi.adequateshop.com/api/Traveler";
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String address = faker.address().fullAddress();

        String requestBody ="<?xml version=\"1.0\"?>\n" +
                "<Travelerinformation>\n" +
                "    <name>"+firstName+"</name>\n" +
                "    <email>"+email+"</email>\n" +
                "    <adderes>"+address+"</adderes>\n" +
                "</Travelerinformation>";


        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody)
                .post(url);

        // response.prettyPrint();
        // x.Travelerinformation.id

        id = response.xmlPath().getString("Travelerinformation.id");

    }

    @Test
    public void test_2_getTraveller(){
        String url = "http://restapi.adequateshop.com/api/Traveler/"+ id;

        /**
         * TASK USE GET REQUEST AND pretty print response
         *
         * Task 2
         *   <name>Boyd</name>
         *   <email>trisha.lubowitz@yahoo.com</email>
         *    Assert.assertNotNull(   name );
         *    Assert.assertNotNull(   email );
         */

        Response response = RestAssured.get(url);

        String name = response.xmlPath().getString("Travelerinformation.name");
        String email = response.xmlPath().getString("Travelerinformation.email");

        System.out.println("Name from XML data: "+ name);
        System.out.println("Email from XML data: "+ email);

        Assert.assertNotNull( name );
        Assert.assertNotNull( email );

       // response.prettyPrint();
    }

    @Test
    public void test_3_getListOfTravellers(){

        /**TASK
         *  GET list of Travellers
         *  Find path to Traveller index num:0
         *  Read data and print out : id , name, email
         */


        String url ="http://restapi.adequateshop.com/api/Traveler";

        Response response = RestAssured.get(url);

        System.out.println( "Status code: " +  response.statusCode());



        int sizeOfList = response.xmlPath().getList("TravelerinformationResponse.travelers.Travelerinformation").size();


    for (int i=0; i<sizeOfList; i++) {
        System.out.println("=========TEST STARTED====================");
        System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].id"));
        Assert.assertNotNull(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].id"));

        System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].name"));
        Assert.assertNotNull(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].name"));

        System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].email"));
        Assert.assertNotNull(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].email"));

        System.out.println("=========:::::::TEST PASSED:::::====================");

    }

    }




}

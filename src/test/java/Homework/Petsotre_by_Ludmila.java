package Homework;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
public class Petsotre_by_Ludmila {

    @Test
    public void test_1_createPet() {
        String url = "https://petstore.swagger.io/v2/pet";

        Faker faker = new Faker();
        String name = faker.dog().name();

        String string = "myDogsUrl" ;



        String requestBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Pet>\n" +
                "    <id>0</id>\n" +
                "    <Category>\n" +
                "        <id>0</id>\n" +
                "        <name>Dog</name>\n" +
                "    </Category>\n" +
                "    <name> "+name+ "</name>\n" +
                "    <photoUrls>\n" +
                "        <photoUrl>"+string+"</photoUrl>\n" +
                "    </photoUrls>\n" +
                "    <tags>\n" +
                "        <Tag>\n" +
                "            <id>0</id>\n" +
                "            <name>string</name>\n" +
                "        </Tag>\n" +
                "    </tags>\n" +
                "    <status>available</status>\n" +
                "</Pet>";

        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody)
                .post(url);

        response.prettyPrint();

}}

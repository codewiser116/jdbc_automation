package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.junit.Assert;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.Config;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClientSteps {


    @Given("new client is created using API")
    public void new_client_is_created_using_api() {

        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setCompany_name(faker.company().name());
        requestBody.setClient_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().fullAddress());
        requestBody.setTags_id(new int []{50});

        APIRunner.runPOST("/api/myaccount/clients", requestBody);
        System.out.println(APIRunner.getCustomResponse().getClient_name());
    }


    @Then("verify client exists in database")
    public void verify_client_exists_in_database() throws SQLException {

        String sqlQuery = "select client_id, company_name, client_name, email, phone_number, address from clients where client_id = " + APIRunner.getCustomResponse().getClient_id();

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        CustomResponse response = APIRunner.getCustomResponse();

        while(resultSet.next()){

            Assert.assertEquals(response.getClient_id(), resultSet.getInt("client_id"));
            Assert.assertEquals(response.getCompany_name(), resultSet.getString("company_name"));
            Assert.assertEquals(response.getEmail(), resultSet.getString("email"));
            Assert.assertEquals(response.getClient_name(), resultSet.getString("client_name"));

        }

    }

    @Then("delete client in database")
    public void delete_client_in_database() throws SQLException {
        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        int client_id = APIRunner.getCustomResponse().getClient_id();

        String query1 = "delete from client_tag where client_id = " + client_id;
        String query2 = "delete from clients where client_id = " + client_id;

        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
    }


    @Then("verify client does not exist using API")
    public void verify_client_does_not_exist_using_api() {
        // HOME ASSIGNMENT

        int client_id = APIRunner.getCustomResponse().getClient_id();

        // RUN API GET REQUEST TO GET CLIENT ID
        // 404
    }



}

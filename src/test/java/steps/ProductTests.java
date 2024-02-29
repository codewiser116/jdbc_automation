package steps;

import io.cucumber.java.en.*;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTests {

    private static Connection connection;
    private static ResultSet resultSet;

    @Given("I set up connection to database")
    public void i_set_up_connection_to_database() throws SQLException {
        connection = DBUtilities.getConnection();
    }

    @Given("I retrieve all product prices")
    public void i_retrieve_all_product_prices() throws SQLException {
        String query = "select id, price, title from products where price < ? or price > ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 10000);

        resultSet = preparedStatement.executeQuery();
    }

    @Then("verify prices are between {int} and {int}")
    public void verify_prices_are_between_and(Integer lowerLimit, Integer upperLimit) throws SQLException {

        while (resultSet.next()){
//            long price = resultSet.getLong("price");
//
//            if (price < lowerLimit || price > upperLimit){
                System.out.println("ERROR: the price is out of limit for \t id: " + resultSet.getInt("id")
                + " \t price: " + resultSet.getLong("price") + " \t\t title: " + resultSet.getString("title"));
//            }
        }
    }

}

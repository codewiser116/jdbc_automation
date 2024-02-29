package database_tests;

import org.junit.Test;
import utilities.DBUtilities;

import java.sql.*;

public class PreparedStatementPractice {

    public static void main(String[] args) throws SQLException {
//        printClients1("Google");
        printClients2("Codewise");
    }


 /*
    print client_name of all clients whose company_name = "any company"
  */
    public static void printClients1(String companyName) throws SQLException {
        Connection connection = DBUtilities.getConnection();
        String query = "select client_name from clients where company_name = '" + companyName + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }

    public static void printClients2(String companyName) throws SQLException {
        Connection connection = DBUtilities.getConnection();
        String query = "select client_name from clients where company_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, companyName);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }




}




package database_tests;

import org.junit.Assert;

import java.sql.*;

public class IntroToJDBC {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try{
            // 1. Creates a connection with database
            connection = DriverManager.getConnection(url, username, password);

            //==================    OPTION 1  ====================================
            // 2. Create STATEMENT
            statement = connection.createStatement();
            String sqlQuery = "select * from clients where client_name = \\John Doe\\;";

            // 3. Execute query and store results
            resultSet = statement.executeQuery(sqlQuery);
//            int numberOfRecords = 0;

//            //===================    OPTION 2  ===============================
//
//            // Step 3: Create a PreparedStatement
//            String sql = "select * from clients where client_name = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            // Step 4: Set parameters
//            preparedStatement.setString(1, "John Doe");
//
//            preparedStatement.executeQuery();



            while (resultSet.next()){

                Assert.assertEquals(3867, resultSet.getInt(1));


//                numberOfRecords++;

//                System.out.println(resultSet.getString("address"));
//                System.out.println(resultSet.getString("client_name"));
//
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getString(2));

                // verify client_id column doesn't have null values

//                Assert.assertNotNull(resultSet.getString("client_id"));


//                Assert.assertNotNull(resultSet.getString("client_id") + " is missing client_name",
//                        resultSet.getString("client_name"));



            }

//            Assert.assertEquals(3867, numberOfRecords);
//
            // verify the number of records = 3867


        }catch (SQLException e){
            e.printStackTrace();
        }



    }

}

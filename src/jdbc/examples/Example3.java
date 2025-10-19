/**
 * Example3.java
 * Connects to a database using JDBC and retrieves records from the 'students' table.
 * createStatement() and executeQuery() methods are used to fetch data.
 * ResultSet() hold the data retrieved from the database after executing the query.
 * ResultSet.next() moves the cursor to the next row of the result set.
 */


package jdbc.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example3
{
    public static void main(String[] args)
    {
        Properties props = new Properties();
        String filePath = "resources" + File.separator + "config.properties";
        try (FileInputStream fis = new FileInputStream(filePath))
        {
            // Load the properties file
            props.load(fis);
        }
        catch (IOException e)
        {
            System.out.println("Could not read config file: " + e.getMessage());
            return;
        }

        // Get values from properties file
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String sql = "SELECT id, name, age FROM students";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age")
                );
            }

        }
        catch (SQLException e)
        {
            System.out.println("Query failed: " + e.getMessage());
        }
    }
}

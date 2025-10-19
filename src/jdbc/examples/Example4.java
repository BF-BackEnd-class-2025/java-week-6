/**
 * Example4.java
 * Connects to a database using JDBC and updates records in the 'students' table.
 * Uses createStatement() and executeUpdate() methods to perform the update operation.
 * Handles exceptions and prints success or failure messages.
 */


package jdbc.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example4
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

        String sql = "UPDATE students SET age = 23 WHERE name = 'Alice'";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement())
        {

            int rowsUpdated = stmt.executeUpdate(sql);
            System.out.println("✅ Rows updated: " + rowsUpdated);

        }
        catch (SQLException e)
        {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
    }
}

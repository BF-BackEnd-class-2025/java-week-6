/**
 * Example2.java
 * Connects to a database using JDBC and inserts a new record into the 'students' table.
 * Database connection parameters are read from an external properties file.
 * Write sql to insert a new student with name, age, and email.
 * Handles exceptions and prints success or failure messages.
 * executeUpdate() used for INSERT , UPDATE, DELETE statements.
 */



package jdbc.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example2
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

        String sql = "INSERT INTO students (name, age, email) VALUES ('Alice', 21, 'alice@example.com')";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement())
        {

            int rowsInserted = stmt.executeUpdate(sql);
            System.out.println("✅ Rows inserted: " + rowsInserted);

        } catch (SQLException e)
        {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }
}

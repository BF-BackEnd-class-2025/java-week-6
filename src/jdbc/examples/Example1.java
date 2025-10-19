/**
 * Example1.java
 * Try to connect to a database using JDBC with connection parameters
 * read from a properties file.
 * The properties file should contain:
 * db.url=jdbc:your_database_url
 * db.user=your_username
 * db.password=your_password
 * Make sure to replace the placeholders with actual values.
 * Ensure the JDBC driver for your database is included in the classpath.
 */


package jdbc.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example1
{
    public static void main(String[] args)
    { Properties props = new Properties();
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


        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            System.out.println("Connected successfully to the database!");
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}

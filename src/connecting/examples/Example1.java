/**
 * Example1.java
 * Get database connection parameters from a properties file
 * and establish a connection using JDBC.
 * Handle exceptions for file reading and SQL connection.
 */


package connecting.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example1
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

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            System.out.println("✅ Connection successful!");
        }
        catch (SQLException e)
        {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}

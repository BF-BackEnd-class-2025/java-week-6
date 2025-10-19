/**
 * Example2.java
 * Using config.properties to get DB connection parameters
 * and connect via JDBC with exception handling.
 */

package connecting.examples;

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
            props.load(fis);
        }
        catch (IOException e)
        {
            System.out.println("⚠️ Failed to read configuration: " + e.getMessage());
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            System.out.println("✅ Connected using config file!");
        }
        catch (SQLException e)
        {
            System.out.println("❌ Database connection failed: " + e.getMessage());
        }
    }
}

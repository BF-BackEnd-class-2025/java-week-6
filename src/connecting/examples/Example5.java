/**
 * Example5.java
 * Inserting Data Using PreparedStatement with Configuration File
 * This example demonstrates how to insert data into a database
 * using JDBC and prepared statements. It reads database connection
 * parameters from a properties file and inserts a new student record
 * into the "students" table.
 * Error handling with detailed messages
 * e.getMessage() for description
 * e.getSQLState() for SQL state code
 * e.getErrorCode() for vendor-specific error code
 */

package connecting.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example5
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

        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "Daniel");
            ps.setInt(2, 24);
            ps.setString(3, "daniel@example.com");

            int rowsInserted = ps.executeUpdate();
            System.out.println("✅ Rows inserted: " + rowsInserted);

        }
        catch (SQLException e)
        {
            System.out.println("❌ Database Error!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
        }
    }
}

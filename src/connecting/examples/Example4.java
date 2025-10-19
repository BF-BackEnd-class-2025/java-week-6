/**
 * Example 4:
 * Inserting Data Using PreparedStatement with Configuration File
 * This example demonstrates how to insert data into a database
 * using JDBC and prepared statements. It reads database connection
 * parameters from a properties file and inserts a new student record
 * into the "students" table.
 * Uses `PreparedStatement` with placeholders (`?`)
 * Prevents SQL injection
 * Uses parameter binding (`ps.setString`, `ps.setInt`)
 * `executeUpdate()` returns how many rows were inserted
 */

package connecting.examples;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Example4
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
             PreparedStatement ps = conn.prepareStatement(sql))
        {

            ps.setString(1, "Daniel");
            ps.setInt(2, 24);
            ps.setString(3, "daniel@example.com");

            int rowsInserted = ps.executeUpdate();
            System.out.println("✅ Rows inserted: " + rowsInserted);

        }
        catch (SQLException e)
        {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }
}

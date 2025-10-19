package statements.examples;

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
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql))
        {

            ps.setString(1, "Maya");
            ps.setInt(2, 21);
            ps.setString(3, "maya@example.com");

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows inserted: " + rows);
        }
        catch (SQLException e)
        {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }
}



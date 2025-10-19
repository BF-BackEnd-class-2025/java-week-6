package crud.examples;

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
        String sql = "UPDATE students SET email = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "updated_email@example.com");
            ps.setInt(2, 1); // update student with id = 1

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows updated: " + rows);
        }
        catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
    }
}

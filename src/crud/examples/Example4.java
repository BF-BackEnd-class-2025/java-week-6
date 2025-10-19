package crud.examples;

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
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, 2); // delete student with id = 2
            int rows = ps.executeUpdate();
            System.out.println("✅ Rows deleted: " + rows);
        }
        catch (SQLException e)
        {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
    }
}

package jdbc.examples;

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

        String sql = "DELETE FROM students WHERE name = 'Bob'";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement())
        {

            int rowsDeleted = stmt.executeUpdate(sql);
            System.out.println("✅ Rows deleted: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
    }
}








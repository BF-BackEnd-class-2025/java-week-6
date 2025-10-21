package statements.examples;

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

        String insert1 = "INSERT INTO students (name, age, email) VALUES ('Lina', 20, 'lina@example.com')";
        String insert2 = "INSERT INTO students (name, age, email) VALUES ('Omar', 23, 'omar@example.com')";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement())
        {

            conn.setAutoCommit(false); // Start transaction

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2);

            conn.commit(); // Commit both
            System.out.println("✅ Transaction committed successfully!");

        }
        catch (SQLException e)
        {
            System.out.println("❌ Transaction failed: " + e.getMessage());
        }
    }
}

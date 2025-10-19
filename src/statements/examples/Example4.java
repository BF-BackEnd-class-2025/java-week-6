package statements.examples;

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

        String insert1 = "INSERT INTO students (name, age, email) VALUES ('Sara', 22, 'sara@example.com')";
        String insert2 = "INSERT INTO invalid_table (name) VALUES ('ERROR')"; // intentional error

        try (Connection conn = DriverManager.getConnection( url, user, password );
             Statement stmt = conn.createStatement())
        {

            conn.setAutoCommit(false); // start transaction

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2); // this will fail

            conn.commit(); // won't reach this line
        }
        catch (SQLException e)
        {
            System.out.println("⚠️ Error detected, rolling back changes...");
            try (Connection conn = DriverManager.getConnection (url, user, password ))
            {
                conn.rollback();
                System.out.println("🔄 Rollback complete.");
            }
            catch (SQLException ex) {
                System.out.println("❌ Rollback failed: " + ex.getMessage());
            }
        }
    }
}

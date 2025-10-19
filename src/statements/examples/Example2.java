package statements.examples;

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
        String sql = "SELECT * FROM students WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/school_db",
                "postgres",
                "yourpassword");
             PreparedStatement ps = conn.prepareStatement(sql);
             Scanner scanner = new Scanner(System.in))
        {

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("🎓 Found: " + rs.getString("name") + ", " + rs.getInt("age"));
            }
            else
            {
                System.out.println("❌ No student found with that name.");
            }

        }
        catch (SQLException e) {
            System.out.println("⚠️ SQL Error: " + e.getMessage());
        }
    }
}

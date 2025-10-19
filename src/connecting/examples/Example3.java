/**
 * Example 3:
 * Secure User Input with Prepared Statements
 * This example demonstrates how to securely handle user input
 * when querying a database using JDBC and prepared statements.
 * It reads database connection parameters from a properties file,
 * prompts the user for a student name, and safely queries the database
 * to find the student.
 *
 * Uses `PreparedStatement` with placeholders (`?`)
 * Prevents SQL injection
 * Queries student by name safely
 */

package connecting.examples;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Example3
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

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            String sql = "SELECT * FROM students WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("🎓 Found student: " + rs.getString("name"));
            }
            else
            {
                System.out.println("❌ Student not found.");
            }

        }
        catch (SQLException e)
        {
            System.out.println("⚠️ SQL error: " + e.getMessage());
        }
    }
}

/**
 * TODO:
 * 1. Connect to your database.
 * 2. Retrieve all students using SELECT * FROM students.
 * 3. Print each student’s info in table format.
 */

package crud.exercises;

import java.io.*;
import java.util.*;
import java.sql.*;

public class Exercise2
{
    public static void main(String[] args)
    {
        Properties props = new Properties();

        String filePath = "resources" + File.separator + "config.properties";
        try( FileInputStream fin = new FileInputStream(filePath) )
        {
            props.load(fin);
        }
        catch (IOException e)
        {
            System.out.println("Error reading properties : " + e.getMessage());
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            String sqlText = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sqlText);
            while (res.next())
            {
                int id = res.getInt("id");
                String name = res.getString("name");
                int age = res.getInt("age");
                String email = res.getString("email");
                System.out.printf("ID: %d | Name: %s | Age: %d | Email: %s\n", id, name, age, email);

            }
        }
        catch (SQLException e)
        {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

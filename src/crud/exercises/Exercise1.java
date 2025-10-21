/**
 * TODO:
 * 1. Connect to your database.
 * 2. Insert a new student (name, age, email).
 * 3. Print "Student added successfully!" if successful.
 */

package crud.exercises;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Exercise1
{
    public static void main(String[] args)
    {
        Properties  props = new Properties();

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
            String sqlText = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlText);
            ps.setString(1, "John Doe");
            ps.setInt(2, 20);
            ps.setString(3, "john@gmail.com");
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        }
        catch (SQLException e)
        {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}




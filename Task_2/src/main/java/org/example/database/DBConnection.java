package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_management_system"; // Update DB name if needed
    private static final String USER = "root"; // Change this to your MySQL username
    private static final String PASSWORD = ""; // Change this to your MySQL password

    // Get a database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
        }
        return connection;
    }

    // Close the connection (Optional Utility Method)
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database Connection Closed.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close the connection.");
            System.out.println(e);
        }
    }
}

package org.example.dao;

import org.example.database.DBConnection;
import org.example.models.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    private final Connection connection;

    public StaffDAO(Connection connection) {
        this.connection = connection;
    }
    // Add a staff member
    public void addStaff(Staff staff) {

            String sql = "INSERT INTO staff (name, role, salary) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, staff.getName());
                stmt.setString(2, staff.getRole());
                stmt.setDouble(3, staff.getSalary());
                stmt.executeUpdate();
            }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Get all staff members
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();

            String sql = "SELECT * FROM staff";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    double salary = rs.getDouble("salary");
                    staffList.add(new Staff(id, name, role, salary));
                }
            }
        catch (SQLException e) {
            System.out.println(e);
        }
        return staffList;
    }
}

package org.example.dao;

import org.example.database.DBConnection;
import org.example.models.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private final Connection connection;

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void scheduleAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, doctor_name, appointment_date, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2,appointment.getDoctorId());
            stmt.setString(3, appointment.getDoctorName());
            stmt.setDate(4, Date.valueOf(appointment.getAppointmentDate()));
            stmt.setString(5, appointment.getStatus());

            stmt.executeUpdate();
            System.out.println("Appointment scheduled successfully!");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("doctor_name"),
                        rs.getDate("appointment_date").toLocalDate(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return appointments;
    }
}

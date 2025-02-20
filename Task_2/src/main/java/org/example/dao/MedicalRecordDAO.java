package org.example.dao;

import org.example.models.MedicalRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {


    private final Connection connection;

    public MedicalRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMedicalRecord(MedicalRecord record) {
        String sql = "INSERT INTO medicalrecord (patient_id, diagnosis, treatment, medications, record_date, doctor_notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, record.getPatientId());
            stmt.setString(2, record.getDiagnosis());
            stmt.setString(3, record.getTreatment());
            stmt.setString(4, record.getMedications());
            stmt.setDate(5, Date.valueOf(record.getRecordDate()));
            stmt.setString(6, record.getDoctorNotes());
            stmt.executeUpdate();
            System.out.println("Medical record added successfully.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<MedicalRecord> getMedicalRecordsByPatientId(int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medicalrecord WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String diagnosis = rs.getString("diagnosis");
                    String treatment = rs.getString("treatment");
                    String medications = rs.getString("medications");
                    Date recordDate = rs.getDate("record_date");
                    String doctorNotes = rs.getString("doctor_notes");
                    records.add(new MedicalRecord(id, patientId, diagnosis, treatment, medications, recordDate.toLocalDate(), doctorNotes));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;
    }
}

package org.example.models;

import java.time.LocalDate;

public class MedicalRecord {
    private int id;
    private int patientId;
    private String diagnosis;
    private String treatment;
    private String medications;
    private LocalDate recordDate;
    private String doctorNotes;

    public MedicalRecord(int id, int patientId, String diagnosis, String treatment, String medications, LocalDate recordDate, String doctorNotes) {
        this.id = id;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medications = medications;
        this.recordDate = recordDate;
        this.doctorNotes = doctorNotes;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getMedications() {
        return medications;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" + "id=" + id + ", patientId=" + patientId + ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' + ", medications='" + medications + '\'' +
                ", recordDate=" + recordDate + ", doctorNotes='" + doctorNotes + '\'' + '}';
    }
}

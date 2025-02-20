package org.example.models;

import java.time.LocalDate;

public class Billing {
    private int id;
    private int patientId;
    private double totalAmount;
    private LocalDate billDate;
    private String status;

    public Billing(int id, int patientId, double totalAmount, LocalDate billDate, String status) {
        this.id = id;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

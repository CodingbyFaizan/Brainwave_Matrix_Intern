package org.example.dao;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.database.DBConnection;
import org.example.models.Billing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    private final Connection connection;

    public BillingDAO(Connection connection) {
        this.connection = connection;
    }

        public Billing getBillById(int billId) {
            Billing bill = null;

                String sql = "SELECT * FROM billing WHERE id = ?";

                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, billId);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            // Extract bill details from result set
                            int id = rs.getInt("id");
                            int patientId = rs.getInt("patient_id");
                            double totalAmount = rs.getDouble("total_amount");
                            Date billDate = rs.getDate("bill_date");
                            String status = rs.getString("status");

                            // Create a Billing object
                            bill = new Billing(id, patientId, totalAmount, billDate.toLocalDate(), status);
                        }
                    }

            } catch (SQLException e) {
                System.out.println(e);
            }
            return bill;
        }


    public void addBill(Billing bill) {

            String sql = "INSERT INTO billing (patient_id, total_amount, bill_date, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, bill.getPatientId());
                stmt.setDouble(2, bill.getTotalAmount());
                stmt.setDate(3, Date.valueOf(bill.getBillDate()));
                stmt.setString(4, bill.getStatus());
                stmt.executeUpdate();
            }
        catch (SQLException e) {
            System.out.println(e);
        }
    }


    public List<Billing> getAllBills() {
        List<Billing> bills = new ArrayList<>();

            String sql = "SELECT * FROM billing";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int patientId = rs.getInt("patient_id");
                    double totalAmount = rs.getDouble("total_amount");
                    Date billDate = rs.getDate("bill_date");
                    String status = rs.getString("status");
                    bills.add(new Billing(id, patientId, totalAmount, billDate.toLocalDate(), status));
                }
            }
        catch (SQLException e) {
            System.out.println(e);
        }
        return bills;
    }


    public void generateInvoice(Billing bill) {
        Document document = new Document();
        try {
            // Define output PDF file path
            String filePath = "invoice_" + bill.getId() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open the doc for writing ....
            document.open();

            // hospital details
            document.add(new Paragraph("===== XYZ Hospital ====="));
            document.add(new Paragraph("Address: 12, Healthcare St, Mumbai, India"));
            document.add(new Paragraph("Phone: +91 997 686 890"));
            document.add(new Paragraph("\n"));

            // Invoice header
            document.add(new Paragraph("Invoice ID: " + bill.getId()));
            document.add(new Paragraph("Patient ID: " + bill.getPatientId()));
            document.add(new Paragraph("Date: " + bill.getBillDate().toString()));
            document.add(new Paragraph("\n"));

            // Billing Info
            document.add(new Paragraph("Total Amount: $" + bill.getTotalAmount()));
            document.add(new Paragraph("Status: " + bill.getStatus()));
            document.add(new Paragraph("\n"));

            // End the document
            document.close();

            System.out.println("Invoice generated successfully: " + filePath);
        } catch (DocumentException | IOException e) {
            System.out.println(e);
        }
    }

    // Generate PDF invoices for all bills
    public void generateAllInvoices() {
        List<Billing> bills = getAllBills();
        for (Billing bill : bills) {
            generateInvoice(bill);
        }
    }
}

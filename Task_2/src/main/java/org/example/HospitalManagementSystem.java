package org.example;

import org.example.dao.*;
import org.example.database.DBConnection;
import org.example.models.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DBConnection.getConnection();

        // DAO instances
        AppointmentDAO appointmentDAO= new AppointmentDAO(conn);
        PatientDAO patientDAO = new PatientDAO(conn);
        MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO(conn);
        InventoryDAO inventoryDAO = new InventoryDAO(conn);
        BillingDAO billingDAO = new BillingDAO(conn);
        StaffDAO staffDAO = new StaffDAO(conn);

        while (true) {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("1. Register Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View All Appointment");
            System.out.println("5. Manage EHR");
            System.out.println("6. Manage Inventory");
            System.out.println("7. Generate Bill");
            System.out.println("8. Generate Invoice");
            System.out.println("9. Generate All Invoice");
            System.out.println("10. Manage Staff");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    Patient newPatient = new Patient(0, name, age, gender, phone, address);
                    patientDAO.addPatient(newPatient);
                    System.out.println("Patient registered successfully.");
                    break;

                case 2:
                    List<Patient> patients = patientDAO.getAllPatients();
                    for (Patient patient : patients) {
                        System.out.println("ID: " + patient.getId() + ", Name: " + patient.getName());
                    }
                    break;

                case 3:
//                    System.out.println("Enter id: ");
//                    int id = scanner.nextInt();
                    System.out.println("Enter Patient id: ");
                    int patientId = scanner.nextInt();
                    System.out.println("Enter Doctor id: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.println("Enter Appointment Date in (YYYY-MM-DD): ");
                    String dateInput =scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateInput);
                    System.out.println("Enter Status: ");
                    String status = scanner.nextLine();
                    Appointment appointment = new Appointment(patientId,doctorId,doctorName,date,status);
                    appointmentDAO.scheduleAppointment(appointment);
                    break;

                case 4:
                    List<Appointment> appointments = new ArrayList<>();
                    for(Appointment x : appointmentDAO.getAllAppointments()){
                        System.out.println("id: " + x.getId() + ", patient id: " + x.getPatientId() + ", doctor id: " + x.getDoctorId()
                        + ", doctor name: " + x.getDoctorName() + ", appointment date: " + x.getAppointmentDate() + ", status: " + x.getStatus());
                    }
                    break;

                case 5:
                    manageEHR(scanner, medicalRecordDAO);
                    break;

                case 6:
                    manageInventory(scanner, inventoryDAO);
                    break;

                case 7:
                    generateBill(scanner, billingDAO);
                    break;

                case 8:
                    generateInvoice(scanner, billingDAO);
                    break;

                case 9:
                    generateAllInvoices(billingDAO);
                    break;

                case 10:
                    manageStaff(scanner, staffDAO);
                    break;

                case 11:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Manage Inventory
    private static void manageInventory(Scanner scanner, InventoryDAO inventoryDAO) {
        System.out.println("\n===== Inventory Management =====");
        System.out.println("1. Add Item to Inventory");
        System.out.println("2. View All Inventory Items");
        System.out.println("3. Go Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Item Name: ");
                String itemName = scanner.nextLine();
                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();
                System.out.print("Enter Price per Unit: ");
                double pricePerUnit = scanner.nextDouble();
                scanner.nextLine();  // Consume newline

                Inventory newItem = new Inventory(0, itemName, quantity, pricePerUnit);
                inventoryDAO.addInventoryItem(newItem);
                System.out.println("Inventory item added successfully.");
                break;

            case 2:
                List<Inventory> inventoryItems = inventoryDAO.getAllInventoryItems();
                for (Inventory item : inventoryItems) {
                    System.out.println("ID: " + item.getId() + ", Name: " + item.getItemName() + ", Quantity: " + item.getQuantity() + ", Price: " + item.getPricePerUnit());
                }
                break;

            case 3:
                return;

            default:
                System.out.println("Invalid choice! Try again.");
        }
    }

    //Manage EHR
    private static void manageEHR(Scanner scanner, MedicalRecordDAO medicalRecordDAO) {
        System.out.println("\n===== Electronic Health Records (EHR) Management =====");
        System.out.println("1. Add Medical Record");
        System.out.println("2. View Medical Records");
        System.out.println("3. Go Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Patient ID: ");
                int patientId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Diagnosis: ");
                String diagnosis = scanner.nextLine();
                System.out.print("Enter Treatment: ");
                String treatment = scanner.nextLine();
                System.out.print("Enter Medications: ");
                String medications = scanner.nextLine();
                System.out.print("Enter Doctor Notes: ");
                String doctorNotes = scanner.nextLine();
                LocalDate recordDate = LocalDate.now();

                MedicalRecord newRecord = new MedicalRecord(0, patientId, diagnosis, treatment, medications, recordDate, doctorNotes);
                medicalRecordDAO.addMedicalRecord(newRecord);
                break;

            case 2:
                System.out.print("Enter Patient ID: ");
                int id = scanner.nextInt();
                List<MedicalRecord> records = medicalRecordDAO.getMedicalRecordsByPatientId(id);
                for (MedicalRecord record : records) {
                    System.out.println(record);
                }
                break;

            case 3:
                return;

            default:
                System.out.println("Invalid choice! Try again.");
        }
    }

    // Generate Bill
    private static void generateBill(Scanner scanner, BillingDAO billingDAO) {
        System.out.println("\n===== Generate Bill =====");
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter Bill Status (Paid/Unpaid): ");
        String status = scanner.nextLine();

        Billing newBill = new Billing(0, patientId, totalAmount, java.time.LocalDate.now(), status);
        billingDAO.addBill(newBill);
        System.out.println("Bill generated successfully.");
    }

    // Generate Invoice
    private static void generateInvoice(Scanner scanner, BillingDAO billingDAO) {
        System.out.println("\n===== Generate Invoice =====");
        System.out.print("Enter Bill ID: ");
        int billId = scanner.nextInt();


        Billing bill = billingDAO.getBillById(billId);

        if (bill != null) {
            // Generate the invoice using the bill details
            billingDAO.generateInvoice(bill);
            System.out.println("Invoice generated successfully.");
        } else {
            System.out.println("Invalid Bill ID. Invoice could not be generated.");
        }
    }

    private static void generateAllInvoices(BillingDAO billingDAO){
        billingDAO.generateAllInvoices();
    }

    // Manage Staff
    private static void manageStaff(Scanner scanner, StaffDAO staffDAO) {
        System.out.println("\n===== Staff Management =====");
        System.out.println("1. Add Staff");
        System.out.println("2. View All Staff");
        System.out.println("3. Go Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Staff Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Role: ");
                String role = scanner.nextLine();
                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine();

                Staff newStaff = new Staff(0, name, role, salary);
                staffDAO.addStaff(newStaff);
                System.out.println("Staff added successfully.");
                break;

            case 2:
                List<Staff> staffList = staffDAO.getAllStaff();
                for (Staff staff : staffList) {
                    System.out.println("ID: " + staff.getId() + ", Name: " + staff.getName() + ", Role: " + staff.getRole() + ", Salary: " + staff.getSalary());
                }
                break;

            case 3:
                return;

            default:
                System.out.println("Invalid choice! Try again.");
        }
    }
}

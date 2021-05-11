package main;

import office.MedicalOffice;
import person.employee.*;
import person.patient.Child;
import person.patient.Patient;

import java.sql.Timestamp;
import java.util.Scanner;

public class Menu {

    private static void main_menu(){
        System.out.println("======================\n");
        System.out.println("\tMedical Office\n");
        System.out.println("======================\n");
        System.out.println("\t\tMenu\n");
        System.out.println("1. Add a new employee to the office.\n");
        System.out.println("2. Add a new patient to the office.\n");
        System.out.println("3. Show list of employees.\n");
        System.out.println("4. Show list of patients.\n");
        System.out.println("5. Make an appointment.\n");
        System.out.println("6. Show list of appointments ordered by date and time.\n");
        System.out.println("7. Write prescription for a patient and assign it to an appointment.\n");
        System.out.println("8. Show all prescriptions for a certain patient.\n");
        System.out.println("9. Sort employees by a custom criteria.\n");
        System.out.println("10. Apply discount for children's appointments.\n");
        System.out.println("11. Add medical records for a patient.\n");
        System.out.println("12. Show medical records for every patient.\n");
        System.out.println("13. Calculate Heart Disease Risk for the patients with medical records.\n");
        System.out.println("0. Exit.\n\n");
    }
    public static void menuOutput() throws Exception {
        MedicalOffice office = MedicalOffice.getInstance();
//        Employee asistent = new Assistant("Jane", "Wilson", 27, 2500, 5, 100);
//        Employee doctor = new Doctor("John", "Carter", 39, 5000, 15, Shift.Night, Specialization.Pediatry);
//        office.addEmployee(asistent);
//        office.addEmployee(doctor);
//        Patient kid = new Child("Jamie", "Dixon", 14, "Kate", "Bruce");
//        office.addPatient(kid);


        int option;
        do {
            main_menu();
            Scanner scan = new Scanner(System.in);
            System.out.println("Pick an option: ");
            option = scan.nextInt();

            if (option == 1) {
                office.addEmployee();
                MedicalOffice.audit("Add employee", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nEmployee added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            } else if (option == 2) {
                office.addPatient();
                MedicalOffice.audit("Add patient", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nPatient added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            }else if (option == 3){
                office.showEmployees();
                MedicalOffice.audit("Show employees", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 4){
                office.showPatients();
                MedicalOffice.audit("Show patients", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 5){
                office.addAppointment();
                MedicalOffice.audit("Add appointment", new Timestamp(System.currentTimeMillis()));
                System.out.println("Appointment made successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 6){
                office.showAppointments();
                MedicalOffice.audit("Show appointments", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 7){
                office.addPrescription();
                MedicalOffice.audit("Add prescription", new Timestamp(System.currentTimeMillis()));
                System.out.println("Prescription made successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 8){
                office.showPrescriptions();
                MedicalOffice.audit("Show prescriptions", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 9){
                office.customSortEmployees();
                MedicalOffice.audit("Custom sort employees", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 10){
                office.discountForChildren();
                MedicalOffice.audit("Apply Discount", new Timestamp(System.currentTimeMillis()));
                System.out.println("50% discount applied successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 11){
                boolean check = office.addMedicalRecords();
                if(check) {
                    MedicalOffice.audit("Add medical records", new Timestamp(System.currentTimeMillis()));
                    System.out.println("Medical records added successfully! Press Any Key To Continue...");
                }else{
                    System.out.println("Patient already has records or the entered data are invalid. Press Any Key to Continue...");
                }
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 12){
                office.showMedicalRecords();
                MedicalOffice.audit("Show medical records", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 13){
                office.showHeartDiseaseRisk();
                MedicalOffice.audit("Calculate Heart Disease Risk", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option != 0) {
                System.out.println("Invalid option!");
            }
        }while (option != 0);
    }
}

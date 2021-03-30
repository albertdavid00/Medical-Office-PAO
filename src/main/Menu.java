package main;

import office.MedicalOffice;
import person.employee.*;
import person.patient.Child;
import person.patient.Patient;

import java.util.Scanner;

public class Menu {

    public static void main_menu(){
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
        System.out.println("0. Exit.\n\n");
    }
    public static void menu() throws Exception {
        MedicalOffice office = MedicalOffice.getInstance();
        Employee asistent = new Assistant("Jane", "Wilson", 27, 2500, 5, 100);
        Employee doctor = new Doctor("John", "Carter", 39, 5000, 15, Shift.Night, Specialization.Pediatry);
        office.addEmployee(asistent);
        office.addEmployee(doctor);
        Patient kid = new Child("Jamie", "Dixon", 14, "Kate", "Bruce");
        office.addPatient(kid);


        int option;
        do {
            main_menu();
            Scanner scan = new Scanner(System.in);
            System.out.println("Pick an option: ");
            option = scan.nextInt();

            if (option == 1) {
                office.addEmployee();
                System.out.println("\nEmployee added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            } else if (option == 2) {
                office.addPatient();
                System.out.println("\nPatient added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            }else if (option == 3){
                office.showEmployees();
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 4){
                office.showPatients();
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 5){
                office.addAppointment();
                System.out.println("Appointment made successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 6){
                office.showAppointments();
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 7){
                office.addPrescription();
                System.out.println("Prescription made successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 8){
                office.showPrescriptions();
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 9){
                office.customSortEmployees();
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 10){
                office.discountForChildren();
                System.out.println("50% discount applied successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option != 0) {
                System.out.println("Invalid option!");
            }
        }while (option != 0);
    }
}

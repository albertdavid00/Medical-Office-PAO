package main;

import office.MedicalOffice;

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
        System.out.println("0. Exit.\n\n");
    }
    public static void menu() throws Exception {
        MedicalOffice office = MedicalOffice.getInstance();
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
            else if (option != 0) {
                System.out.println("Invalid option!");
            }
        }while (option != 0);
    }
}

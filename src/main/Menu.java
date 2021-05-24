package main;

import config.DataSetup;
import office.MedicalOffice;
import person.employee.*;
import person.patient.Adult;
import person.patient.Child;
import person.patient.Patient;
import repository.AdultRepository;
import repository.AssistantRepository;
import repository.ChildRepository;
import repository.DoctorRepository;

import javax.xml.crypto.Data;
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
        int option;
        do {
            main_menu();
            Scanner scan = new Scanner(System.in);
            System.out.println("Pick an option: ");
            option = scan.nextInt();

            if (option == 1) {
                office.addEmployee();
                Service.audit("Add employee", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nEmployee added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            } else if (option == 2) {
                office.addPatient();
                Service.audit("Add patient", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nPatient added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            }else if (option == 3){
                office.showEmployees();
                Service.audit("Show employees", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 4){
                office.showPatients();
                Service.audit("Show patients", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 5){
                office.addAppointment();
                Service.audit("Add appointment", new Timestamp(System.currentTimeMillis()));
                System.out.println("Appointment made successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 6){
                office.showAppointments();
                Service.audit("Show appointments", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 7){
                office.addPrescription();
                Service.audit("Add prescription", new Timestamp(System.currentTimeMillis()));
                System.out.println("Prescription made successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 8){
                office.showPrescriptions();
                Service.audit("Show prescriptions", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 9){
                office.customSortEmployees();
                Service.audit("Custom sort employees", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 10){
                office.discountForChildren();
                Service.audit("Apply Discount", new Timestamp(System.currentTimeMillis()));
                System.out.println("50% discount applied successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 11){
                boolean check = office.addMedicalRecords();
                if(check) {
                    Service.audit("Add medical records", new Timestamp(System.currentTimeMillis()));
                    System.out.println("Medical records added successfully! Press Any Key To Continue...");
                }else{
                    System.out.println("Patient already has records or the entered data are invalid. Press Any Key to Continue...");
                }
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 12){
                office.showMedicalRecords();
                Service.audit("Show medical records", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 13){
                office.showHeartDiseaseRisk();
                Service.audit("Calculate Heart Disease Risk", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option != 0) {
                System.out.println("Invalid option!");
            }
        }while (option != 0);
    }
    private static void main_menu_DB(){
        System.out.println("======================\n");
        System.out.println("\tMedical Office\n");
        System.out.println("======================\n");
        System.out.println("\t\tMenu\n");
        // Doctors Table
        System.out.println("1. Show the doctors in the database.\n");
        System.out.println("2. Insert a new doctor in the database.\n");
        System.out.println("3. Find doctor by id.\n");
        System.out.println("4. Update doctor by id.\n");
        System.out.println("5. Delete doctor by id.\n\n");
        // Assistants Table
        System.out.println("6. Show assistants in the database.\n");
        System.out.println("7. Insert a new assistant in the database.\n");
        System.out.println("8. Find assistant by id\n");
        System.out.println("9. Update assistant by id.\n");
        System.out.println("10. Delete assistant by id.\n\n");
        // Adults table
        System.out.println("11. Show the adult patients in the database.\n");
        System.out.println("12. Insert a new adult in the database.\n");
        System.out.println("13. Find adult by id.\n");
        System.out.println("14. Update adult by id.\n");
        System.out.println("15. Delete adult by id.\n\n");
        // Children table
        System.out.println("16. Show the children patients in the database.\n");
        System.out.println("17. Insert a new child in the database.\n");
        System.out.println("18. Find child by id.\n");
        System.out.println("19. Update child by id.\n");
        System.out.println("20. Delete child by id.\n");

        System.out.println("0. Exit.\n\n");
    }
    public static void menuOutputDB() throws Exception {
        MedicalOffice office = MedicalOffice.getInstance();
        DataSetup datasetup = new DataSetup();
        datasetup.setUp();
        AdultRepository adRep = new AdultRepository();
        ChildRepository chRep = new ChildRepository();
        DoctorRepository docRep = new DoctorRepository();
        AssistantRepository assRep = new AssistantRepository();
        int option;
        do {
            main_menu_DB();
            Scanner scan = new Scanner(System.in);
            System.out.println("Pick an option: ");
            option = scan.nextInt();

            if (option == 1) {
                datasetup.displayDoctor();
//                Service.audit("Show doctors to DB", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            }
            else if (option == 2) {
                docRep.insertDoctor(Doctor.read());
                //Service.audit("Insert doctor", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nDoctor added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();

            }
            else if (option == 3){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                System.out.println(docRep.getDoctorById(id));
                //Service.audit("Find doctor", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 4){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Pick an attribute to update: firstName / lastName / Age" +
                                "/ Salary / Experience / Shift / Specialization");
                String attribute = scan.nextLine();
                System.out.println("Enter new value: ");
                String value = scan.nextLine();
                if(attribute.equalsIgnoreCase("firstname")){
                    docRep.updateDoctorFirstName(value, id);
                }else if (attribute.equalsIgnoreCase("lastname")){
                    docRep.updateDoctorLastName(value, id);
                }else if (attribute.equalsIgnoreCase("age")){
                    docRep.updateDoctorAge(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("salary")){
                    docRep.updateDoctorSalary(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("experience")){
                    docRep.updateDoctorYearsOfExperience(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("shift")){
                    value = value.substring(0, 1).toUpperCase() + value.substring(1);
                    Shift shift = Shift.valueOf(value);
                    docRep.updateDoctorShift(shift, id);
                }else if (attribute.equalsIgnoreCase("specialization")){
                    value = value.substring(0, 1).toUpperCase() + value.substring(1);
                    Specialization spec = Specialization.valueOf(value);
                    docRep.updateDoctorSpecialization(spec, id);
                }else {
                    System.out.println("Invalid attribute...");
                }
                //Service.audit("UPDATE DOCTOR", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 5){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                if(docRep.getDoctorById(id) != null) {
                    docRep.DeleteDoctor(id);
                    System.out.println("Doctor deleted successfully.");
                }else{
                    System.out.println("There is no doctor with such id...");
                }
                //Service.audit("Delete doctor", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 6){
                datasetup.displayAssistant();
                //Service.audit("Show assistants", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 7){

                assRep.insertAssistant(Assistant.read());
                //Service.audit("Insert assistants", new Timestamp(System.currentTimeMillis()));
                System.out.println("Assistant added successfully! Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 8){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                System.out.println(assRep.getAssistantById(id));
                //Service.audit("Find Assistant", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 9){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Pick an attribute to update: firstName / lastName / Age" +
                        "/ Salary / Experience / Bonus");
                String attribute = scan.nextLine();
                System.out.println("Enter new value: ");
                String value = scan.nextLine();
                if(attribute.equalsIgnoreCase("firstname")){
                    assRep.updateAssistantFirstName(value, id);
                }else if (attribute.equalsIgnoreCase("lastname")){
                    assRep.updateAssistantLastName(value, id);
                }else if (attribute.equalsIgnoreCase("age")){
                    assRep.updateAssistantAge(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("salary")){
                    assRep.updateAssistantSalary(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("experience")){
                    assRep.updateAssistantYearsOfExperience(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("bonus")){
                    assRep.updateAssistantBonus(Integer.parseInt(value), id);
                }else {
                    System.out.println("Invalid attribute...");
                }
                //Service.audit("UPDATE Assistant", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }

            else if (option == 10){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                if(assRep.getAssistantById(id) != null) {
                    assRep.DeleteAssistant(id);
                    System.out.println("Assistant deleted successfully.");
                }else{
                    System.out.println("There is no assistant with such id...");
                }
                //Service.audit("DELETE Assistant", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if(option == 11){
                datasetup.displayAdult();
//                Service.audit("Show doctors to DB", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 12) {
                adRep.insertAdult(Adult.read());
                //Service.audit("Insert adult", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nAdult patient added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 13){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                System.out.println(adRep.getAdultById(id));
                //Service.audit("Find doctor", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 14){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Pick an attribute to update: firstName / lastName / Age / PhoneNumber");
                String attribute = scan.nextLine();
                System.out.println("Enter new value: ");
                String value = scan.nextLine();
                if(attribute.equalsIgnoreCase("firstname")){
                    adRep.updateAdultFirstName(value, id);
                }else if (attribute.equalsIgnoreCase("lastname")){
                    adRep.updateAdultLastName(value, id);
                }else if (attribute.equalsIgnoreCase("age")){
                    adRep.updateAdultAge(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("phoneNumber")){
                    adRep.updateAdultPhoneNumber(value, id);
                } else {
                    System.out.println("Invalid attribute...");
                }
                //Service.audit("UPDATE Adult", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 15){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                if(adRep.getAdultById(id) != null) {
                    adRep.DeleteAdult(id);
                    System.out.println("Adult deleted successfully.");
                }else{
                    System.out.println("There is no adult with such id...");
                }
                //Service.audit("Delete Adult", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if(option == 16){
                datasetup.displayChild();
//                Service.audit("Show children in DB", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 17) {
                chRep.insertChild(Child.read());
                //Service.audit("Insert child", new Timestamp(System.currentTimeMillis()));
                System.out.println("\nChild patient added successfully!\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 18){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                System.out.println(chRep.getChildById(id));
                //Service.audit("Find doctor", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 19){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Pick an attribute to update: firstName / lastName / Age / " +
                                "Mother / Father");
                String attribute = scan.nextLine();
                System.out.println("Enter new value: ");
                String value = scan.nextLine();
                if(attribute.toLowerCase().equals("firstname")){
                    chRep.updateChildFirstName(value, id);
                }else if (attribute.equalsIgnoreCase("lastname")){
                    chRep.updateChildLastName(value, id);
                }else if (attribute.equalsIgnoreCase("age")){
                    chRep.updateChildAge(Integer.parseInt(value), id);
                }else if (attribute.equalsIgnoreCase("mother")){
                    chRep.updateChildMotherName(value, id);
                }else if (attribute.equalsIgnoreCase("father")){
                    chRep.updateChildFatherName(value, id);
                } else {
                    System.out.println("Invalid attribute...");
                }
                //Service.audit("UPDATE Adult", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option == 20){
                System.out.println("Enter id: ");
                int id = scan.nextInt();
                if(chRep.getChildById(id) != null) {
                    chRep.DeleteChild(id);
                    System.out.println("Child deleted successfully.");
                }else{
                    System.out.println("There is no child with such id...");
                }
                //Service.audit("Delete Child", new Timestamp(System.currentTimeMillis()));
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            else if (option != 0) {
                System.out.println("Invalid option!");
            }
        }while (option != 0);
    }
}

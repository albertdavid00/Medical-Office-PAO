package office;

import person.employee.*;
import person.patient.Adult;
import person.patient.Child;
import person.patient.Patient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Singleton class

public class MedicalOffice {
    private static String officeName; //câmp de instanță
    private static MedicalOffice medicalOffice; //1
    private ArrayList<Patient> patients;
    private ArrayList<Employee> employees;


    private MedicalOffice() {

        officeName = "Catena";
        employees = new ArrayList<>();
        patients = new ArrayList<>();

    }

    public static MedicalOffice getInstance() {

        if (medicalOffice == null)

            medicalOffice = new MedicalOffice();

        return medicalOffice;

    }

    public static void showMedicalOffice(){

        System.out.println("Medical Office: " + officeName);

    }
    public Employee addEmployee(){

        Employee emp = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Doctor (1) or Assistant (2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1 || choice == 2) {

            System.out.println("Enter first name: ");
            String fName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lName = scanner.nextLine();

            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter salary: ");
            int salary = scanner.nextInt();

            System.out.println("Enter years of experience: ");
            int yrsOfExp = scanner.nextInt();
            if (choice == 1) {
                scanner.nextLine();
                System.out.println("Enter work shift (Morning / Afternoon / Night): ");
                String strShift = scanner.nextLine().toLowerCase();
                strShift = strShift.substring(0, 1).toUpperCase() + strShift.substring(1);
                Shift shift = Shift.valueOf(strShift);

                System.out.println("Enter specialization (Dermatology / Neurology / Ophtalmology / Psychiatry / Pediatry / Cardiology / Endocrinology): ");
                String strSpec = scanner.nextLine().toLowerCase();
                strSpec = strSpec.substring(0, 1).toUpperCase() + strSpec.substring(1);
                Specialization spec = Specialization.valueOf(strSpec);
                emp = new Doctor(fName, lName, age, salary, yrsOfExp, shift, spec);
                employees.add(emp);
            } else{
                System.out.println("Enter bonus income: ");
                int bonus = scanner.nextInt();

                emp = new Assistant(fName, lName, age, salary, yrsOfExp, bonus);
                employees.add(emp);
            }
        } else{
            System.out.println("Invalid choice!");
        }
        return emp;
    }

    public void showEmployees(){ System.out.println(this.employees); }

    public void showPatients(){ System.out.println(this.patients); }

    public Patient addPatient() throws Exception {
        Patient patient = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose patient: Child (1) or Adult (2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1 || choice == 2){
            System.out.println("Enter first name: ");
            String fName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lName = scanner.nextLine();

            System.out.println("Enter age: ");
            int age = scanner.nextInt();


            if(choice == 1){// Child
                while(age >= 18){
                    System.out.println("Age must be < 18! Enter valid age: ");
                    age = scanner.nextInt();
                }
                scanner.nextLine();
                System.out.println("Mother's name: ");
                String mother = scanner.nextLine();
                System.out.println("Father's name: ");
                String father = scanner.nextLine();
                patient = new Child(fName, lName, age, mother, father);
                patients.add(patient);
            }else{
                while(age < 18){
                    System.out.println("Age must be at least 18! Enter valid age: ");
                    age = scanner.nextInt();
                }
                scanner.nextLine();
                System.out.println("Phone number: ");
                String phone = scanner.nextLine();
                patient = new Adult(fName, lName, age, phone);
                patients.add(patient);
            }
        } else{
            System.out.println("Invalid choice!");
        }
        return patient;
    }

}

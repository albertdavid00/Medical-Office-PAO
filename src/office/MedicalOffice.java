package office;

import appointment.Appointment;
import person.employee.*;
import person.patient.Adult;
import person.patient.Child;
import person.patient.Patient;
import prescription.Prescription;

import java.lang.reflect.Array;
import java.util.*;

// Singleton class

public class MedicalOffice {
    private static String officeName; // câmp de instanță
    private static MedicalOffice medicalOffice; //1
    private ArrayList<Patient> patients;
    private ArrayList<Employee> employees;
    private Map<Appointment, Prescription> appointmentsAndPrescriptions;


    private MedicalOffice() {

        officeName = "Catena";
        employees = new ArrayList<>();
        patients = new ArrayList<>();
        appointmentsAndPrescriptions = new HashMap<>();

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

    public void showEmployees(){
        int numberOfEmployees = this.employees.size();
        if(numberOfEmployees == 0){
            System.out.println("The medical office has no employees.");
            return;
        }
        for(int i = 0; i < numberOfEmployees; i++){
            System.out.println((i+1) + ". " + this.employees.get(i) + "\n");
        }
    }

    public void showPatients(){
        int numberOfPatients = this.patients.size();
        if(numberOfPatients == 0){
            System.out.println("The medical office has no patients.");
            return;
        }
        for(int i = 0; i < numberOfPatients; i++){
            System.out.println((i+1) + ". " + this.patients.get(i) + "\n");
        }
    }

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
    public Appointment addAppointment() throws Exception {
        Appointment appointment;
        Scanner scan = new Scanner(System.in);
        boolean invalidAppointment;
        String date, startTime, endTime;
        do {
            System.out.println("Enter date (dd/mm/yyyy): ");
            date = scan.nextLine();
            System.out.println("Enter start time (hh:mm): ");
            startTime = scan.nextLine();
            System.out.println("Enter end time (hh:mm): ");
            endTime = scan.nextLine();
            invalidAppointment = checkAppointmentOverlap(date, startTime, endTime);
            if(invalidAppointment){
                System.out.println("Appointments are overlapping. Please pick another date or time.");
            }
        } while (invalidAppointment);

        System.out.println("Assign existing patient (1) or add a new patient (2)?");
        int choice = scan.nextInt();
        scan.nextLine();
        Patient patient = null;
        if (choice == 1) {
            if (patients.size() == 0) {
                System.out.println("There are no existing patients. Please add a new one.\n");
                patient = this.addPatient();
            } else {
                this.showPatients();
                System.out.println("Select the id of the patient: ");
                int id = scan.nextInt();
                while (id - 1 > patients.size()) {
                    System.out.println("Invalid id! Enter valid id: ");
                    id = scan.nextInt();
                }
                scan.nextLine();
                patient = patients.get(id - 1);
            }
        } else if (choice == 2) {
            patient = this.addPatient();
        }

        this.showEmployees();
        System.out.println("Select the id of the employee for the appointment:");
        int EmployeeId = scan.nextInt();
        while (EmployeeId - 1 > employees.size()) {
            System.out.println("Invalid id! Enter valid id: ");
            EmployeeId = scan.nextInt();
        }
        Employee employee = employees.get(EmployeeId - 1);

        System.out.println("Enter price: ");
        Double price = scan.nextDouble();

        appointment = new Appointment(date, startTime, endTime, patient, employee, price);
        appointmentsAndPrescriptions.put(appointment, null);
        return appointment;
    }
    private boolean checkAppointmentOverlap(String date, String startTime, String endTime){
        Iterator it = appointmentsAndPrescriptions.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry) it.next();
            Appointment appointment = (Appointment) mapElement.getKey();
            String appDate, stTime, eTime;
            appDate = appointment.getDate();
            stTime = appointment.getStartTime();
            eTime = appointment.getEndTime();

            if(date.equals(appDate)){    // appointments are in the same day
                if (startTime.compareTo(stTime) <= 0){
                    if(endTime.compareTo(stTime) > 0){      // overlap
                        return true;
                    }
                }else{
                    if (startTime.compareTo(eTime) < 0){    // overlap
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

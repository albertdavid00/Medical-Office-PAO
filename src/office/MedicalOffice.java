package office;

import appointment.Appointment;
import person.employee.*;
import person.patient.Adult;
import person.patient.Child;
import person.patient.Patient;
import prescription.Diagnosis;
import prescription.Medicine;
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
        appointmentsAndPrescriptions = new TreeMap<>();

    }

    public static MedicalOffice getInstance() {

        if (medicalOffice == null)

            medicalOffice = new MedicalOffice();

        return medicalOffice;

    }

    public void addEmployee(Employee emp){
        this.employees.add(emp);
    }
    public void addPatient(Patient patient){
        this.patients.add(patient);
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
                while(!phone.matches("^0[0-9]{9}$")){
                    System.out.println("Invalid phone number. Enter a valid one: ");
                    phone = scanner.nextLine();
                }

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
                while (id - 1 >= patients.size() || id < 1) {
                    System.out.println("Invalid id! Enter valid id: ");
                    id = scan.nextInt();
                }
                scan.nextLine();
                patient = patients.get(id - 1);
            }
        } else if (choice == 2) {
            patient = this.addPatient();
        }
        System.out.println("Employees: ");
        this.showEmployees();
        System.out.println("Select the id of the employee for the appointment:");
        int EmployeeId = scan.nextInt();
        while (EmployeeId - 1 >= employees.size() || EmployeeId < 1) {
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

    public void showAppointments(){
        System.out.println("Appointments: ");
        int nrOfApps = appointmentsAndPrescriptions.size();
        int i = 1;
        for(Map.Entry<Appointment, Prescription> entry: appointmentsAndPrescriptions.entrySet()){
            System.out.println((i++) + ". " + entry.getKey());
        }
    }

    public Prescription addPrescription(){
        System.out.println("Patients: ");
        this.showPatients();
        System.out.println("Pick the id of a patient to write the prescription to:");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();

        while(id - 1 >= patients.size() || id < 1){
            System.out.println("Invalid id. Please input a valid id: ");
            id = scan.nextInt();
        }

        Patient patient = patients.get(id - 1);

        System.out.println("Pick a diagnosis for the patient (Dermatitis / CommonCold / Flu / " +
                            "Depression / Arrhythmia / Glaucoma / Seizure / Diabetes): ");
        String strDiag = scan.nextLine().toLowerCase();
        strDiag = strDiag.substring(0, 1).toUpperCase() + strDiag.substring(1);
        Diagnosis diagnosis;
        if(strDiag.equals("Commoncold")){
            diagnosis = Diagnosis.CommonCold;
        }else {
            diagnosis = Diagnosis.valueOf(strDiag);
        }

        List<Medicine> meds = prescribeMeds();
        Prescription prescription;
        this.showAppointments();

        System.out.println("Pick the appointment (id) where the prescription was made: ");
        id = scan.nextInt();

        int i = 1;
        Appointment app = null;
        Employee employee = null;

        for(Map.Entry<Appointment, Prescription> entry: appointmentsAndPrescriptions.entrySet()) {
            if(id == i){
                app = entry.getKey();
                employee = app.getEmployee();
                break;
            }
            i++;
        }
        if (app != null) {
            prescription = new Prescription(patient, employee, diagnosis, meds);
            appointmentsAndPrescriptions.put(app, prescription);
        }
        else{
            System.out.println("Invalid id...");
            return null;
        }
        return  prescription;
    }

    public List<Medicine> prescribeMeds(){
        System.out.println("Add medicine to prescription");
        Scanner scan = new Scanner(System.in);

        Medicine med;
        List<Medicine> meds = new LinkedList<>();

        String medName;
        int price, quantity;
        boolean addNewMeds = true;

        do{
            System.out.println("\tEnter name of medicine: ");
            medName = scan.nextLine();

            System.out.println("\tEnter quantity (mg): ");
            quantity = scan.nextInt();

            System.out.println("\tEnter price: ");
            price = scan.nextInt();

            med = new Medicine(medName, price, quantity);
            meds.add(med);

            System.out.println("Enter new medicine? Yes(1) / No(2): ");
            int option = scan.nextInt();

            if(option == 1){
                scan.nextLine();
            }else{
                addNewMeds = false;
            }
        }while (addNewMeds);

        return meds;
    }

    public void showPrescriptions(){
        this.showPatients();
        System.out.println("Pick the patient(id): ");

        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();

        while (id - 1 >= patients.size() || id < 1) {
            System.out.println("Invalid id! Enter valid id: ");
            id = scan.nextInt();
        }

        Patient patient = patients.get(id - 1);
        List<Prescription> prescList = new LinkedList<Prescription>();
        for(Map.Entry<Appointment, Prescription> entry: appointmentsAndPrescriptions.entrySet()){
            Prescription prescription = entry.getValue();
            if(prescription.getPatient().equals(patient)){
                prescList.add(prescription);
            }
        }
        if (prescList.isEmpty()){
            System.out.println("The patient has no prescriptions.");
        }else{
            for(int i = 0; i<prescList.size(); i++){
                System.out.println((i+1) + ". " + prescList.get(i));
            }
        }
    }

    public void customSortEmployees(){
        System.out.println("Choose criteria:\n\t1. Annual Income\n\t2. Years of experience");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if(choice == 1){
            employees.sort((e1, e2) -> (int) (e2.annualIncome() - e1.annualIncome()));
        }
        else {
            employees.sort((e1, e2) -> e2.getYearsOfExperience() - e1.getYearsOfExperience());
        }
    }

    public void discountForChildren(){
        for(Map.Entry<Appointment, Prescription> entry: appointmentsAndPrescriptions.entrySet()){
            Appointment appointment = entry.getKey();
            appointment.applyDiscount();
        }
    }
}
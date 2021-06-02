package office;

import appointment.Appointment;
import main.Service;
import person.employee.*;
import person.patient.Adult;
import person.patient.Child;
import person.patient.Patient;
import prescription.Diagnosis;
import prescription.MedicalRecords;
import prescription.Medicine;
import prescription.Prescription;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.*;

// Singleton class

public class MedicalOffice {
    private static String officeName; // câmp de instanță
    private static MedicalOffice medicalOffice; //1
    private ArrayList<Patient> patients;
    private ArrayList<Employee> employees;
    private Map<Appointment, Prescription> appointmentsAndPrescriptions;
    private ArrayList<MedicalRecords> medicalrecords;

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public Map<Appointment, Prescription> getAppointmentsAndPrescriptions() {
        return appointmentsAndPrescriptions;
    }

    public void setAppointmentsAndPrescriptions(Map<Appointment, Prescription> appointmentsAndPrescriptions) {
        this.appointmentsAndPrescriptions = appointmentsAndPrescriptions;
    }

    public ArrayList<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(ArrayList<MedicalRecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    private MedicalOffice() throws Exception {

        officeName = "Catena";
        employees = new ArrayList<>();
        patients = new ArrayList<>();
        appointmentsAndPrescriptions = new TreeMap<>();
        medicalrecords = new ArrayList<>();

        //employees.addAll(Service.reader("src/resources/doctors.csv", "doctor", this));
        employees.addAll(Doctor.getDoctors());
        employees.addAll(Assistant.getAssistants());
        //employees.addAll(Service.reader("src/resources/assistants.csv", "assistant", this));

        //patients.addAll(Service.reader("src/resources/adults.csv", "adult", this));
        //patients.addAll(Service.reader("src/resources/children.csv", "child", this));
        patients.addAll(Adult.getAdults());
        patients.addAll(Child.getChildren());
        medicalrecords.addAll(Service.reader("src/resources/medicalRecords.csv", "medicalRecords", this));
        for(MedicalRecords medRec : medicalrecords){
            Patient patient = patients.stream()
                    .filter(p -> p.getIdPatient() == medRec.getPatientId())
                    .findAny()
                    .orElse(null);
            patient.setRecords(medRec);
        }

        ArrayList<Appointment> apps = new ArrayList<>();
        apps.addAll(Service.reader("src/resources/appointments.csv", "Appointment", this));
        for(Appointment app : apps){
            appointmentsAndPrescriptions.put(app, null);
        }

    }

    public static MedicalOffice getInstance() throws Exception {

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
    public Employee addEmployee() throws IOException {  // la adaugare se va scrie si in csv-ul aferent

        Employee emp = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Doctor (1) or Assistant (2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1 || choice == 2) {

            if (choice == 1) {

                emp = Doctor.read();
                employees.add(emp);
                //Service.writer("src/resources/doctors.csv", emp);
                Doctor.writeDoctor((Doctor) emp);
            } else{

                emp = Assistant.read();
                employees.add(emp);
                //Service.writer("src/resources/assistants.csv", emp);
                Assistant.writeAssistant((Assistant) emp);
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

            if(choice == 1){// Child

                patient = Child.read();
                patients.add(patient);
                //Service.writer("src/resources/children.csv", patient);
                Child.writeChild((Child) patient);
            }else{

                patient = Adult.read();
                patients.add(patient);
                //Service.writer("src/resources/adults.csv", patient);
                Adult.writeAdult((Adult) patient);
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
        Service.writer("src/resources/appointments.csv", appointment);
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

    public Prescription addPrescription() throws IOException {
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
        scan.nextLine();
        
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

    public List<Medicine> prescribeMeds() throws IOException {
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
        for (Medicine m : meds){
            Service.writer("src/resources/meds.csv", m);
        }
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

    public boolean addMedicalRecords() throws Exception {
        Scanner scan = new Scanner(System.in);
        this.showPatients();
        System.out.println("Select the patient to write the medical records for.");
        int id = scan.nextInt();
        while (id - 1 >= patients.size() || id < 1) {
            System.out.println("Invalid id! Enter valid id: ");
            id = scan.nextInt();
        }
        Patient patient = patients.get(id - 1);
        if(patient.getRecords() != null){
            return false;
        }
        System.out.println("Enter height (cm): ");
        int height = scan.nextInt();
        System.out.println("Enter weight (kg): ");
        int weight = scan.nextInt();
        scan.nextLine();
        String gender;
        try {
            System.out.println("Enter gender (male / female): ");
            gender = scan.nextLine();
            if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"))
                throw new Exception("Invalid gender!");
        }catch (Exception e){
            System.out.println("Invalid gender");
            return false;
        }

        System.out.println("Enter systolic blood pressure (normal pressure: 90 - 120): ");
        int systolicPressure = scan.nextInt();
        System.out.println("Enter diastolic blood pressure (normal pressure: 60 - 80): ");
        int diastolicPressure = scan.nextInt();
        System.out.println("Enter average heart rate (bpm): ");
        int avgHeartRate = scan.nextInt();
        MedicalRecords medRec = new MedicalRecords(height, weight, gender, systolicPressure, diastolicPressure, avgHeartRate);
        medRec.setPatientId(patient.getIdPatient());
        patient.setRecords(medRec);
        Service.writer("src/resources/medicalRecords.csv", medRec);
        return true;
    }

    public void showMedicalRecords(){
        int id = 1;
        for(int i = 0; i < patients.size(); i++){
            Patient patient = patients.get(i);
            if (patient.getRecords() != null){
                System.out.println(id + ". " + patient + "\n\t" + patient.getRecords());
                id ++;
            }
        }
    }

    public void showHeartDiseaseRisk(){
        int id = 1;
        for(int i = 0; i < patients.size(); i++){
            Patient patient = patients.get(i);
            if (patient.getRecords() != null){
                System.out.println(id + ". " + patient.getFirstName() + " "+ patient.getLastName() + "" +
                        " status: " + patient.getType() + "\tHeart Disease Risk: " + patient.calculateHeartRisk());
                id ++;
            }
        }
    }
  /*  // metoda generica de citire din csv
    public <T> ArrayList<T> reader(String pathToCsv, String option) throws Exception {
        File csvFile = new File(pathToCsv);
        ArrayList<T> objects = new ArrayList<>();
        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
            String row;
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                if (option.toLowerCase().equals("doctor")) {
                    String fname = data[0];
                    String lname = data[1];
                    int age = Integer.parseInt(data[2]);
                    int salary = Integer.parseInt(data[3]);
                    int yrsxp = Integer.parseInt(data[4]);
                    Shift shift = Shift.valueOf(data[5]);
                    Specialization spec = Specialization.valueOf(data[6]);
                    objects.add((T) new Doctor(fname, lname, age, salary, yrsxp, shift, spec));
                }
                else if (option.toLowerCase().equals("assistant")) {
                    String fname = data[0];
                    String lname = data[1];
                    int age = Integer.parseInt(data[2]);
                    int salary = Integer.parseInt(data[3]);
                    int yrsxp = Integer.parseInt(data[4]);
                    int bonus = Integer.parseInt(data[5]);
                    objects.add((T) new Assistant(fname, lname, age, salary, yrsxp, bonus));
                }
                else if (option.toLowerCase().equals("child")) {
                    String fname = data[0];
                    String lname = data[1];
                    int age = Integer.parseInt(data[2]);
                    String nameM = data[3];
                    String nameF = data[4];
                    objects.add((T) new Child(fname, lname, age, nameM, nameF));
                }
                else if (option.toLowerCase().equals("adult")) {
                    String fname = data[0];
                    String lname = data[1];
                    int age = Integer.parseInt(data[2]);
                    String phoneNum = data[3];

                    objects.add((T) new Adult(fname, lname, age, phoneNum));
                }
                else if (option.toLowerCase().equals("medicine")) {
                    String name = data[0];
                    int price = Integer.parseInt(data[1]);;
                    int quantity = Integer.parseInt(data[2]);

                    objects.add((T) new Medicine(name, price, quantity));
                }
                else if (option.toLowerCase().equals("medicalrecords")){
                    int height = Integer.parseInt(data[0]);
                    int weight = Integer.parseInt(data[1]);
                    String gender = data[2];
                    int sysBP = Integer.parseInt(data[3]);
                    int diaBP = Integer.parseInt(data[4]);
                    int avgHR = Integer.parseInt(data[5]);
                    int pId = Integer.parseInt((data[6]));
                    MedicalRecords mr = new MedicalRecords(height, weight, gender, sysBP, diaBP, avgHR);
                    mr.setPatientId(pId);
                    objects.add((T) mr);
                }
                else if (option.toLowerCase().equals("appointment")){
                    String date = data[0];
                    String stime = data[1];
                    String etime = data[2];
                    Double price = Double.parseDouble(data[3]);
                    int patId = Integer.parseInt(data[4]);
                    int empId = Integer.parseInt(data[5]);
                    Patient patient = patients.stream()
                            .filter(p -> patId == p.getIdPatient())
                            .findAny().orElse(null);
                    Employee emp = employees.stream()
                            .filter(e -> empId == e.getIdEmployee())
                            .findAny().orElse(null);
                    Appointment app = new Appointment(date, stime, etime, patient, emp, price);
                    objects.add((T) app);
                }
            }
//            csvReader.close();
//            for (T doc : objects){
//                System.out.println(doc);
//            }
        }
        return objects;
    }*/
    // metoda generica de scriere in csv
    /*public static <T> void writer(String pathToCsv, T object) throws IOException {
        File csvFile = new File(pathToCsv);
        if (csvFile.isFile()) {
            FileWriter csvWriter = new FileWriter(pathToCsv, true);
            if(csvFile.length() == 0) {  // daca e gol
                if(object.getClass().getSimpleName().equals("Doctor")){
                    csvWriter.append("First Name,Last Name,Age,Salary,Years of Experience,Shift,Specialization\n");
                }
                else if(object.getClass().getSimpleName().equals("Assistant")){
                    csvWriter.append("First Name,Last Name,Age,Salary,Years of Experience, Bonus\n");
                }
                else if(object.getClass().getSimpleName().equals("Adult")){
                    csvWriter.append("First Name,Last Name,Age,Phone Number\n");
                }
                else if(object.getClass().getSimpleName().equals("Child")){
                    csvWriter.append("First Name,Last Name,Age,Mother's Name,Father's Name\n");
                }
                else if(object.getClass().getSimpleName().equals("Medicine")){
                    csvWriter.append("Name,Price,Quantity\n");
                }
                else if(object.getClass().getSimpleName().equals("MedicalRecords")){
                    csvWriter.append("Height,Weight,Gender,Systolic Blood Pressure, Diastolic Blood Pressure, Avg. HeartReate, PatientId\n");
                }
                else if(object.getClass().getSimpleName().equals("Appointment")){
                    csvWriter.append("Date,Start Time, End Time, Price, PatientId, EmployeeId\n");
                }
            }
            if(object.getClass().getSimpleName().equals("Doctor")){
                Doctor doc = (Doctor) object;
                csvWriter.append(doc.getFirstName() + ",");
                csvWriter.append(doc.getLastName() + ",");
                csvWriter.append(String.valueOf(doc.getAge()) + ",");
                csvWriter.append(String.valueOf(doc.getSalary()) + ",");
                csvWriter.append(String.valueOf(doc.getYearsOfExperience()) + ",");
                csvWriter.append(doc.getShift().toString() + ",");
                csvWriter.append(doc.getSpecialization().toString() + "\n");
            }
            else if(object.getClass().getSimpleName().equals("Assistant")){
                Assistant assistant = (Assistant) object;
                csvWriter.append(assistant.getFirstName() + ",");
                csvWriter.append(assistant.getLastName() + ",");
                csvWriter.append(String.valueOf(assistant.getAge()) + ",");
                csvWriter.append(String.valueOf(assistant.getSalary()) + ",");
                csvWriter.append(String.valueOf(assistant.getYearsOfExperience()) + ",");
                csvWriter.append(String.valueOf(assistant.getBonus()) + "\n");
            }
            else if(object.getClass().getSimpleName().equals("Adult")){
                Adult adult = (Adult) object;
                csvWriter.append(adult.getFirstName() + ",");
                csvWriter.append(adult.getLastName() + ",");
                csvWriter.append(String.valueOf(adult.getAge()) + ",");
                csvWriter.append(adult.getPhoneNumber() + "\n");
            }
            else if(object.getClass().getSimpleName().equals("Child")){
                Child child = (Child) object;
                csvWriter.append(child.getFirstName() + ",");
                csvWriter.append(child.getLastName() + ",");
                csvWriter.append(String.valueOf(child.getAge()) + ",");
                csvWriter.append(child.getMotherName() + ",");
                csvWriter.append((child.getFatherName()) + "\n");
            }
            else if(object.getClass().getSimpleName().equals("MedicalRecords")){
                MedicalRecords mr = (MedicalRecords) object;
                csvWriter.append(String.valueOf(mr.getHeight()) + ",");
                csvWriter.append(String.valueOf(mr.getWeight()) + ",");
                csvWriter.append(mr.getGender() + ",");
                csvWriter.append(String.valueOf(mr.getSystolicBloodPressure()) + ",");
                csvWriter.append(String.valueOf(mr.getDiastolicBloodPressure()) + ",");
                csvWriter.append(String.valueOf(mr.getAverageHeartRate()) + ",");
                csvWriter.append(String.valueOf(mr.getPatientId()) + "\n");
            }
            else if(object.getClass().getSimpleName().equals("Appointment")){
                Appointment app = (Appointment) object;
                csvWriter.append(app.getDate() + ",");
                csvWriter.append(app.getStartTime() + ",");
                csvWriter.append(app.getEndTime() + ",");
                csvWriter.append(app.getPrice() + ",");
                csvWriter.append(String.valueOf(app.getPatient().getIdPatient()) + ",");
                csvWriter.append(String.valueOf(app.getEmployee().getIdEmployee()) + "\n");
            }
            csvWriter.close();
        }
    }*/
   /* // metoda pentru audit
    public static void audit(String action, Timestamp timestamp) throws IOException {
        String pathToCsv = "src/audit.csv";
        File csvFile = new File(pathToCsv);
        FileWriter csvWriter = new FileWriter(pathToCsv, true);
        if (csvFile.isFile()) {
            if(csvFile.length() == 0) {
                csvWriter.append("Action,Timestamp\n");
            }
            csvWriter.append(action + "," + timestamp.toString() + "\n");

        }
        csvWriter.close();
    }*/
}
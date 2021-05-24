package main;

import appointment.Appointment;
import person.employee.*;
import person.patient.Adult;
import person.patient.Child;
import person.patient.Patient;
import prescription.MedicalRecords;
import prescription.Medicine;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import office.MedicalOffice;

public class Service {
    private static Service service; //1

    private Service() {}

    public static Service getPresident() { //3
        if (service == null)
            service = new Service();
        return service;

    }
    static public <T> ArrayList<T> reader(String pathToCsv, String option, MedicalOffice medicalOffice) throws Exception {
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
                    ArrayList<Patient> patients = medicalOffice.getPatients();
                    Patient patient = patients.stream()
                            .filter(p -> patId == p.getIdPatient())
                            .findAny().orElse(null);
                    Employee emp = medicalOffice.getEmployees().stream()
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
    }

    public static <T> void writer(String pathToCsv, T object) throws IOException {
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
    }

    // metoda pentru audit
    public static void audit(String action, Timestamp timestamp) throws IOException {
        String pathToCsv = "src/resources/audit.csv";
        File csvFile = new File(pathToCsv);
        FileWriter csvWriter = new FileWriter(pathToCsv, true);
        if (csvFile.isFile()) {
            if(csvFile.length() == 0) {
                csvWriter.append("Action,Timestamp\n");
            }
            csvWriter.append(action + "," + timestamp.toString() + "\n");

        }
        csvWriter.close();
    }
}

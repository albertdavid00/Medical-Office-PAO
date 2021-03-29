package main;
import appointment.Appointment;
import office.MedicalOffice;
import person.employee.Doctor;
import person.employee.Employee;
import person.employee.Shift;
import person.employee.Specialization;
import person.patient.*;
import prescription.Diagnosis;
import prescription.Medicine;
import prescription.Prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
//        Patient x = new Adult("Ion", "Popescu", 20, "0745612327");
//        try {
//            Child y = new Child("Costelus", "Pop", 19, "Mirela", "Costel");
//        }catch(Exception e){
//            System.out.println("Incorrect age");
//        }
//        Patient ana = new Adult("Ana", "Banana", 21, "n-am cartela");
//        if (2 > 1){
//            Child a = new Child("a", "b", 1, "asd", "sadsad");
//        }
//        System.out.println(Patient.getId());
//        Employee doctor = new Doctor("John", "Carter", 39, 5000, 15, Shift.Night, Specialization.Pediatry);
//        Appointment app = new Appointment("15/09/2021", "15:30", "16:15", x, doctor, 500.00);
//        int d = app.duration();
////        System.out.println(d);
////        System.out.println(app);
//        Medicine med1 = new Medicine("parasinus", 500, 100);
//        Medicine med2 = new Medicine("vitamin C", 100, 50);
//        List<Medicine> lm = new ArrayList<Medicine>();
//        lm.add(med1);
//        lm.add(med2);
//        Prescription p = new Prescription(x, doctor, Diagnosis.CommonCold, lm);
//        System.out.println(p);
//        MedicalOffice office = MedicalOffice.getInstance();
//        Patient pat = office.addPatient();
//        office.showPatients();
        Menu.menu();
    }
}

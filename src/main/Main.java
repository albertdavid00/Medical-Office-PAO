package main;
import appointment.Appointment;
import config.DataSetup;
import office.MedicalOffice;
import person.employee.Doctor;
import person.employee.Employee;
import person.employee.Shift;
import person.employee.Specialization;
import person.patient.*;
import prescription.Diagnosis;
import prescription.MedicalRecords;
import prescription.Medicine;
import prescription.Prescription;
import repository.AdultRepository;
import repository.ChildRepository;
import repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Do you wish to use database (1) or csv (2)");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if(choice == 1) {
            Menu.menuOutputDB();
        }else if(choice == 2){
            Menu.menuOutput();
        }
        else{
            System.out.println("Invalid choice!");
        }
       /* DataSetup setUpData = new DataSetup();

        AdultRepository adultRep = new AdultRepository();*/
//        Adult adult = adultRep.getAdultById(1);
//        System.out.println(adult);
//        setUpData.displayAdult();
//        Adult ad = new Adult("Ion", "Ionescu", 50, "0737995123");
//        adultRep.insertAdult(ad);
//        setUpData.displayAdult();
//        ChildRepository chRep = new ChildRepository();
//        chRep.getChildById(1);
//        DoctorRepository docRep = new DoctorRepository();
//        docRep.getDoctorById(1);
//        docRep.DeleteDoctor(1);
        //setUpData.setUp();
//        setUpData.addAdult();
        //setUpData.displayAdult();
        //setUpData.addChild();
        //setUpData.displayChild();
        //setUpData.addDoctor();
/*        setUpData.displayDoctor();
        setUpData.addAssistant();
        setUpData.displayAssistant();*/
//        Patient x = new Child("George", "Coman", 13, "0736369123", "sad");
//        x.setRecords(new MedicalRecords());
//        System.out.println(x.getRecords());
//        System.out.println(x.getType());
    }
}

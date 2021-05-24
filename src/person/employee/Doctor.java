package person.employee;

import javax.print.Doc;
import java.util.Objects;
import java.util.Scanner;

public class Doctor extends Employee{
    private Shift shift;
    private Specialization specialization;


    public Doctor(int id, String firstName, String lastName, int age, int salary, int yrsOfExp, Shift shift, Specialization specialization){
        super(firstName, lastName, age, salary, yrsOfExp);
        this.setIdEmployee(id);
        this.shift = shift;
        this.specialization = specialization;
    }
    public Doctor(String firstName, String lastName, int age, int salary, int yrsOfExp, Shift shift, Specialization specialization){
        super(firstName, lastName, age, salary, yrsOfExp);
        this.shift = shift;
        this.specialization = specialization;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Shift getShift() { return shift; }

    public void setShift(Shift shift) { this.shift = shift; }

    public int shiftBonus(){
        int bonus = 0;
        if (this.shift == Shift.Night){
            bonus = 100;
        }
        return salary + bonus;
    }

    @Override
    public Float annualIncome(){
        Float income = salary - ((float)salary / taxPercentage);
        income *= 12;
        return income;
    }

    @Override
    public String toString() {
        return super.toString() + ", shift: " + shift +
                ", specialization: " + specialization + ", status: Doctor";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return shift == doctor.shift && specialization == doctor.specialization;
    }
    public static Doctor read(){
        Scanner scanner = new Scanner(System.in);

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
        scanner.nextLine();
        System.out.println("Enter work shift (Morning / Afternoon / Night): ");
        String strShift = scanner.nextLine().toLowerCase();
        strShift = strShift.substring(0, 1).toUpperCase() + strShift.substring(1);
        Shift shift = Shift.valueOf(strShift);

        System.out.println("Enter specialization (Dermatology / Neurology / Ophtalmology / Psychiatry / Pediatry / Cardiology / Endocrinology): ");
        String strSpec = scanner.nextLine().toLowerCase();
        strSpec = strSpec.substring(0, 1).toUpperCase() + strSpec.substring(1);
        Specialization spec = Specialization.valueOf(strSpec);
        return new Doctor(fName, lName, age, salary, yrsOfExp, shift, spec);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shift, specialization);
    }
}

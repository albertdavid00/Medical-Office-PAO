package person.patient;

import person.Person;

import java.util.Scanner;

public class Child extends Patient {
    protected String motherName, fatherName;

    public Child(int id, String firstName, String lastName, int age, String motherName, String fatherName) {
        super(firstName, lastName, age);
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.setIdPatient(id);
    }

    public Child(String firstName, String lastName, int age, String motherName, String fatherName) throws Exception {
        super(firstName, lastName, age);
        this.motherName = motherName;
        this.fatherName = fatherName;
        if(age >= 18){
            setCounterPatients(getCounterPatients()-1);
            throw new Exception("Child can't be 18 years old or older");
        }
    }

    public String getMotherName() { return motherName; }

    public void setMotherName(String motherName) { this.motherName = motherName; }

    public String getFatherName() { return fatherName; }

    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public static Child read() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String fName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lName = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        while(age >= 18){
            System.out.println("Age must be < 18! Enter valid age: ");
            age = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.println("Mother's name: ");
        String mother = scanner.nextLine();
        System.out.println("Father's name: ");
        String father = scanner.nextLine();
        return new Child(fName, lName, age, mother, father);
    }
    @Override
    public String getType(){ return "Child"; }

    @Override
    public String toString() {
        return super.toString() +
                "Mother's name: '" + motherName + '\'' +
                ", Father's Name: '" + fatherName + '\'';
    }
}

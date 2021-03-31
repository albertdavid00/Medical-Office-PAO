package person.patient;

import person.Person;

import java.util.Scanner;

public class Child extends Patient {
    protected String motherName, fatherName;

    public Child(String firstName, String lastName, int age, String motherName, String fatherName) throws Exception {
        super(firstName, lastName, age);
        this.motherName = motherName;
        this.fatherName = fatherName;
        if(age >= 18){
            setId(getId()-1);
            throw new Exception("Child can't be 18 years old or older");
        }
    }

    public String getMotherName() { return motherName; }

    public void setMotherName(String motherName) { this.motherName = motherName; }

    public String getFatherName() { return fatherName; }

    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    @Override
    public String getType(){ return "Child"; }

    @Override
    public String toString() {
        return super.toString() +
                "Mother's name: '" + motherName + '\'' +
                ", Father's Name: '" + fatherName + '\'';
    }
}

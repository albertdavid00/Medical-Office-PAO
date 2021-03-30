package person.patient;

import java.util.Objects;
import java.util.Scanner;

public class Adult extends Patient{
    protected String phoneNumber;

    public Adult(String firstName, String lastName, int age, String phoneNumber) throws Exception {
        super(firstName, lastName, age);
        this.phoneNumber = phoneNumber;

        if(age < 18){
            throw new Exception("Adults have to be at least 18 years old!");
        }
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "phoneNumber: '" + phoneNumber + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Adult adult = (Adult) o;
        return Objects.equals(phoneNumber, adult.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phoneNumber);
    }

}

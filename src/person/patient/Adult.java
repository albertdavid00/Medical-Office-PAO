package person.patient;

import service.AdultCSVReaderWriter;
import service.CSVCompatible;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Adult extends Patient implements CSVCompatible {
    protected String phoneNumber;
    private static AdultCSVReaderWriter csvReaderWriter = AdultCSVReaderWriter.getInstance();

    public Adult(int id, String fname, String lname, int age, String pN) {
        super(fname, lname, age);
        this.phoneNumber = pN;
        this.setIdPatient(id);
    }
    public Adult(String firstName, String lastName, int age, String phoneNumber) {
        super(firstName, lastName, age);
        this.phoneNumber = phoneNumber;

//        if(age < 18){
//            throw new Exception("Adults have to be at least 18 years old!");
//        }
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static Adult read() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String fName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lName = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = scanner.nextInt();
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
        return new Adult(fName, lName, age, phone);
    }

    public static List<Adult> getAdults(){
        return csvReaderWriter.read();
    }

    public static void writeAdult(Adult object){
        csvReaderWriter.write(object);
    }




    @Override
    public String getType(){ return "Adult"; }

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

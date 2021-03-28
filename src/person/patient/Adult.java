package person.patient;

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

    //    @Override
//    public Adult read() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Adult's first name: ");
//        String fName = scanner.nextLine();
//        System.out.println("Adult's last name: ");
//        String lName = scanner.nextLine();
//
//        System.out.println("Adult's age (>18): ");
//        int age = scanner.nextInt();
//
//        System.out.println("Phone number: ");
//        String phone = scanner.nextLine();
//
//        Adult adult = new Adult(fName, lName, age, phone);
//        return adult;
//    }
}

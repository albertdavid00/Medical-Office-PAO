package main;
import person.patient.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Patient x = new Adult("Ion", "Popescu", 20, "0745612327");
        try {
            Child y = new Child("Costelus", "Pop", 19, "Mirela", "Costel");
        }catch(Exception e){
            System.out.println("Incorrect age");
        }
        Patient ana = new Adult("Ana", "Banana", 21, "n-am cartela");
        if (2 > 1){
            Child a = new Child("a", "b", 1, "asd", "sadsad");
        }
        System.out.println(Patient.getId());
    }
}

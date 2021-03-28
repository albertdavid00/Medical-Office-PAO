package person.patient;

public class testPatients {
    public static void main(String[] args) throws Exception {
        Patient kid = new Child("Harry", "Osborn", 13, "Natasha", "Norman");
        Patient adult = new Adult("Jackie", "Chan", 29, "0735981249");
        System.out.println(kid);
        System.out.println(adult);
    }
}

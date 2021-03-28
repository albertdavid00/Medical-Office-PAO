package person.employee;

public class testEmployees {
    public static void main(String[] args){
        Employee asistent = new Assistant("Jane", "Wilson", 27, 2500, 5, 100);
        Employee doctor = new Doctor("John", "Carter", 39, 5000, 15, Shift.Night, Specialization.Pediatry);
        float income = doctor.annualIncome();
        System.out.println(income);
        System.out.println(doctor.shiftBonus());
        System.out.println(asistent);
        System.out.println(doctor);
    }
}

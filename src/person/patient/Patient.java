package person.patient;

import person.Person;

public abstract class Patient extends Person {
    private static int idPatient = 0;
    public Patient(){ idPatient ++; }
    public Patient(String firstName, String lastName, int age){
        super(firstName, lastName, age);
        idPatient++;
    }

    public static int getId() { return idPatient; }

    public static void setId(int id) { Patient.idPatient = id; }

    @Override
    public String toString() {
        return super.toString() + ", status: patient, ";
    }
}



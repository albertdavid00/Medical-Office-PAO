package prescription;

import person.employee.Employee;
import person.patient.Child;
import person.patient.Patient;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Prescription {
    private Patient patient;
    private Employee employee;
    private Diagnosis diagnosis;
    private List<Medicine> meds;
    private static int  counterPrescription = 0;
    private int idPrescription;


    public Prescription(Patient patient, Employee employee, Diagnosis diagnosis) {
        counterPrescription ++;
        idPrescription = counterPrescription;
        this.patient = patient;
        this.employee = employee;
        this.diagnosis = diagnosis;
        this.meds = new LinkedList<Medicine>();
    }

    public Prescription(Patient patient, Employee employee, Diagnosis diagnosis, List<Medicine> meds) {
        this.patient = patient;
        this.employee = employee;
        this.diagnosis = diagnosis;
        this.meds = meds;
        counterPrescription ++;
        idPrescription = counterPrescription;
    }


    public static int getCounterPrescription() {
        return counterPrescription;
    }

    public static void setCounterPrescription(int counterPrescription) {
        Prescription.counterPrescription = counterPrescription;
    }

    public int getIdPrescription() {
        return idPrescription;
    }

    public void setIdPrescription(int idPrescription) {
        this.idPrescription = idPrescription;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Medicine> getMeds() { return meds; }

    public void setMeds(List<Medicine> meds) { this.meds = meds; }

    @Override
    public String toString() {
        return "Prescription{" +
                "\n\tpatient=" + patient +
                "\n\temployee=" + employee +
                "\n\tdiagnosis=" + diagnosis +
                "\n\tmeds=" + meds +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(employee, that.employee) &&
                diagnosis == that.diagnosis &&
                Objects.equals(meds, that.meds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, employee, diagnosis, meds);
    }
}

package person.patient;

import person.Person;
import prescription.MedicalRecords;

import java.util.Objects;

public abstract class Patient extends Person {
    private static int idPatient = 0;
    private MedicalRecords records;

    public Patient(){ idPatient ++; }
    public Patient(String firstName, String lastName, int age){
        super(firstName, lastName, age);
        idPatient++;
        //this.records = new MedicalRecords();
    }

    public static int getId() { return idPatient; }

    public static void setId(int id) { Patient.idPatient = id; }

    public MedicalRecords getRecords() { return records; }

    public void setRecords(MedicalRecords records) { this.records = records; }

    public String getType(){ return "Patient"; }

    public String calculateHeartRisk(){
        if(this.records == null){
            return "More data needed";
        }
        int normalSysBloodPressure = 105; // avg
        int normalDiasBloodPressure = 70; // avg
        int normalHeartRate = 85; // avg
        int sbpDifference = Math.abs(this.records.getSystolicBloodPressure() - normalSysBloodPressure);
        int dbpDifference = Math.abs(this.records.getDiastolicBloodPressure() - normalDiasBloodPressure);
        int BMI = (int) (this.records.getWeight() / Math.pow(this.records.getHeight(), 2));
        int riskFactors = 0;
        if(sbpDifference > (150 - normalSysBloodPressure)) {
            riskFactors ++;
        }
        if(dbpDifference > (90 - normalDiasBloodPressure)) {
            riskFactors ++;
        }
        if(Math.abs(this.records.getAverageHeartRate() - normalHeartRate) > 100 - normalHeartRate) {
            riskFactors ++;
        }
        if(BMI < 19 || BMI > 25){
            riskFactors ++;
        }
        if (riskFactors <= 1){
            return "Low";
        }else if (riskFactors <= 3){
            return "Medium";
        }
        return "High";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(records, patient.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), records);
    }

    @Override
    public String toString() {
        return super.toString() + ", status: patient, ";
    }
}



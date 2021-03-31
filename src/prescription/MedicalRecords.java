package prescription;

import java.util.Objects;

public class MedicalRecords {
    int height, weight;
    String gender;
    int systolicBloodPressure, diastolicBloodPressure;
    int averageHeartRate;

    public MedicalRecords() {
        height = 0;
        weight = 0;
        gender = "";
        systolicBloodPressure = 0;
        diastolicBloodPressure = 0;
        averageHeartRate = 0;
    }

    public MedicalRecords(int height, int weight, String gender, int systolicBloodPressure, int diastolicBloodPressure, int averageHeartRate) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.averageHeartRate = averageHeartRate;
    }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public int getSystolicBloodPressure() { return systolicBloodPressure; }

    public void setSystolicBloodPressure(int systolicBloodPressure) { this.systolicBloodPressure = systolicBloodPressure; }

    public int getDiastolicBloodPressure() { return diastolicBloodPressure; }

    public void setDiastolicBloodPressure(int diastolicBloodPressure) { this.diastolicBloodPressure = diastolicBloodPressure; }

    public int getAverageHeartRate() { return averageHeartRate; }

    public void setAverageHeartRate(int averageHeartRate) { this.averageHeartRate = averageHeartRate; }

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "height=" + height +
                ", weight=" + weight +
                ", gender='" + gender + '\'' +
                ", systolicBloodPressure=" + systolicBloodPressure +
                ", diastolicBloodPressure=" + diastolicBloodPressure +
                ", averageHeartRate=" + averageHeartRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecords that = (MedicalRecords) o;
        return height == that.height &&
                weight == that.weight &&
                systolicBloodPressure == that.systolicBloodPressure &&
                diastolicBloodPressure == that.diastolicBloodPressure &&
                averageHeartRate == that.averageHeartRate &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, weight, gender, systolicBloodPressure, diastolicBloodPressure, averageHeartRate);
    }
}
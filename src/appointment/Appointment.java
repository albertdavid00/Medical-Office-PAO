package appointment;

import person.employee.Employee;
import person.patient.Child;
import person.patient.Patient;

import java.util.Comparator;
import java.util.Objects;

public class Appointment implements Comparable<Appointment> {

    private static final Double discount = 0.5;
    private boolean discounted;
    private String date;
    private String startTime;
    private String endTime;
    private Patient patient;
    private Employee employee;
    private Double price;
    private static int counterApp = 0;
    private int idAppointment;

    public Appointment(String date, String startTime, String endTime, Patient patient, Employee employee, Double price) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patient = patient;
        this.employee = employee;
        this.price = price;
        this.discounted = false;
        counterApp ++;
        idAppointment = counterApp;
    }

    public static int getCounterApp() {
        return counterApp;
    }

    public static void setCounterApp(int counterApp) {
        Appointment.counterApp = counterApp;
    }

    public int getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getStartTime() { return startTime; }

    public void setStartTime(String time) { this.startTime = time; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) { this.employee = employee; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public void applyDiscount(){
        if(!this.discounted) {
            if (patient instanceof Child) {
                this.price *= discount;
                this.discounted = true;
            }
        }
    }
    public int duration(){
        // Time: hh:mm
        String hh = startTime.substring(0, 2);
        String mm = startTime.substring(3, 5);
        int startHour = Integer.parseInt(hh);
        int startMinutes = Integer.parseInt(mm);
        hh = endTime.substring(0, 2);
        mm = endTime.substring(3, 5);
        int endHour = Integer.parseInt(hh);
        int endMinutes = Integer.parseInt(mm);
        int duration;
        duration = (endHour - startHour) * 60 + (endMinutes - startMinutes);
        return duration; // in minutes
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", Time= " + startTime + " - " + endTime +
                ", Patient=" + patient.getFirstName() + " " + patient.getLastName() +
                ", Employee=" + employee.getFirstName() + " " + employee.getLastName() +
                ", discounted='" + discounted + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return discounted == that.discounted &&
                Objects.equals(date, that.date) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discounted, date, startTime, endTime, patient, employee, price);
    }

    @Override
    public int compareTo(Appointment o) {
        if(this.date.compareTo(o.date) > 0){
            return 1;
        }else if (this.date.compareTo(o.date) < 0){
            return -1;
        }
        return this.startTime.compareTo(o.startTime);
    }
}

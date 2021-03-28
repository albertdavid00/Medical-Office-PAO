package person.employee;

import java.util.Objects;

public class Doctor extends Employee{
    private Shift shift;
    private Specialization specialization;

    public Doctor(String firstName, String lastName, int age, int salary, int yrsOfExp, Shift shift, Specialization specialization){
        super(firstName, lastName, age, salary, yrsOfExp);
        this.shift = shift;
        this.specialization = specialization;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Shift getShift() { return shift; }

    public void setShift(Shift shift) { this.shift = shift; }

    public int shiftBonus(){
        int bonus = 0;
        if (this.shift == Shift.Night){
            bonus = 100;
        }
        return salary + bonus;
    }

    @Override
    public Float annualIncome(){
        Float income = salary - ((float)salary / taxPercentage);
        income *= 12;
        return income;
    }

    @Override
    public String toString() {
        return super.toString() + ", shift: " + shift +
                ", specialization: " + specialization + ", status: Doctor";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return shift == doctor.shift && specialization == doctor.specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shift, specialization);
    }
}

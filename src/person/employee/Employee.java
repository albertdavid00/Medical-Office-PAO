package person.employee;

import person.Person;

import java.util.Objects;

public abstract class Employee extends Person {
    private static int counterEmployee = 0;
    private int idEmployee;

    protected static final int taxPercentage = 10;    // taxa pe venit
    protected int salary;
    protected int yearsOfExperience;

    public Employee(String firstName, String lastName, int age, int salary, int yearsOfExperience){
        super(firstName, lastName, age);
        counterEmployee++;
        idEmployee = counterEmployee;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getIdEmployee() { return idEmployee; }

    public void setIdEmployee(int idEmployee) { this.idEmployee = idEmployee; }

    public static int getCounterEmployee() { return counterEmployee; }

    public static void setCounterEmployee(int cEmployee) { Employee.counterEmployee = cEmployee; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

    public int getYearsOfExperience() { return yearsOfExperience; }

    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public static int getTaxPercentage() { return taxPercentage; }

    public int shiftBonus(){ return salary;}

    public abstract Float annualIncome();

    @Override
    public String toString() {
        return super.toString() +
                ", salary: " + salary +
                ", yearsOfExperience: " + yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && yearsOfExperience == employee.yearsOfExperience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, yearsOfExperience);
    }
}

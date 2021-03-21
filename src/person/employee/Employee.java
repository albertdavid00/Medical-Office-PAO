package person.employee;

import person.Person;

public abstract class Employee extends Person {
    private static int idEmployee = 0;
    protected int salary;

    public Employee(String firstName, String lastName, int age, int salary){
        super(firstName, lastName, age);
        idEmployee++;
        this.salary = salary;
    }

    public static int getIdEmployee() { return idEmployee; }

    public static void setIdEmployee(int idEmployee) { Employee.idEmployee = idEmployee; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }
}

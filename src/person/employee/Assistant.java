package person.employee;

import java.util.Objects;
import java.util.Scanner;

public class Assistant extends Employee{
    private int bonus;

    public Assistant(int id, String firstName, String lastName, int age, int salary, int yrsOfExp, int bonus){
        super(firstName, lastName, age, salary, yrsOfExp);
        this.bonus = bonus;
        this.setIdEmployee(id);
    }

    public Assistant(String firstName, String lastName, int age, int salary, int yrsOfExp, int bonus){
        super(firstName, lastName, age, salary, yrsOfExp);
        this.bonus = bonus;
    }

    public int getBonus() { return bonus; }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public static Assistant read(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name: ");
        String fName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lName = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter salary: ");
        int salary = scanner.nextInt();

        System.out.println("Enter years of experience: ");
        int yrsOfExp = scanner.nextInt();

        System.out.println("Enter bonus income: ");
        int bonus = scanner.nextInt();

        return new Assistant(fName, lName, age, salary, yrsOfExp, bonus);
    }

    @Override
    public Float annualIncome(){
        Float income = salary - ((float)salary / taxPercentage) + bonus;
        income *= 12;
        return income;
    }

    @Override
    public String toString() {
        return super.toString() + " bonus: " + bonus + ", status: Asistent";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assistant assistant = (Assistant) o;
        return bonus == assistant.bonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }
}

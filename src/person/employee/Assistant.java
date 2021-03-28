package person.employee;

import java.util.Objects;

public class Assistant extends Employee{
    int bonus;

    public Assistant(String firstName, String lastName, int age, int salary, int yrsOfExp, int bonus){
        super(firstName, lastName, age, salary, yrsOfExp);
        this.bonus = bonus;
    }

    public int getBonus() { return bonus; }

    public void setBonus(int bonus) {
        this.bonus = bonus;
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

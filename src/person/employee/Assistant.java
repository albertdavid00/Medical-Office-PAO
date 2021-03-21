package person.employee;

public class Assistant extends Employee{
    int bonus;
    public Assistant(String firstName, String lastName, int age, int salary, int bonus){
        super(firstName, lastName, age, salary);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}

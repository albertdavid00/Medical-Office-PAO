package person.patient;

public class Child extends Patient {
    protected String nameMother, nameFather;
    // afectiune enum - acuta sau cronica;

    public Child(String firstName, String lastName, int age, String nameMother, String nameFather) throws Exception {
        super(firstName, lastName, age);
        this.nameMother = nameMother;
        this.nameFather = nameFather;
        if(age >= 18){
            setId(getId()-1);
            throw new Exception("Child can't be 18 years old or older");
        }
    }
}

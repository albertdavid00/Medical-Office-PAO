package person.patient;

public class Adult extends Patient{
    protected String phoneNumber;

    public Adult(String firstName, String lastName, int age, String phoneNumber) throws Exception {
        super(firstName, lastName, age);

        this.phoneNumber = phoneNumber;
        if(age < 18){
            throw new Exception("Adults have to be at least 18 years old!");
        }
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

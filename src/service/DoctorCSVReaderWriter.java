package service;

import person.employee.Doctor;
import person.employee.Shift;
import person.employee.Specialization;
import person.patient.Child;

public class DoctorCSVReaderWriter implements CSVReaderWriter<Doctor>{
    private static DoctorCSVReaderWriter INSTANCE = null;
    private static final String FILE_NAME = "src/resources/doctors.csv";

    public static synchronized DoctorCSVReaderWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorCSVReaderWriter();
        }
        return INSTANCE;
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    @Override
    public Doctor processCSVLine(String line) {
        String[] split = line.split(SEPARATOR);
        return new Doctor(split[0], split[1], Integer.parseInt(split[2]),
                Integer.parseInt(split[3]), Integer.parseInt(split[4]),
                Shift.valueOf(split[5]), Specialization.valueOf(split[6]));
    }

    @Override
    public String convertObjectToCSVLine(Doctor object) {
        return object.getFirstName() + SEPARATOR + object.getLastName() +
                SEPARATOR + object.getAge() + SEPARATOR + object.getSalary() +
                SEPARATOR + object.getYearsOfExperience() + SEPARATOR +
                object.getShift() + SEPARATOR + object.getSpecialization() + "\n";
    }
}

package service;

import person.employee.Assistant;
import person.employee.Doctor;
import person.employee.Shift;
import person.employee.Specialization;

public class AssistantCSVReaderWriter implements CSVReaderWriter<Assistant>{

    private static AssistantCSVReaderWriter INSTANCE = null;
    private static final String FILE_NAME = "src/resources/assistants.csv";

    public static synchronized AssistantCSVReaderWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AssistantCSVReaderWriter();
        }
        return INSTANCE;
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    @Override
    public Assistant processCSVLine(String line) {
        String[] split = line.split(SEPARATOR);
        return new Assistant(split[0], split[1], Integer.parseInt(split[2]),
                Integer.parseInt(split[3]), Integer.parseInt(split[4]),
                Integer.parseInt(split[5]));
    }

    @Override
    public String convertObjectToCSVLine(Assistant object) {
        return object.getFirstName() + SEPARATOR + object.getLastName() +
                SEPARATOR + object.getAge() + SEPARATOR + object.getSalary() +
                SEPARATOR + object.getYearsOfExperience() + SEPARATOR +
                object.getBonus() + "\n";
    }
}

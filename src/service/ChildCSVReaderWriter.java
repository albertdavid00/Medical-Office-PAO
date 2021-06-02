package service;

import person.patient.Adult;
import person.patient.Child;

public class ChildCSVReaderWriter implements CSVReaderWriter<Child>{

    private static ChildCSVReaderWriter INSTANCE = null;
    private static final String FILE_NAME = "src/resources/children.csv";

    public static synchronized ChildCSVReaderWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChildCSVReaderWriter();
        }
        return INSTANCE;
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    @Override
    public Child processCSVLine(String line) {
        String[] split = line.split(SEPARATOR);
        return new Child(split[0], split[1], Integer.parseInt(split[2]), split[3], split[4]);
    }

    @Override
    public String convertObjectToCSVLine(Child object) {
        return object.getFirstName() + SEPARATOR + object.getLastName() +
                SEPARATOR + object.getAge() + SEPARATOR +
                object.getMotherName() + SEPARATOR + object.getFatherName() + "\n";
    }
}

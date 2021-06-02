package service;

import person.patient.Adult;

public class AdultCSVReaderWriter implements CSVReaderWriter<Adult>{

    private static AdultCSVReaderWriter INSTANCE = null;
    private static final String FILE_NAME = "src/resources/adults.csv";

    public static synchronized AdultCSVReaderWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdultCSVReaderWriter();
        }
        return INSTANCE;
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    @Override
    public Adult processCSVLine(String line) {
        String[] split = line.split(SEPARATOR);
        return new Adult(split[0], split[1], Integer.parseInt(split[2]), split[3] );
    }

    @Override
    public String convertObjectToCSVLine(Adult object) {
        return object.getFirstName() +
                SEPARATOR + object.getLastName() + SEPARATOR + object.getAge() +
                SEPARATOR + object.getPhoneNumber() + "\n";
    }
}

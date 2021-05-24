package config;

import person.employee.Employee;
import person.employee.Shift;
import repository.RepositoryHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSetup {

    public void setUp() {
        String createTableAdults = "CREATE TABLE IF NOT EXISTS adults" +
                "(id int PRIMARY KEY AUTO_INCREMENT, firstName varchar(30)," +
                "lastName varchar(30), age int, phoneNumber varchar(15))";
        String createTableChildren = "CREATE TABLE IF NOT EXISTS children" +
                "(id int PRIMARY KEY AUTO_INCREMENT, firstName varchar(30)," +
                "lastName varchar(30), age int, motherName varchar(30), fatherName varchar(30))";
        String createTableDoctors = "CREATE TABLE IF NOT EXISTS doctors" +
                "(id int PRIMARY KEY AUTO_INCREMENT, firstName varchar(30)," +
                "lastName varchar(30), age int, salary int, yearsOfExperience int," +
                "shift varchar(30), specialization varchar(30))";
        String createTableAssistants = "CREATE TABLE IF NOT EXISTS assistants" +
                "(id int PRIMARY KEY AUTO_INCREMENT, firstName varchar(30)," +
                "lastName varchar(30), age int, salary int, yearsOfExperience int, bonus int)";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableAdults);
            repositoryHelper.executeSql(databaseConnection, createTableChildren);
            repositoryHelper.executeSql(databaseConnection, createTableDoctors);
            repositoryHelper.executeSql(databaseConnection, createTableAssistants);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAdult() {
        String insertAdultSql = "INSERT INTO adults(firstName, lastName, age, phoneNumber) VALUES('Marvin','Sprouse',23,'0723884572')";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertAdultSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addChild() {
        String insertChildSql = "INSERT INTO children(firstName, lastName, age, motherName, fatherName) VALUES('Timmy','Smith',14,'Nicole','Steve')";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertChildSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDoctor() {
        String insertDoctorSql = "INSERT INTO doctors(firstName, lastName, age, salary, yearsOfExperience, shift, specialization)" +
        "VALUES('Dave','Jones',53,12000,30,'Morning','Neurology')";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertDoctorSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addAssistant() {
        String insertDoctorSql = "INSERT INTO assistants(firstName, lastName, age, salary, yearsOfExperience, bonus)" +
                "VALUES('Fiona','Watson',49,4500,23,450)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertDoctorSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAdult() {
        String selectSql = "SELECT * FROM adults";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) +
                        "\tFirst Name:" + resultSet.getString(2) +
                        "\tLast Name:" + resultSet.getString(3) +
                        "\tAge:" + resultSet.getInt(4) +
                        "\tPhone Number:" + resultSet.getString(5) +
                        "\tStatus: Patient");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayChild() {
        String selectSql = "SELECT * FROM children";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) +
                        "\tFirst Name:" + resultSet.getString(2) +
                        "\tLast Name:" + resultSet.getString(3) +
                        "\tAge:" + resultSet.getInt(4) +
                        "\tMother's Name:" + resultSet.getString(5) +
                        "\tFather's Name:" + resultSet.getString(6) +
                        "\tStatus:Patient");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayDoctor() {
        String selectSql = "SELECT * FROM doctors";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) +
                        "\tFirst Name:" + resultSet.getString(2) +
                        "\tLast Name:" + resultSet.getString(3) +
                        "\tAge:" + resultSet.getInt(4) +
                        "\tSalary:" + resultSet.getInt(5) +
                        "\tYears of Experience:" + resultSet.getInt(6) +
                        "\tShift:" + resultSet.getString(7) +
                        "\tSpecialization:" + resultSet.getString(8)+
                        "\tStatus:Doctor");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAssistant() {
        String selectSql = "SELECT * FROM assistants";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) +
                        "\tFirst Name:" + resultSet.getString(2) +
                        "\tLast Name:" + resultSet.getString(3) +
                        "\tAge:" + resultSet.getInt(4) +
                        "\tSalary:" + resultSet.getInt(5) +
                        "\tYears of Experience:" + resultSet.getInt(6) +
                        "\tBonus:" + resultSet.getInt(7) +
                        "\tStatus:Assistant");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package repository;

import config.DatabaseConfiguration;
import person.employee.Doctor;
import person.employee.Shift;
import person.employee.Specialization;

import java.sql.*;

public class DoctorRepository {
    // CallableStatement
    public void insertDoctor(Doctor doctor) {
        String dropProcedureSql = "DROP PROCEDURE IF EXISTS insertDoctor";
        String createProcedureSql = "CREATE PROCEDURE insertDoctor(OUT id int, IN firstName varchar(30)," +
                " IN lastName varchar(30), IN age int, IN salary int, IN experience int," +
                " IN shift varchar(30), IN specialization varchar(30))" +
                "BEGIN INSERT INTO doctors(firstName, lastName, age, salary, yearsOfExperience, shift, specialization)" +
                "VALUES (firstName, lastName, age, salary, experience, shift, specialization);" +
                "SET id = LAST_INSERT_ID(); END";
        String preparedSql = "{call insertDoctor(?,?,?,?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement stmt = databaseConnection.prepareCall(dropProcedureSql);
            CallableStatement stmt2 = databaseConnection.prepareCall(createProcedureSql);
            stmt.execute();
            stmt2.execute();
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, doctor.getFirstName());
            cstmt.setString(3, doctor.getLastName());
            cstmt.setInt(4 , doctor.getAge());
            cstmt.setInt(5, doctor.getSalary());
            cstmt.setInt(6, doctor.getYearsOfExperience());
            cstmt.setString(7, doctor.getShift().toString());
            cstmt.setString(8, doctor.getSpecialization().toString());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Doctor getDoctorById(int id) {
        String selectSql = "SELECT * FROM doctors WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDoctor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateDoctorFirstName(String fname, int id) {
        String updateFNameSql = "UPDATE doctors SET firstName=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateFNameSql);
            preparedStatement.setString(1, fname);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDoctorLastName(String lname, int id) {
        String updateLNameSql = "UPDATE doctors SET lastName=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateLNameSql);
            preparedStatement.setString(1, lname);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDoctorAge(int age, int id) {
        String updateAgeSql = "UPDATE doctors SET age=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateAgeSql);
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDoctorSalary(int salary, int id) {
        String updateSalarySql = "UPDATE doctors SET salary=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSalarySql);
            preparedStatement.setInt(1, salary);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDoctorYearsOfExperience(int exp, int id) {
        String updateExpSql = "UPDATE doctors SET yearsOfExperience=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateExpSql);
            preparedStatement.setInt(1, exp);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDoctorShift(Shift shift, int id) {
        String updateShiftSql = "UPDATE doctors SET shift=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateShiftSql);
            preparedStatement.setString(1, shift.toString());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDoctorSpecialization(Specialization specialization, int id) {
        String updateSpecializationSql = "UPDATE doctors SET specialization=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSpecializationSql);
            preparedStatement.setString(1, specialization.toString());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteDoctor(int id) {
        String deleteDoctorSql = "DELETE FROM doctors WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteDoctorSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Doctor mapToDoctor(ResultSet resultSet) throws Exception {
        if (resultSet.next()){
            return new Doctor(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4),
                    resultSet.getInt(5), resultSet.getInt(6),
                    Shift.valueOf(resultSet.getString(7)),
                    Specialization.valueOf(resultSet.getString(8)));
        }
        return null;
    }
}

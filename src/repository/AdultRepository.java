package repository;

import java.sql.*;
import config.DatabaseConfiguration;
import person.patient.Adult;

public class AdultRepository {
    // CallableStatement
    public void insertAdult(Adult adult) {
        String dropProcedureSql = "DROP PROCEDURE IF EXISTS insertAdult";
        String createProcedureSql = "CREATE PROCEDURE insertAdult(OUT id int, IN firstName varchar(30)," +
                " IN lastName varchar(30), IN age int, IN phoneNumber varchar(15))" +
                "BEGIN INSERT INTO adults(firstName, lastName, age, phoneNumber)" +
                "VALUES (firstName, lastName, age, phoneNumber);" +
                "SET id = LAST_INSERT_ID(); END";
        String preparedSql = "{call insertAdult(?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement stmt = databaseConnection.prepareCall(dropProcedureSql);
            CallableStatement stmt2 = databaseConnection.prepareCall(createProcedureSql);
            stmt.execute();
            stmt2.execute();
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, adult.getFirstName());
            cstmt.setString(3, adult.getLastName());
            cstmt.setInt(4 , adult.getAge());
            cstmt.setString(5, adult.getPhoneNumber());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Adult getAdultById(int id) {
        String selectSql = "SELECT * FROM adults WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAdult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateAdultFirstName(String fname, int id) {
        String updateFNameSql = "UPDATE adults SET firstName=? WHERE id=?";

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
    public void updateAdultLastName(String lname, int id) {
        String updateLNameSql = "UPDATE adults SET lastName=? WHERE id=?";

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
    public void updateAdultAge(int age, int id) {
        String updateAgeSql = "UPDATE adults SET age=? WHERE id=?";

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
    public void updateAdultPhoneNumber(String phone, int id) {
        String updatePNSql = "UPDATE adults SET phoneNumber=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updatePNSql);
            preparedStatement.setString(1, phone);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteAdult(int id) {
        String deleteAdultSql = "DELETE FROM adults WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteAdultSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Adult mapToAdult(ResultSet resultSet) throws Exception {
        if (resultSet.next()){
            return new Adult(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5));
        }
        return null;
    }
}

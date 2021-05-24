package repository;

import config.DatabaseConfiguration;
import person.patient.Adult;
import person.patient.Child;

import java.sql.*;

public class ChildRepository {
    // CallableStatement
    public void insertChild(Child child) {
        String dropProcedureSql = "DROP PROCEDURE IF EXISTS insertChild";
        String createProcedureSql = "CREATE PROCEDURE insertChild(OUT id int, IN firstName varchar(30)," +
                " IN lastName varchar(30), IN age int, IN motherName varchar(30), IN fatherName varchar(30))" +
                "BEGIN INSERT INTO children(firstName, lastName, age, motherName, fatherName)" +
                "VALUES (firstName, lastName, age, motherName, fatherName);" +
                "SET id = LAST_INSERT_ID(); END";
        String preparedSql = "{call insertChild(?,?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement stmt = databaseConnection.prepareCall(dropProcedureSql);
            CallableStatement stmt2 = databaseConnection.prepareCall(createProcedureSql);
            stmt.execute();
            stmt2.execute();
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, child.getFirstName());
            cstmt.setString(3, child.getLastName());
            cstmt.setInt(4 , child.getAge());
            cstmt.setString(5, child.getMotherName());
            cstmt.setString(6, child.getFatherName());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Child getChildById(int id) {
        String selectSql = "SELECT * FROM children WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToChild(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateChildFirstName(String fname, int id) {
        String updateFNameSql = "UPDATE children SET firstName=? WHERE id=?";

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
    public void updateChildLastName(String lname, int id) {
        String updateLNameSql = "UPDATE children SET lastName=? WHERE id=?";

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
    public void updateChildAge(int age, int id) {
        String updateAgeSql = "UPDATE children SET age=? WHERE id=?";

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
    public void updateChildMotherName(String mname, int id) {
        String updateMNSql = "UPDATE children SET motherName=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateMNSql);
            preparedStatement.setString(1, mname);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateChildFatherName(String fname, int id) {
        String updateFNSql = "UPDATE children SET fatherName=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateFNSql);
            preparedStatement.setString(1, fname);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteChild(int id) {
        String deleteChildSql = "DELETE FROM children WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteChildSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Child mapToChild(ResultSet resultSet) throws Exception {
        if (resultSet.next()){
            return new Child(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6));
        }
        return null;
    }
}

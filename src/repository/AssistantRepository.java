package repository;

import config.DatabaseConfiguration;
import person.employee.Assistant;
import person.employee.Doctor;
import person.employee.Shift;
import person.employee.Specialization;

import java.sql.*;

public class AssistantRepository {
    // CallableStatement
    public void insertAssistant(Assistant assistant) {
        String dropProcedureSql = "DROP PROCEDURE IF EXISTS insertAssistant";
        String createProcedureSql = "CREATE PROCEDURE insertAssistant(OUT id int, IN firstName varchar(30)," +
                " IN lastName varchar(30), IN age int, IN salary int, IN experience int, IN bonus int)" +
                "BEGIN INSERT INTO assistants(firstName, lastName, age, salary, yearsOfExperience, bonus)" +
                "VALUES (firstName, lastName, age, salary, experience, bonus);" +
                "SET id = LAST_INSERT_ID(); END";
        String preparedSql = "{call insertAssistant(?,?,?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement stmt = databaseConnection.prepareCall(dropProcedureSql);
            CallableStatement stmt2 = databaseConnection.prepareCall(createProcedureSql);
            stmt.execute();
            stmt2.execute();
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, assistant.getFirstName());
            cstmt.setString(3, assistant.getLastName());
            cstmt.setInt(4 , assistant.getAge());
            cstmt.setInt(5, assistant.getSalary());
            cstmt.setInt(6, assistant.getYearsOfExperience());
            cstmt.setInt(7, assistant.getBonus());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Assistant getAssistantById(int id) {
        String selectSql = "SELECT * FROM assistants WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAssistant(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateAssistantFirstName(String fname, int id) {
        String updateFNameSql = "UPDATE assistants SET firstName=? WHERE id=?";

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
    public void updateAssistantLastName(String lname, int id) {
        String updateLNameSql = "UPDATE assistants SET lastName=? WHERE id=?";

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
    public void updateAssistantAge(int age, int id) {
        String updateAgeSql = "UPDATE assistants SET age=? WHERE id=?";

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
    public void updateAssistantSalary(int salary, int id) {
        String updateSalarySql = "UPDATE assistants SET salary=? WHERE id=?";

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
    public void updateAssistantYearsOfExperience(int exp, int id) {
        String updateExpSql = "UPDATE assistants SET yearsOfExperience=? WHERE id=?";

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
    public void updateAssistantBonus(int bonus, int id) {
        String updateShiftSql = "UPDATE assistants SET bonus=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateShiftSql);
            preparedStatement.setInt(1, bonus);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteAssistant(int id) {
        String deleteAssistantSql = "DELETE FROM assistants WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteAssistantSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Assistant mapToAssistant(ResultSet resultSet) throws Exception {
        if (resultSet.next()){
            return new Assistant(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4),
                    resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
        }
        return null;
    }
}

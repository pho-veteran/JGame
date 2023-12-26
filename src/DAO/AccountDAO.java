package DAO;

import DTO.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private DBConn dbConn = new DBConn();
    public Integer registerAccount(String username, String password) {
        String sqlCheckExistence = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        String sqlInsertAccount = "INSERT INTO accounts(username, password, accessRight) VALUES(?, ?, 'User')";
        String sqlInsertUser = "INSERT INTO users(username, balance, primeStatus) VALUES(?, 0.0, 0)";


        try (PreparedStatement checkExistenceStatement = dbConn.getConn().prepareStatement(sqlCheckExistence);
             PreparedStatement insertAccountStatement = dbConn.getConn().prepareStatement(sqlInsertAccount);
             PreparedStatement insertUserStatement = dbConn.getConn().prepareStatement(sqlInsertUser)) {

            // Check if the username already exists
            checkExistenceStatement.setString(1, username);
            ResultSet resultSet = checkExistenceStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("User already exists");
                return -1;
            }

            insertAccountStatement.setString(1, username);
            insertAccountStatement.setString(2, password);
            insertAccountStatement.executeUpdate();

            insertUserStatement.setString(1, username);
            insertUserStatement.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Account loginAccount(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Account result = new Account(rs.getString("username"), rs.getString("password"), rs.getString("accessRight"));
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

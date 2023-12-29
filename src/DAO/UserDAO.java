package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private DBConn dbConn = new DBConn();

    public double getBalance(String username) {
        String sql = "SELECT balance FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("Balance: " + rs.getDouble("balance"));
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Integer getPrimeStatus(String username) {
        String sql = "SELECT primeStatus FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("Prime status: " + rs.getInt("primeStatus"));
                return rs.getInt("primeStatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting prime status");
        }
        return 0;
    }
    public Integer getUserID(String username) {
        String sql = "SELECT userID FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("User ID: " + rs.getInt("userID"));
                return rs.getInt("userID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final DBConn dbConn = new DBConn();

    public double getBalance(String username) {
        String sql = "SELECT balance FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
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
                return rs.getInt("userID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addFund(String username, double fund) {
        String sql = "UPDATE users SET balance = balance + ? WHERE username = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setDouble(1, fund);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buyPrime(String username) {
        String sql = "UPDATE users SET primeStatus = 1 WHERE username = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

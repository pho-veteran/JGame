package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {
    private DBConn dbConn = new DBConn();
    public String registerAccount(String username, String password, String accessRight) {
        String sql = "INSERT INTO accounts(username, password, accessRight) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, accessRight);
            preparedStatement.executeUpdate();
            return "Đăng kí thành công!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi liên quan đến lớp DAO";
        }
    }
    public String loginAccount(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = dbConn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String accessRight = rs.getString("access_right");
                return accessRight;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

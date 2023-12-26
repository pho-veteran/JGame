package DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private String url = "jdbc:mysql://localhost:3306/gamemanagement";
    private String username = "root";
    private String password = "Ntv06042005@";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn;
    public DBConn() {
        try {
            this.conn = getConnection(url, username, password, driver);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối đến database");
        }
    }
    public Connection getConn() {
        return conn;
    }
    private Connection getConnection(String dbURL, String userName, String password, String driver) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(dbURL, userName, password);
    }
}


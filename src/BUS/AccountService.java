package BUS;

import DAO.AccountDAO;
import DAO.UserDAO;
import DTO.Account;

import java.sql.SQLException;

public class AccountService {
    private String username;
    private Account account;
    public Integer register(String username, String password) throws SQLException {
        return new AccountDAO().registerAccount(username, password);
    }

    public Account login(String username, String password) {
        Account attempt = new AccountDAO().loginAccount(username, password);
        if (attempt != null) {
            account = attempt;
            return attempt;
        } else {
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public Double getBalance(String username) {
        return new UserDAO().getBalance(username);
    }

    public Integer getPrimeStatus(String username) {
        return new UserDAO().getPrimeStatus(username);
    }
}

package BUS;

import DAO.AccountDAO;
import DAO.GameDAO;
import DAO.UserDAO;
import DTO.Account;

import java.sql.SQLException;

public class AccountService {
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

    public Double getBalance(String username) {
        return new UserDAO().getBalance(username);
    }

    public Integer getPrimeStatus(String username) {
        return new UserDAO().getPrimeStatus(username);
    }
    public Boolean checkGameOwned(String username, Integer gameID) {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.checkGameOwned(username, gameID);
    }
    public void buyGame(Integer accountID, Integer gameID, Double price) {
        GameDAO gameDAO = new GameDAO();
        gameDAO.buyGame(accountID, gameID, price);
    }
    public Integer getUserID(String username) {
        UserDAO userDao = new UserDAO();
        return userDao.getUserID(username);
    }
}

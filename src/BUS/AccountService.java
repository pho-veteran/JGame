package BUS;

import DAO.AccountDAO;
import DAO.GameDAO;
import DAO.UserDAO;
import DTO.Account;
import DTO.Game;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountService {
    public Integer register(String username, String password) throws SQLException {
        return new AccountDAO().registerAccount(username, password);
    }

    public Account login(String username, String password) {
        Account account = new AccountDAO().loginAccount(username, password);
        if (account != null) {
            return account;
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
    public ArrayList<Game> getOwnedGames(Integer accountID) {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getOwnedGames(accountID);
    }
    public ArrayList<Game> getSearchedOwnedGames(Integer accountID, String name, String genre) {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getSearchedOwnedGames(accountID, name, genre);
    }
    public String getPassword(Integer accountID) {
        AccountDAO accountDAO = new AccountDAO();
        return accountDAO.getPassword(accountID);
    }
    public void changePassword(String newPassword, Integer accountID) {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changePassword(newPassword, accountID);
    }
}

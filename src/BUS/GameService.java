package BUS;

import DAO.GameDAO;
import DTO.Game;

import java.util.ArrayList;

public class GameService {
    public ArrayList<Game> getTop5GamesList() {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getTop5GameList();
    }

    public ArrayList<Game> getAllGamesList() {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getAllGameList();
    }

    public ArrayList<Game> searchGame(String name, String genre) {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getSearchGameList(name, genre);
    }

    public ArrayList<String> getGameGenreList() {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getGameGenre();
    }

    public void updateGame(Game game) {
        GameDAO gameDAO = new GameDAO();
        gameDAO.updateGame(game);
    }

    public void removeGame(Game game) {
        GameDAO gameDAO = new GameDAO();
        gameDAO.removeGame(game);
    }

    public void addGame(Game game) {
        GameDAO gameDAO = new GameDAO();
        gameDAO.addGame(game);
    }
}

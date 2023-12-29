package BUS;

import DAO.GameDAO;
import DTO.Game;

import java.util.ArrayList;

public class GameService {
    public ArrayList<Game> getTop5GameList() {
        GameDAO gameDAO = new GameDAO();
        return gameDAO.getTop5GameList();
    }
    public ArrayList<Game> getAllGameList() {
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
}

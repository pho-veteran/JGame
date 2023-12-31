package DAO;

import DTO.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameDAO {

    private DBConn conn = new DBConn();
    //Get top 5 games from database return a list of Object: Game
    public ArrayList<Game> getTop5GameList() {
        ArrayList<Game> top5GameList = new ArrayList<>();
        String sql = "SELECT games.*\n" +
                "FROM games\n" +
                "INNER JOIN usergames ON games.gameID = usergames.gameID\n" +
                "GROUP BY games.gameID\n" +
                "ORDER BY COUNT(usergames.gameID) DESC\n" +
                "LIMIT 5;\n";
        try (PreparedStatement statement = conn.getConn().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Game currentGame = new Game();
                currentGame.setGameId(rs.getInt("gameID"));
                currentGame.setDescription(rs.getString("description"));
                currentGame.setGenre(rs.getString("genre"));
                currentGame.setName(rs.getString("name"));
                currentGame.setPrice(rs.getDouble("price"));
                currentGame.setBannerURL(rs.getString("bannerURL"));
                top5GameList.add(currentGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return top5GameList;
    }

    //Get all games from database return a list of Object: Game
    public ArrayList<Game> getAllGameList() {
        ArrayList<Game> allGameList = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (PreparedStatement statement = conn.getConn().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Game currentGame = new Game();
                currentGame.setGameId(rs.getInt("gameID"));
                currentGame.setDescription(rs.getString("description"));
                currentGame.setGenre(rs.getString("genre"));
                currentGame.setName(rs.getString("name"));
                currentGame.setPrice(rs.getDouble("price"));
                currentGame.setBannerURL(rs.getString("bannerURL"));
                allGameList.add(currentGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allGameList;
    }

    //Search by name or genre or both, return a list of Object: Game
    public ArrayList<Game> getSearchGameList(String name, String genre) {
        ArrayList<Game> searchGameList = new ArrayList<>();
        String sql = "SELECT * FROM games WHERE name LIKE ? AND genre LIKE ?";
        try (PreparedStatement statement = conn.getConn().prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + genre + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Game currentGame = new Game();
                currentGame.setGameId(rs.getInt("gameID"));
                currentGame.setDescription(rs.getString("description"));
                currentGame.setGenre(rs.getString("genre"));
                currentGame.setName(rs.getString("name"));
                currentGame.setPrice(rs.getDouble("price"));
                currentGame.setBannerURL(rs.getString("bannerURL"));
                searchGameList.add(currentGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchGameList;
    }

    //Get game genre list from database return a list of String
    public ArrayList<String> getGameGenre() {
        ArrayList<String> genreList = new ArrayList<>();
        String sql = "SELECT DISTINCT genre FROM games";
        try (PreparedStatement preparedStatement = conn.getConn().prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                genreList.add(rs.getString("genre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreList;
    }

    //Check if user owned the game or not
    public boolean checkGameOwned(String username, Integer gameID) {
        String sql = "SELECT * FROM usergames INNER JOIN users ON usergames.userID = users.userID\n" +
                        "WHERE username = ? and gameID = ?";
        try (PreparedStatement preparedStatement = conn.getConn().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, gameID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Buy game
    public void buyGame(Integer accountID, Integer gameID, Double gamePrice) {
        String sql = "INSERT INTO usergames(userID, gameID) VALUES(?, ?)";
        String sqlUpdateBalance = "UPDATE users SET balance = balance - ? WHERE userID = ?";
        try (PreparedStatement preparedStatement = conn.getConn().prepareStatement(sql);
             PreparedStatement preparedStatement1 = conn.getConn().prepareStatement(sqlUpdateBalance)) {
            preparedStatement.setInt(1, accountID);
            preparedStatement.setInt(2, gameID);
            preparedStatement.executeUpdate();

            preparedStatement1.setDouble(1, gamePrice);
            preparedStatement1.setInt(2, accountID);
            preparedStatement1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get owned games by account id
    public ArrayList<Game> getOwnedGames(Integer accountID) {
        ArrayList<Game> ownedGamesList = new ArrayList<>();
        String sql = "SELECT games.*\n" +
                "FROM games\n" +
                "INNER JOIN usergames ON games.gameID = usergames.gameID\n" +
                "WHERE usergames.userID = ?";
        try (PreparedStatement preparedStatement = conn.getConn().prepareStatement(sql)) {
            preparedStatement.setInt(1, accountID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Game currentGame = new Game();
                currentGame.setGameId(rs.getInt("gameID"));
                currentGame.setDescription(rs.getString("description"));
                currentGame.setGenre(rs.getString("genre"));
                currentGame.setName(rs.getString("name"));
                currentGame.setPrice(rs.getDouble("price"));
                currentGame.setBannerURL(rs.getString("bannerURL"));
                ownedGamesList.add(currentGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ownedGamesList;
    }
    //Get searched owned games by account id, name, genre
    public ArrayList<Game> getSearchedOwnedGames(Integer accountID, String name, String genre) {
        ArrayList<Game> ownedGamesList = new ArrayList<>();
        String sql = "SELECT games.*\n" +
                "FROM games\n" +
                "INNER JOIN usergames ON games.gameID = usergames.gameID\n" +
                "WHERE usergames.userID = ? AND games.name LIKE ? AND games.genre LIKE ?";
        try (PreparedStatement preparedStatement = conn.getConn().prepareStatement(sql)) {
            preparedStatement.setInt(1, accountID);
            preparedStatement.setString(2, "%" + name + "%");
            preparedStatement.setString(3, "%" + genre + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Game currentGame = new Game();
                currentGame.setGameId(rs.getInt("gameID"));
                currentGame.setDescription(rs.getString("description"));
                currentGame.setGenre(rs.getString("genre"));
                currentGame.setName(rs.getString("name"));
                currentGame.setPrice(rs.getDouble("price"));
                currentGame.setBannerURL(rs.getString("bannerURL"));
                ownedGamesList.add(currentGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ownedGamesList;
    }
}

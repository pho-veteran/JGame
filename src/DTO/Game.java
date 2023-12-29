package DTO;

public class Game {
    private int gameId;
    private double price;
    private String name;
    private String description;
    private String genre;
    private String bannerURL;
    public Game(int gameID, double price, String name, String description, String genre, String bannerURL) {
        this.gameId = gameID;
        this.price = price;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.bannerURL = bannerURL;
    }
    public Game() {

    }
    public String getBannerURL() {
        return bannerURL;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }
}

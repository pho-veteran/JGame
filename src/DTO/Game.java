package DTO;

public class Game {
    private double price;
    private String name;
    private String description;
    private String genre;
    public Game(double price, String name, String description, String genre) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.genre = genre;
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
}

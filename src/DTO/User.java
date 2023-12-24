package DTO;

public class User {
    private int id;
    private String username;
    private double balance;
    private Boolean primeStatus;

    public User(int id, String username, double balance, Boolean primeStatus) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.primeStatus = primeStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean getPrimeStatus() {
        return primeStatus;
    }

    public void setPrimeStatus(Boolean primeStatus) {
        this.primeStatus = primeStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

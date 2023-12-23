package DTO;

import java.awt.*;

public class User extends Account {
    private double balance;
    private Boolean primeStatus;
    public User(int id, String username, String password, String accessRights, double balance, Boolean primeStatus) {
        super(id, username, password, accessRights);
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
}

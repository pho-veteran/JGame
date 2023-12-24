package DTO;

public class Account {
    private int id;
    private String username;
    private String password;
    private String accessRight;

    public Account(int id, String username, String password, String accessRight) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessRight = accessRight;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }
}

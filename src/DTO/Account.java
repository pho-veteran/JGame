package DTO;

public abstract class Account {
    private int id;
    private String username;
    private String password;
    private String accessRights;

    public Account(int id, String username, String password, String accessRights) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessRights = accessRights;
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

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }
}

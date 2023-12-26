package DTO;

public class Account {
    private String username;
    private String password;
    private String accessRight;

    public Account(String username, String password, String accessRight) {
        this.username = username;
        this.password = password;
        this.accessRight = accessRight;
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

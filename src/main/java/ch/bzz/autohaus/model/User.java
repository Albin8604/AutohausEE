package ch.bzz.autohaus.model;

public class User {
    private String userUUID;
    private String userName;
    private String password;
    private String userRole;

    public User() {
    }

    public User(String userUUID, String userName, String password, String userRole) {
        this.userUUID = userUUID;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public User setUserUUID(String userUUID) {
        this.userUUID = userUUID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public void logon(){

    }

    public void logoff(){

    }
}

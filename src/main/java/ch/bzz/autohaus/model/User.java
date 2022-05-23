package ch.bzz.autohaus.model;


/**
 * Model class of User
 *
 * @author Albin Smrqaku
 * @since 2022-05-23
 * @version 1.0
 *
 */

public class User {
    private String userUUID;
    private String userName;
    private String password;
    private String userRole;

    /**
     * empty constructor
     */
    public User() {
    }

    /**
     * constructor
     *
     * @param userUUID userUUID of User
     * @param userName userName of User
     * @param password password of User
     * @param userRole userRole of User
     */
    public User(String userUUID,
                String userName,
                String password,
                String userRole) {
        this.userUUID = userUUID;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    /**
     * Used to logon the user
     */
    public void logon(){

    }

    /**
     * Used to logoff the user
     */
    public void logoff(){

    }

    /**
     * gets userUUID
     *
     * @return value of userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * sets userUUID
     *
     * @param userUUID the value to set
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gets userName
     *
     * @return value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets userName
     *
     * @param userName the value to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets password
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     *
     * @param password the value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets userRole
     *
     * @return value of userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * sets userRole
     *
     * @param userRole the value to set
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}

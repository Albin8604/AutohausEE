package ch.bzz.autohaus.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Model class of User
 *
 * @author Albin Smrqaku
 *
 */

public class User {
    @FormParam("id")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89AB][0-9a-f]{3}-[0-9a-f]{12}$")
    private String userUUID;
    @FormParam("userName")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String userName;
    @FormParam("password")
    @NotEmpty
    @Size(min = 8,max = 30)
    private String password;
    @FormParam("userRole")
    @NotEmpty
    @Size(min = 3,max = 30)
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

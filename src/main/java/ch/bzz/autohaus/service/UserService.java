package ch.bzz.autohaus.service;

import ch.bzz.autohaus.assets.Helper;
import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.User;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Webservice for Operations with the User class
 *
 * @author Albin Smrqaku
 */

@Path("user")
public class UserService {
    /**
     * Delivers userList as a JsonArray
     *
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status OK and the userList
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers(
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        List<User> userList = Collections.emptyList();
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            userList = DataHandler.getInstance().readAllUser();
        } else {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(userList)
                .build();
    }

    /**
     * Delivers a user with a specific uuid
     *
     * @param id                uuid of the user
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400 or 404 (depends on if an entity could be found) and the user
     */
    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idUser(
            @QueryParam("id") String id,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);
        User user = null;

        int httpStatus = 200;

        if (loggedInUser != null) {
            try {
                user = DataHandler.getInstance().readUserByUUID(id);
                if (user == null) {
                    httpStatus = 404;
                }
            } catch (Exception exception) {
                httpStatus = 400;
            }
        } else {
            httpStatus = 401;
        }
        return Response
                .status(httpStatus)
                .entity(user)
                .build();
    }

    /**
     * Creates a user
     *
     * @param user              user BeanParam
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200 or 400
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(
            @Valid @BeanParam User user,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForCreate(loggedInUser)) {
                user.setUserUUID(UUID.randomUUID().toString());

                try {
                    DataHandler.getInstance().insertUser(user);
                } catch (Exception exception) {
                    httpStatus = 400;
                }
            } else {
                httpStatus = 403;
            }
        } else {
            httpStatus = 401;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a user
     *
     * @param user              user BeanParam
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400 or 410
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(
            @Valid @BeanParam User user,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForUpdate(loggedInUser)) {
                try {
                    User userToBeUpdated = DataHandler.getInstance().readUserByUUID(user.getUserUUID());
                    if (userToBeUpdated != null) {
                        setAttributes(userToBeUpdated, user);

                        DataHandler.getInstance().updateUser();
                    } else {
                        httpStatus = 410;
                    }
                } catch (Exception exception) {
                    httpStatus = 400;
                }
            } else {
                httpStatus = 403;
            }
        } else {
            httpStatus = 401;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Deletes a user identified by its uuid
     *
     * @param id                uuid of the user
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(
            @QueryParam("id") String id,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForDelete(loggedInUser)){
                try {
                    if (!DataHandler.getInstance().deleteUser(id)) {
                        httpStatus = 410;
                    }
                } catch (Exception exception) {
                    httpStatus = 400;
                }
            }else {
                httpStatus = 403;
            }
        } else {
            httpStatus = 401;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Logs a user in
     *
     * @param username username of the user
     * @param password password of the user
     * @return Response
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus = 200;

        NewCookie usernameCookie = null;
        NewCookie passwordCookie = null;
        NewCookie roleCookie = null;

        User user = DataHandler.getInstance()
                .readUserByLogin(username, password);
        String role = "guest";
        if (user != null) {
            role = user.getUserRole();

            usernameCookie = new NewCookie(
                    "username",
                    Helper.getInstance().encrypt(user.getUserName()),
                    "/",
                    "",
                    "Login-Cookie",
                    600,
                    false
            );

            passwordCookie = new NewCookie(
                    "password",
                    Helper.getInstance().encrypt(user.getPassword()),
                    "/",
                    "",
                    "Login-Cookie",
                    600,
                    false
            );

            roleCookie = new NewCookie(
                    "role",
                    Helper.getInstance().encrypt(user.getUserRole()),
                    "/",
                    "",
                    "Login-Cookie",
                    600,
                    false
            );
        } else {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(role)
                .cookie(
                        usernameCookie,
                        passwordCookie,
                        roleCookie
                )
                .build();
    }

    /**
     * Logs a user out
     *
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoutUser(
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        int httpStatus = 200;

        NewCookie usernameCookie = null;
        NewCookie passwordCookie = null;
        NewCookie roleCookie = null;

        User user = DataHandler.getInstance()
                .readUserByLogin(
                        Helper.getInstance().decrypt(encryptedUsername),
                        Helper.getInstance().decrypt(encryptedPassword)
                );
        if (user != null) {
            usernameCookie = new NewCookie(
                    "username",
                    "",
                    "/",
                    "",
                    "Login-Cookie",
                    1,
                    false
            );

            passwordCookie = new NewCookie(
                    "username",
                    "",
                    "/",
                    "",
                    "Login-Cookie",
                    1,
                    false
            );

            roleCookie = new NewCookie(
                    "role",
                    "",
                    "/",
                    "",
                    "Login-Cookie",
                    1,
                    false
            );
        } else {
            httpStatus = 400;
        }

        return Response
                .status(httpStatus)
                .cookie(
                        usernameCookie,
                        passwordCookie,
                        roleCookie
                )
                .build();
    }

    /**
     * sets the attributes for the user-object
     *
     * @param user             the user-object
     * @param userToBeCopiedOf the user-object that the attributes should be taken of
     */
    private void setAttributes(
            User user,
            User userToBeCopiedOf
    ) {
        if (!user.getUserName().equals(userToBeCopiedOf.getUserName())) {
            user.setUserName(userToBeCopiedOf.getUserName());
        }

        if (!user.getPassword().equals(userToBeCopiedOf.getPassword())) {
            user.setPassword(userToBeCopiedOf.getPassword());
        }

        if (!user.getUserRole().equals(userToBeCopiedOf.getUserRole())) {
            user.setUserRole(userToBeCopiedOf.getUserRole());
        }
    }
}

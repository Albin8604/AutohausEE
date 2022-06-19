package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Kontaktperson;
import ch.bzz.autohaus.model.User;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Webservice for Operations with the User class
 *
 * @author Albin Smrqaku
 *
 */

@Path("user")
public class UserService {
    /**
     * Delivers userList as a JsonArray
     *
     * @return Response with Status OK and the userList
     *
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
        List<User> userList = DataHandler.getInstance().readAllUser();

        return Response
                .status(200)
                .entity(userList)
                .build();
    }

    /**
     * Delivers a user with a specific uuid
     *
     * @param id uuid of the user
     * @return Response with Status 200, 400 or 404 (depends on if an entity could be found) and the user
     *
     */
    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idKontaktperson(
            @QueryParam("id") String id
    ) {
        User user = null;
        int httpStatus = 200;

        try {
            user = DataHandler.getInstance().readUserByUUID(id);
            if (user == null) {
                httpStatus = 404;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity(user)
                .build();
    }

    /**
     * Creates a user
     *
     * @param user user BeanParam
     * @return Response with Status 200 or 400
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(
            @Valid @BeanParam User user
    ) {
        int httpStatus = 200;

        user.setUserUUID(UUID.randomUUID().toString());

        try {
            DataHandler.getInstance().insertUser(user);
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a user
     *
     * @param user user BeanParam
     * @return Response with Status 200, 400 or 410
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(
            @Valid @BeanParam User user
    ) {
        int httpStatus = 200;

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
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Deletes a user identified by its uuid
     *
     * @param id uuid of the user
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(
            @QueryParam("id") String id
    ) {
        int httpStatus = 200;

        try {
            if (!DataHandler.getInstance().deleteUser(id)) {
                httpStatus = 410;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity("")
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

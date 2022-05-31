package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
}

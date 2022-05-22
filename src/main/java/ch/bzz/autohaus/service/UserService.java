package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Kontaktperson;
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
 * @since 2022-05-19
 * @version 1.0
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
     * @return Response with Status OK and the user
     *
     */

    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idKontaktperson(
            @QueryParam("id") String id
    ) {
        User user = DataHandler.getInstance().readUserByUUID(id);
        return Response
                .status(200)
                .entity(user)
                .build();
    }
}

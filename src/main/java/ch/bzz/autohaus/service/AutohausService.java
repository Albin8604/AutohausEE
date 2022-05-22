package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Autohaus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Webservice for Operations with the Autohaus class
 *
 * @author Albin Smrqaku
 * @since 2022-05-19
 * @version 1.0
 *
 */

@Path("autohaus")
public class AutohausService {
    /**
     * Delivers autohausList as a JsonArray
     *
     * @return Response with Status OK and the autohausList
     *
     */

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
        List<Autohaus> autohausList = DataHandler.getInstance().readAllAutohaus();

        return Response
                .status(200)
                .entity(autohausList)
                .build();
    }

    /**
     * Delivers an autohaus with a specific uuid
     *
     * @param id uuid of the autohaus
     * @return Response with Status OK and the autohaus
     *
     */

    @GET
    @Path("autohaus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idAutohaus(
            @QueryParam("id") String id
    ) {
        Autohaus autohaus = DataHandler.getInstance().readAutohausByUUID(id);
        return Response
                .status(200)
                .entity(autohaus)
                .build();
    }
}

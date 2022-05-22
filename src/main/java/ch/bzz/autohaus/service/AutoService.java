package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Webservice for Operations with the Auto class
 *
 * @author Albin Smrqaku
 * @since 2022-05-19
 * @version 1.0
 *
 */

@Path("auto")
public class AutoService {
    /**
     * Delivers autoList as a JsonArray
     *
     * @return Response with Status OK and the autoList
     *
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
        List<Auto> autoList = DataHandler.getInstance().readAllAutos();

        return Response
                .status(200)
                .entity(autoList)
                .build();
    }

    /**
     * Delivers an auto with a specific uuid
     *
     * @param id uuid of the auto
     * @return Response with Status OK and the auto
     *
     */
    @GET
    @Path("auto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idAuto(
            @QueryParam("id") String id
    ) {
        Auto auto = DataHandler.getInstance().readAutoByUUID(id);

        return Response
                .status(200)
                .entity(auto)
                .build();
    }

}

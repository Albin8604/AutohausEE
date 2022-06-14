package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Kontaktperson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Webservice for Operations with the Kontaktperson class
 *
 * @author Albin Smrqaku
 *
 */
@Path("kontaktperson")
public class KontaktpersonService {
    /**
     * Delivers kontaktpersonList as a JsonArray
     *
     * @return Response with Status OK and the kontaktpersonList
     *
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
        List<Kontaktperson> kontaktpersonList = DataHandler.getInstance().readAllKontaktperson();

        return Response
                .status(200)
                .entity(kontaktpersonList)
                .build();
    }

    /**
     * Delivers a kontaktperson with a specific uuid
     *
     * @param id uuid of the konaktperson
     * @return Response with Status 200, 400 or 404 (depends on if an entity could be found) and the kontaktperson
     *
     */
    @GET
    @Path("kontaktperson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idKontaktperson(
            @QueryParam("id") String id
    ) {
        Kontaktperson kontaktperson = null;
        int httpStatus = 200;

        try {
            kontaktperson = DataHandler.getInstance().readKontaktpersonByUUID(id);
            if (kontaktperson == null) {
                httpStatus = 404;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity(kontaktperson)
                .build();
    }
}
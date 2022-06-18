package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Autohaus;

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
import java.util.List;
import java.util.UUID;

/**
 * Webservice for Operations with the Autohaus class
 *
 * @author Albin Smrqaku
 */

@Path("autohaus")
public class AutohausService {
    /**
     * Delivers autohausList as a JsonArray
     *
     * @return Response with Status OK and the autohausList
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
     * @return Response with Status 200, 400 or 404 (depends on if an entity could be found) and the autohaus
     */

    @GET
    @Path("autohaus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idAutohaus(
            @QueryParam("id") String id
    ) {
        Autohaus autohaus = null;
        int httpStatus = 200;

        try {
            autohaus = DataHandler.getInstance().readAutohausByUUID(id);
            if (autohaus == null) {
                httpStatus = 404;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity(autohaus)
                .build();
    }

    /**
     * Creates an autohaus
     *
     * @param autohaus autohaus BeanParam
     * @return Response with Status 200 or 400
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAutohaus(
            @Valid @BeanParam Autohaus autohaus
    ) {
        int httpStatus = 200;

        autohaus.setAutohausUUID(UUID.randomUUID().toString());

        try {
            DataHandler.getInstance().insertAutohaus(autohaus);
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates an autohaus
     *
     * @param autohaus autohaus BeanParam
     * @return Response with Status 200, 400 or 410
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAutohaus(
            @Valid @BeanParam Autohaus autohaus
    ) {
        int httpStatus = 200;

        try {
            Autohaus autohausToBeUpdated = DataHandler.getInstance().readAutohausByUUID(autohaus.getAutohausUUID());
            if (autohausToBeUpdated != null) {
                setAttributes(autohausToBeUpdated, autohaus);

                DataHandler.getInstance().updateAutohaus();
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
     * Deletes an autohaus identified by its uuid
     *
     * @param id uuid of the autohaus
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAutohaus(
            @QueryParam("id") String id
    ) {
        int httpStatus = 200;

        try {
            if (!DataHandler.getInstance().deleteAutohaus(id)) {
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
     * sets the attributes for the autohaus-object
     *
     * @param autohaus             the autohaus-object
     * @param autohausToBeCopiedOf the autohaus-object that the attributes should be taken of
     */
    private void setAttributes(
            Autohaus autohaus,
            Autohaus autohausToBeCopiedOf
    ) {
        if (!autohaus.getAutos().equals(autohausToBeCopiedOf.getAutos())) {
            autohaus.setAutos(autohausToBeCopiedOf.getAutos());
        }

        if (!autohaus.getKontaktpersonen().equals(autohausToBeCopiedOf.getKontaktpersonen())) {
            autohaus.setKontaktpersonen(autohausToBeCopiedOf.getKontaktpersonen());
        }

        if (!autohaus.getInhaber().equals(autohausToBeCopiedOf.getInhaber())) {
            autohaus.setInhaber(autohausToBeCopiedOf.getInhaber());
        }

        if (!autohaus.getStrasse().equals(autohausToBeCopiedOf.getStrasse())) {
            autohaus.setStrasse(autohausToBeCopiedOf.getStrasse());
        }

        if (!autohaus.getNummer().equals(autohausToBeCopiedOf.getNummer())) {
            autohaus.setNummer(autohausToBeCopiedOf.getNummer());
        }

        if (!autohaus.getOrt().equals(autohausToBeCopiedOf.getOrt())) {
            autohaus.setOrt(autohausToBeCopiedOf.getOrt());
        }

        if (!autohaus.getPlz().equals(autohausToBeCopiedOf.getPlz())) {
            autohaus.setPlz(autohausToBeCopiedOf.getPlz());
        }

        if (!autohaus.getGruendung().equals(autohausToBeCopiedOf.getGruendung())) {
            autohaus.setGruendung(autohausToBeCopiedOf.getGruendung());
        }
    }
}

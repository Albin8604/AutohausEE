package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
 * Webservice for Operations with the Auto class
 *
 * @author Albin Smrqaku
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
     * @return Response with Status 200, 400 or 404 (depends on if an entity could be found) and the auto
     *
     */
    @GET
    @Path("auto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idAuto(
            @QueryParam("id") String id
    ) {
        Auto auto = null;
        int httpStatus = 200;

        try {
            auto = DataHandler.getInstance().readAutoByUUID(id);
            if (auto == null) {
                httpStatus = 404;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity(auto)
                .build();
    }

    /**
     * Creates an auto
     *
     * @param auto auto BeanParam
     * @return Response with Status 200 or 400
     *
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAuto(
            @Valid @BeanParam Auto auto
    ) {
        int httpStatus = 200;

        auto.setAutoUUID(UUID.randomUUID().toString());

        try {
            DataHandler.getInstance().insertAuto(auto);
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates an auto
     *
     * @param auto auto BeanParam
     * @return Response with Status 200, 400 or 410
     *
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAuto(
            @Valid @BeanParam Auto auto
    ) {
        int httpStatus = 200;

        try {
            Auto autoToBeUpdated = DataHandler.getInstance().readAutoByUUID(auto.getAutoUUID());
            if (autoToBeUpdated != null){
                setAttributes(autoToBeUpdated,auto);

                DataHandler.getInstance().updateAuto();
            }else {
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
     * Deletes an auto identified by its uuid
     *
     * @param id uuid of the auto
     * @return Response
     *
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAuto(
            @QueryParam("id") String id
    ) {
        int httpStatus = 200;

        try {
            if (!DataHandler.getInstance().deleteAuto(id)) {
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
     * sets the attributes for the auto-object
     * @param auto  the auto-object
     * @param autoToBeCopiedOf  the auto-object that the attributes should be taken of
     */
    private void setAttributes(
            Auto auto,
            Auto autoToBeCopiedOf
    ) {
        auto.setMarke(autoToBeCopiedOf.getMarke());
        auto.setModell(autoToBeCopiedOf.getModell());
        auto.setFarbcodeHex(autoToBeCopiedOf.getFarbcodeHex());
        auto.setArt(autoToBeCopiedOf.getArt());
        auto.setPs(autoToBeCopiedOf.getPs());
        auto.setNm(autoToBeCopiedOf.getNm());
        auto.setOccasion(autoToBeCopiedOf.getOccasion());
        auto.setPreis(autoToBeCopiedOf.getPreis());
        auto.setBilder(autoToBeCopiedOf.getBilder());
    }
}

package ch.bzz.autohaus.service;

import ch.bzz.autohaus.assets.Helper;
import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.User;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Webservice for Operations with the Auto class
 *
 * @author Albin Smrqaku
 */

@Path("auto")
public class AutoService {
    /**
     * Delivers autoList as a JsonArray
     *
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 401 or 403 and the autoList
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos(
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        List<Auto> autoList = Collections.emptyList();
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForRead(loggedInUser)) {
                autoList = DataHandler.getInstance().readAllAutos();
            } else {
                httpStatus = 403;
            }
        } else {
            httpStatus = 401;
        }


        return Response
                .status(httpStatus)
                .entity(autoList)
                .build();
    }

    /**
     * Delivers an auto with a specific uuid
     *
     * @param id                uuid of the auto
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400, 401, 403 or 404 (depends on if an entity could be found) and the auto
     */
    @GET
    @Path("auto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idAuto(
            @QueryParam("id") String id,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);
        Auto auto = null;

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForRead(loggedInUser)) {
                try {
                    auto = DataHandler.getInstance().readAutoByUUID(id);
                    if (auto == null) {
                        httpStatus = 404;
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
                .entity(auto)
                .build();
    }

    /**
     * Creates an auto
     *
     * @param auto              auto BeanParam
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400, 401 or 403
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAuto(
            @Valid @BeanParam Auto auto,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForCreate(loggedInUser)) {
                auto.setAutoUUID(UUID.randomUUID().toString());

                try {
                    DataHandler.getInstance().insertAuto(auto);
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
     * updates an auto
     *
     * @param auto              auto BeanParam
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400, 401, 403 or 410
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAuto(
            @Valid @BeanParam Auto auto,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForUpdate(loggedInUser)) {
                try {
                    Auto autoToBeUpdated = DataHandler.getInstance().readAutoByUUID(auto.getAutoUUID());
                    if (autoToBeUpdated != null) {
                        setAttributes(autoToBeUpdated, auto);

                        DataHandler.getInstance().updateAuto();
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
     * Deletes an auto identified by its uuid
     *
     * @param id                uuid of the auto
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAuto(
            @QueryParam("id") String id,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForDelete(loggedInUser)) {
                try {
                    if (!DataHandler.getInstance().deleteAuto(id)) {
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
     * sets the attributes for the auto-object
     *
     * @param auto             the auto-object
     * @param autoToBeCopiedOf the auto-object that the attributes should be taken of
     */
    private void setAttributes(
            Auto auto,
            Auto autoToBeCopiedOf
    ) {
        if (!auto.getMarke().equals(autoToBeCopiedOf.getMarke())) {
            auto.setMarke(autoToBeCopiedOf.getMarke());
        }

        if (!auto.getModell().equals(autoToBeCopiedOf.getModell())) {
            auto.setModell(autoToBeCopiedOf.getModell());
        }

        if (!auto.getFarbcodeHex().equals(autoToBeCopiedOf.getFarbcodeHex())) {
            auto.setFarbcodeHex(autoToBeCopiedOf.getFarbcodeHex());
        }

        if (!auto.getArt().equals(autoToBeCopiedOf.getArt())) {
            auto.setArt(autoToBeCopiedOf.getArt());
        }

        if (!auto.getPs().equals(autoToBeCopiedOf.getPs())) {
            auto.setPs(autoToBeCopiedOf.getPs());
        }

        if (!auto.getNm().equals(autoToBeCopiedOf.getNm())) {
            auto.setNm(autoToBeCopiedOf.getNm());
        }

        if (!auto.getOccasion().equals(autoToBeCopiedOf.getOccasion())) {
            auto.setOccasion(autoToBeCopiedOf.getOccasion());
        }

        if (!auto.getPreis().equals(autoToBeCopiedOf.getPreis())) {
            auto.setPreis(autoToBeCopiedOf.getPreis());
        }

        if (!auto.getBilder().equals(autoToBeCopiedOf.getBilder())) {
            auto.setBilder(autoToBeCopiedOf.getBilder());
        }
    }
}

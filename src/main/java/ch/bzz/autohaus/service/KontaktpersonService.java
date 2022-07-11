package ch.bzz.autohaus.service;

import ch.bzz.autohaus.assets.Helper;
import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Kontaktperson;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Webservice for Operations with the Kontaktperson class
 *
 * @author Albin Smrqaku
 */
@Path("kontaktperson")
public class KontaktpersonService {
    /**
     * Delivers kontaktpersonList as a JsonArray
     *
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status OK and the kontaktpersonList
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos(
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        List<Kontaktperson> kontaktpersonList = Collections.emptyList();
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            kontaktpersonList = DataHandler.getInstance().readAllKontaktperson();
        } else {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(kontaktpersonList)
                .build();
    }

    /**
     * Delivers a kontaktperson with a specific uuid
     *
     * @param id                uuid of the konaktperson
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400 or 404 (depends on if an entity could be found) and the kontaktperson
     */
    @GET
    @Path("kontaktperson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idKontaktperson(
            @QueryParam("id") String id,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);
        Kontaktperson kontaktperson = null;

        int httpStatus = 200;

        if (loggedInUser != null) {
            try {
                kontaktperson = DataHandler.getInstance().readKontaktpersonByUUID(id);
                if (kontaktperson == null) {
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
                .entity(kontaktperson)
                .build();
    }

    /**
     * Creates an kontaktperson
     *
     * @param kontaktperson     kontaktperson BeanParam
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200 or 400
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createKontaktperson(
            @Valid @BeanParam Kontaktperson kontaktperson,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForCreate(loggedInUser)) {
                kontaktperson.setKontaktpersonUUID(UUID.randomUUID().toString());

                try {
                    DataHandler.getInstance().insertKontaktperson(kontaktperson);
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
     * updates an kontaktperson
     *
     * @param kontaktperson     kontaktperson BeanParam
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response with Status 200, 400 or 410
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKontaktperson(
            @Valid @BeanParam Kontaktperson kontaktperson,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForUpdate(loggedInUser)) {
                try {
                    Kontaktperson kontaktpersonToBeUpdated = DataHandler.getInstance().readKontaktpersonByUUID(kontaktperson.getKontaktpersonUUID());
                    if (kontaktpersonToBeUpdated != null) {
                        setAttributes(kontaktpersonToBeUpdated, kontaktperson);

                        DataHandler.getInstance().updateKontaktperson();
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
     * Deletes an kontaktperson identified by its uuid
     *
     * @param id                uuid of the kontaktperson
     * @param encryptedUsername encrypted username from cookie
     * @param encryptedPassword encrypted password from cookie
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKontaktperson(
            @QueryParam("id") String id,
            @CookieParam("username") String encryptedUsername,
            @CookieParam("password") String encryptedPassword
    ) {
        User loggedInUser = Helper.getInstance().getUserByEncryptedLogin(encryptedUsername, encryptedPassword);

        int httpStatus = 200;

        if (loggedInUser != null) {
            if (Helper.getInstance().isUserValidForDelete(loggedInUser)) {
                try {
                    if (!DataHandler.getInstance().deleteKontaktperson(id)) {
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
     * sets the attributes for the kontaktperson-object
     *
     * @param kontaktperson             the kontaktperson-object
     * @param kontaktpersonToBeCopiedOf the kontaktperson-object that the attributes should be taken of
     */
    private void setAttributes(
            Kontaktperson kontaktperson,
            Kontaktperson kontaktpersonToBeCopiedOf
    ) {
        if (!kontaktperson.getNachname().equals(kontaktpersonToBeCopiedOf.getNachname())) {
            kontaktperson.setNachname(kontaktpersonToBeCopiedOf.getNachname());
        }

        if (!kontaktperson.getVorname().equals(kontaktpersonToBeCopiedOf.getVorname())) {
            kontaktperson.setVorname(kontaktpersonToBeCopiedOf.getVorname());
        }

        if (!kontaktperson.getEmail().equals(kontaktpersonToBeCopiedOf.getEmail())) {
            kontaktperson.setEmail(kontaktpersonToBeCopiedOf.getEmail());
        }

        if (!kontaktperson.getTel().equals(kontaktpersonToBeCopiedOf.getTel())) {
            kontaktperson.setTel(kontaktpersonToBeCopiedOf.getTel());
        }

        if (!Arrays.equals(kontaktperson.getBild(), kontaktpersonToBeCopiedOf.getBild())) {
            kontaktperson.setBild(kontaktpersonToBeCopiedOf.getBild());
        }

        if (!kontaktperson.getGebDat().equals(kontaktpersonToBeCopiedOf.getGebDat())) {
            kontaktperson.setGebDat(kontaktpersonToBeCopiedOf.getGebDat());
        }
    }
}

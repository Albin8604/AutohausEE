package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Kontaktperson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("kontaktperson")
public class KontaktpersonController {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
        List<Kontaktperson> kontaktpersonList = DataHandler.getInstance().readAllKontaktperson();

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    @GET
    @Path("kontaktperson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idKontaktperson(
            @QueryParam("id") String id
    ) {
        Kontaktperson kontaktperson = DataHandler.getInstance().readKontaktpersonByUUID(id);
        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }
}

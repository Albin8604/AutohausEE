package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("autohaus")
public class AutohausController {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
        List<Autohaus> autohausList = DataHandler.getInstance().readAllAutohaus();

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    @GET
    @Path("autohaus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idAutohaus(
            @QueryParam("id") String id
    ) {
        Autohaus autohaus = DataHandler.getInstance().readAutohausByUUID(id);
        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }
}

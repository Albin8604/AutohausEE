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

@Path("auto")
public class AutoController {
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

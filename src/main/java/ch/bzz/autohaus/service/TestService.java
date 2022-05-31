package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TestService
 *
 * @author Albin Smrqaku
 *
 */
@Path("test")
public class TestService {

    /**
     * confirms the application runs
     *
     * @return message
     */
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    /**
     * restores the json-files
     *
     * @return Response
     */
    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restoreAll() {
        try {
            restore("autoJSON","autohausJSON","kontaktpersonJSON","userJSON");
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataHandler.getInstance().clearLists();
        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }

    private void restore(String... properties) throws IOException {
        for (String property : properties) {
            java.nio.file.Path path = Paths.get(Config.getProperty(property));
            String filename = path.getFileName().toString();
            String folder = path.getParent().toString();

            byte[] jsonFiles = Files.readAllBytes(Paths.get(folder, "backup", filename));
            FileOutputStream fileOutputStream = new FileOutputStream(Config.getProperty(property));
            fileOutputStream.write(jsonFiles);
        }
    }
}


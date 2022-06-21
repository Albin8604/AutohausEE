package ch.bzz.autohaus.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Configure the web services and properties
 *
 * @author Albin Smrqaku
 *
 */


@ApplicationPath("/resource")

public class Config extends Application {
    private static final String PROPERTIES_PATH =
            "C:\\Users\\41765\\Desktop\\Albin\\Lehre\\BZZ\\Informatik\\M133\\AutohausEE\\src\\main\\webapp\\autohaus.properties";
    private static Properties properties = null;
    /**
     * define all provider classes
     *
     * @return set of classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> providers = new HashSet<>();
        providers.add(AutoService.class);
        providers.add(AutohausService.class);
        providers.add(KontaktpersonService.class);
        providers.add(UserService.class);
        return providers;
    }

    /**
     * Gets the value of a property
     *
     * @param property the key of the property to be read
     * @return the value of the property
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }



    /**
     * reads the properties file
     *
     */
    private static void readProperties() {

        InputStream inputStream;
        try {
            inputStream = Files.newInputStream(Paths.get(PROPERTIES_PATH));
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Sets the properties
     *
     * @param properties the value to set
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}
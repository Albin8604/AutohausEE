package ch.bzz.autohaus.data;

import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;
import ch.bzz.autohaus.model.Kontaktperson;
import ch.bzz.autohaus.model.User;
import ch.bzz.autohaus.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes the data in the JSON-files
 *
 * @author Albin Smrqaku
 * @since 2022-05-19
 * @version 1.0
 *
 */

public class DataHandler {
    private static DataHandler instance = null;
    private List<Auto> autoList;
    private List<Autohaus> autohausList;
    private List<Kontaktperson> kontaktpersonList;
    private List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setautohausList(new ArrayList<>());
        readAutohausJSON();
        setautoList(new ArrayList<>());
        readAutoJSON();
        setKontaktpersonList(new ArrayList<>());
        readKontaktpersonJSON();
    }

    /**
     * gets the only instance of this class (Singleton pattern)
     *
     * @return instance of this class
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Autos
     *
     * @return list of Autos
     */
    public List<Auto> readAllAutos() {
        return getautoList();
    }

    /**
     * reads an Auto by its uuid
     *
     * @param autoUUID
     * @return the Auto (null=not found)
     */
    public Auto readAutoByUUID(String autoUUID) {
        Auto auto = null;
        for (Auto entry : getautoList()) {
            if (entry.getAutoUUID().equals(autoUUID)) {
                auto = entry;
            }
        }
        return auto;
    }

    /**
     * reads all Autohaus
     *
     * @return list of Autohaus
     */
    public List<Autohaus> readAllAutohaus() {

        return getautohausList();
    }

    /**
     * reads an Autohaus by its uuid
     *
     * @param autohausUUID
     * @return the Autohaus (null=not found)
     */
    public Autohaus readAutohausByUUID(String autohausUUID) {
        Autohaus autohaus = null;
        for (Autohaus entry : getautohausList()) {
            if (entry.getAutohausUUID().equals(autohausUUID)) {
                autohaus = entry;
            }
        }
        return autohaus;
    }

    /**
     * reads all Kontaktperson
     *
     * @return list of Kontaktperson
     */
    public List<Kontaktperson> readAllKontaktperson() {

        return getKontaktpersonlist();
    }

    /**
     * reads a Kontaktperson by its uuid
     *
     * @param kontaktpersonUUID
     * @return the Kontaktperson (null=not found)
     */
    public Kontaktperson readKontaktpersonByUUID(String kontaktpersonUUID) {
        Kontaktperson kontaktperson = null;
        for (Kontaktperson entry : getKontaktpersonlist()) {
            if (entry.getKontaktpersonUUID().equals(kontaktpersonUUID)) {
                kontaktperson = entry;
            }
        }
        return kontaktperson;
    }

    /**
     * reads all User
     *
     * @return list of User
     */
    public List<User> readAllUser() {

        return getUserList();
    }

    /**
     * reads a User by its uuid
     *
     * @param userUUID
     * @return the User (null=not found)
     */
    public User readUserByUUID(String userUUID) {
        User user = null;
        for (User entry : getUserList()) {
            if (entry.getUserUUID().equals(userUUID)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * reads the Autos from the JSON-file
     */
    private void readAutoJSON() {
        try {
            String path = Config.getProperty("autoJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Auto[] Autos = objectMapper.readValue(jsonData, Auto[].class);
            for (Auto Auto : Autos) {
                getautoList().add(Auto);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Autohaus from the JSON-file
     */
    private void readAutohausJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("autohausJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Autohaus[] autohaeuser = objectMapper.readValue(jsonData, Autohaus[].class);
            for (Autohaus autohaus : autohaeuser) {
                getautohausList().add(autohaus);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Kontaktpersonen from the JSON-file
     */
    private void readKontaktpersonJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("kontaktpersonJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Kontaktperson[] kontaktpersonen = objectMapper.readValue(jsonData, Kontaktperson[].class);
            for (Kontaktperson kontaktperson : kontaktpersonen) {
                getKontaktpersonlist().add(kontaktperson);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Users from the JSON-file
     */
    private void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets autoList
     *
     * @return value of autoList
     */
    private List<Auto> getautoList() {
        return autoList;
    }

    /**
     * sets autoList
     *
     * @param autoList the value to set
     */
    private void setautoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    /**
     * gets autohausList
     *
     * @return value of autohausList
     */
    private List<Autohaus> getautohausList() {
        return autohausList;
    }

    /**
     * sets autohausList
     *
     * @param autohausList the value to set
     */
    private void setautohausList(List<Autohaus> autohausList) {
        this.autohausList = autohausList;
    }

    /**
     * gets kontaktpersonList
     *
     * @return value of kontaktpersonList
     */
    private List<Kontaktperson> getKontaktpersonlist() {
        return kontaktpersonList;
    }

    /**
     * sets kontaktpersonList
     *
     * @param kontaktpersonList the value to set
     */
    private void setKontaktpersonList(List<Kontaktperson> kontaktpersonList) {
        this.kontaktpersonList = kontaktpersonList;
    }

    /**
     * gets userList
     *
     * @return value of userList
     */
    private List<User> getUserList() {
        return userList;
    }

    /**
     * sets userList
     *
     * @param userList the value to set
     */
    private void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
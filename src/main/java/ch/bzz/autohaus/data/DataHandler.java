package ch.bzz.autohaus.data;

import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;
import ch.bzz.autohaus.model.Kontaktperson;
import ch.bzz.autohaus.model.User;
import ch.bzz.autohaus.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes the data in the JSON-files
 *
 * @author Albin Smrqaku
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
        setUserList(new ArrayList<>());
        readUserJSON();
        setAutoList(new ArrayList<>());
        readAutoJSON();
        setKontaktpersonList(new ArrayList<>());
        readKontaktpersonJSON();
        setAutohausList(new ArrayList<>());
        readAutohausJSON();
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
     * clears the lists
     */
    public void clearLists() {
        getUserList().clear();
        getAutoList().clear();
        getKontaktpersonlist().clear();
        getAutohausList().clear();
    }

    /**
     * reads all Autos
     *
     * @return list of Autos
     */
    public List<Auto> readAllAutos() {
        return getAutoList();
    }

    /**
     * reads an Auto by its uuid
     *
     * @param autoUUID
     * @return the Auto (null=not found)
     */
    public Auto readAutoByUUID(String autoUUID) {
        Auto auto = null;
        for (Auto entry : getAutoList()) {
            if (entry.getAutoUUID().equals(autoUUID)) {
                auto = entry;
            }
        }
        return auto;
    }

    /**
     * inserts a new auto into the autoList
     *
     * @param auto the auto to be saved
     */
    public void insertAuto(Auto auto) {
        getAutoList().add(auto);
        writeAutoJSON();
    }

    /**
     * updates the autoList
     */
    public void updateAuto() {
        writeAutoJSON();
    }

    /**
     * deletes an auto identified by the UUID
     *
     * @param autoUUID the key
     * @return success=true/false
     */
    public boolean deleteAuto(String autoUUID) {
        Auto auto = readAutoByUUID(autoUUID);
        if (auto != null) {
            getAutoList().remove(auto);
            writeAutoJSON();
            return true;
        }
        return false;
    }

    /**
     * reads all Autohaus
     *
     * @return list of Autohaus
     */
    public List<Autohaus> readAllAutohaus() {

        return getAutohausList();
    }

    /**
     * reads an Autohaus by its uuid
     *
     * @param autohausUUID
     * @return the Autohaus (null=not found)
     */
    public Autohaus readAutohausByUUID(String autohausUUID) {
        Autohaus autohaus = null;
        for (Autohaus entry : getAutohausList()) {
            if (entry.getAutohausUUID().equals(autohausUUID)) {
                autohaus = entry;
            }
        }
        return autohaus;
    }

    /**
     * inserts a new autohaus into the autohausList
     *
     * @param autohaus the autohaus to be saved
     */
    public void insertAutohaus(Autohaus autohaus) {
        getAutohausList().add(autohaus);
        writeAutohausJSON();
    }

    /**
     * updates the autohausList
     */
    public void updateAutohaus() {
        writeAutohausJSON();
    }

    /**
     * deletes an autohaus identified by the UUID
     *
     * @param autohausUUID the key
     * @return success=true/false
     */
    public boolean deleteAutohaus(String autohausUUID) {
        Autohaus autohaus = readAutohausByUUID(autohausUUID);
        if (autohaus != null) {
            getAutohausList().remove(autohaus);
            writeAutohausJSON();
            return true;
        }
        return false;
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
     * inserts a new kontaktperson into the kontaktpersonList
     *
     * @param kontaktperson the kontaktperson to be saved
     */
    public void insertKontaktperson(Kontaktperson kontaktperson) {
        getKontaktpersonlist().add(kontaktperson);
        writeKontaktpersonJSON();
    }

    /**
     * updates the kontaktpersonList
     */
    public void updateKontaktperson() {
        writeKontaktpersonJSON();
    }

    /**
     * deletes an kontaktperson identified by the UUID
     *
     * @param kontaktpersonUUID the key
     * @return success=true/false
     */
    public boolean deleteKontaktperson(String kontaktpersonUUID) {
        Kontaktperson kontaktperson = readKontaktpersonByUUID(kontaktpersonUUID);
        if (kontaktperson != null) {
            getKontaktpersonlist().remove(kontaktperson);
            writeKontaktpersonJSON();
            return true;
        }
        return false;
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
     * inserts a new user into the userList
     *
     * @param user the user to be saved
     */
    public void insertUser(User user) {
        getUserList().add(user);
        writeUserJSON();
    }

    /**
     * updates the userList
     */
    public void updateUser() {
        writeUserJSON();
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
                getAutoList().add(Auto);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the autoList to the JSON-file
     */
    private void writeAutoJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String pathToJson = Config.getProperty("autoJSON");
        try {
            fileOutputStream = new FileOutputStream(pathToJson);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAutoList());
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
                getAutohausList().add(autohaus);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the autohausList to the JSON-file
     */
    private void writeAutohausJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String pathToJson = Config.getProperty("autohausJSON");
        try {
            fileOutputStream = new FileOutputStream(pathToJson);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAutohausList());
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
     * writes the kontaktpersonList to the JSON-file
     */
    private void writeKontaktpersonJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String pathToJson = Config.getProperty("kontaktpersonJSON");
        try {
            fileOutputStream = new FileOutputStream(pathToJson);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getKontaktpersonlist());
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
     * writes the userList to the JSON-file
     */
    private void writeUserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String pathToJson = Config.getProperty("userJSON");
        try {
            fileOutputStream = new FileOutputStream(pathToJson);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getUserList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * gets autoList
     *
     * @return value of autoList
     */
    private List<Auto> getAutoList() {
        return autoList;
    }

    /**
     * sets autoList
     *
     * @param autoList the value to set
     */
    private void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    /**
     * gets autohausList
     *
     * @return value of autohausList
     */
    private List<Autohaus> getAutohausList() {
        return autohausList;
    }

    /**
     * sets autohausList
     *
     * @param autohausList the value to set
     */
    private void setAutohausList(List<Autohaus> autohausList) {
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
package ch.bzz.autohaus.assets;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.User;
import ch.bzz.autohaus.service.Config;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Helper class, in which methods are defined that can helps multiple classes
 *
 * @author Albin Smrqaku
 */
public class Helper {
    private static Helper instance = null;
    private static final String FORMAT = "dd.MM.yyyy";

    private Helper() {
    }

    /**
     * returns the instace of this calss
     *
     * @return instance of this class
     */
    public static Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }

    /**
     * converts text into localdate
     *
     * @param text text to be converted to localdate
     * @return localdate from text
     */
    public LocalDate textToLocalDate(String text) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(FORMAT));
    }

    /**
     * converts localdate to text with defined format (see at the top of the class)
     *
     * @param localDate date to be formatted
     * @return formatted date
     */
    public String localDateToText(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(FORMAT));
    }

    /**
     * hashes given password
     *
     * @param passwordToHash
     * @return hashed password
     */
    public String hashPassword(String passwordToHash) {
        String hashedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    /**
     * encrypts given text
     *
     * @param textToBeEncrypted
     * @return encrypted text
     */
    public String encrypt(String textToBeEncrypted) {
        try {
            return Base64.getEncoder()
                    .encodeToString(
                            initCipher(Cipher.ENCRYPT_MODE).doFinal(textToBeEncrypted.getBytes(StandardCharsets.UTF_8))
                    );
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
        }
        return "";
    }

    /**
     * decrypts given text
     *
     * @param textToBeDecrypted
     * @return decrypted text
     */
    public String decrypt(String textToBeDecrypted) {
        try {
            return new String(
                    initCipher(Cipher.DECRYPT_MODE)
                            .doFinal(Base64.getDecoder()
                                    .decode(textToBeDecrypted))
            );
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return "";
    }

    /**
     * initialises the cipher
     *
     * @param cipherMode the mode that should be use (encrypt or decrypt)
     * @return initialised cipher
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    private Cipher initCipher(int cipherMode) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(
                Config.getProperty("key").toCharArray(),
                Config.getProperty("salt").getBytes(),
                65536,
                256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(cipherMode, secretKey, ivspec);
        return cipher;
    }

    /**
     * Returns logged in user
     *
     * @param encryptedUsername encrypted username
     * @param encryptedPassword encrypted password
     * @return user with this login credentials
     */
    public User getUserByEncryptedLogin(String encryptedUsername, String encryptedPassword) {
        return DataHandler.getInstance().readUserByLogin(
                Helper.getInstance().decrypt(encryptedUsername),
                Helper.getInstance().decrypt(encryptedPassword)
        );
    }

    /**
     * defines which roles are valid for creation of something and returns if the user is eligible to do it or not
     * @param loggedInUser logged in user
     * @return is user valid for creation
     */
    public boolean isUserValidForCreate(User loggedInUser) {
        final List<String> eligibleRoles = Arrays.asList("owner", "admin");

        return hasUserOneOfRoles(loggedInUser, eligibleRoles);
    }

    /**
     * defines which roles are valid for updating of something and returns if the user is eligible to do it or not
     * @param loggedInUser logged in user
     * @return is user valid for update
     */
    public boolean isUserValidForUpdate(User loggedInUser) {
        return isUserValidForCreate(loggedInUser);
    }

    /**
     * defines which roles are valid for deletion of something and returns if the user is eligible to do it or not
     * @param loggedInUser logged in user
     * @return is user valid for deletion
     */
    public boolean isUserValidForDelete(User loggedInUser) {
        final List<String> eligibleRoles = Collections.singletonList("owner");

        return hasUserOneOfRoles(loggedInUser, eligibleRoles);
    }

    /**
     * checks if one of the defined roles are assigned to the user
     *
     * @param loggedInUser logged in user
     * @param roles roles with user needs to get checked
     * @return if user has one of the defined roles
     */
    private boolean hasUserOneOfRoles(User loggedInUser, List<String> roles) {
        for (String eligibleRole : roles) {
            if (loggedInUser.getUserRole().equalsIgnoreCase(eligibleRole)) {
                return true;
            }
        }
        return false;
    }
}
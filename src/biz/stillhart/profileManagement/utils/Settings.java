package biz.stillhart.profileManagement.utils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * These are some settings
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class Settings {

    /**
     * The public home page (without .xhtml)
     */
    public static final String PUBLIC_HOME = "index";
    /**
     * Pages that are public (with .xhtml)
     * Most visited pages should be at the beginning
     */
    public static final List<String> PUBLIC_PAGES = Arrays.asList("index.xhtml", "recoverSend.xhtml", "recoverSet.xhtml");
    /**
     * The private home page (without .xhtml)
     */
    public static final String PRIVATE_HOME = "profile";
    public static String DB_HOST;
    public static int DB_PORT;
    public static String DB_USER_DN;
    public static String DB_LOGIN;
    public static String DB_PASSWORD;
    /**
     * Number of login attempts
     */
    public static int LOGIN_ATTEMPTS;

    /**
     * If the user gets locked, how long should he be locked (in seconds)
     */
    public static int SECONDS_LOCKED;

    /**
     * Mail log in
     */
    public static String RECOVER_LOGIN_HOST;
    public static String RECOVER_LOGIN_MAIL;
    public static String RECOVER_LOGIN_USER;
    public static String RECOVER_LOGIN_PASSWORD;

    /**
     * The html code for the recover mail
     * ~~username~~ will get replaced with the username
     * ~~link~~ will get replaced with the recover link
     */
    public static String RECOVER_MAIL;


    /**
     * Recover page ( Full length + ?code=)
     */
    public static String RECOVER_LINK;

    /**
     * Number of recover attempts
     */
    public static int MAIL_RECOVER_ATTEMPTS;

    /**
     * recover lock in seconds
     */
    public static int MAIL_RECOVER_LOCK_SECONDS;

    /**
     * Default profile picture path
     */
    public static String DEFAULT_PROFILE_IMAGE_PATH;

    /**
     * new profile picture path
     */
    public static String NEW_PROFILE_IMAGE_PATH;


    /**
     * Construct the settings
     */
    public Settings() {
        readFile();
    }

    /**
     * Read settings from file
     */
    private void readFile() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("/home/benutzer/Downloads/Profil/config.properties");

            prop.load(input);
            Settings.DB_HOST = prop.getProperty("DB_HOST");
            Settings.DB_PORT = Integer.parseInt(prop.getProperty("DB_PORT"));
            Settings.DB_USER_DN = prop.getProperty("DB_USER_DN");
            Settings.DB_LOGIN = prop.getProperty("DB_LOGIN");
            Settings.DB_PASSWORD = prop.getProperty("DB_PASSWORD");

            Settings.LOGIN_ATTEMPTS = Integer.parseInt(prop.getProperty("LOGIN_ATTEMPTS"));
            Settings.SECONDS_LOCKED = Integer.parseInt(prop.getProperty("SECONDS_LOCKED"));

            Settings.RECOVER_LOGIN_HOST = prop.getProperty("RECOVER_LOGIN_HOST");
            Settings.RECOVER_LOGIN_MAIL = prop.getProperty("RECOVER_LOGIN_MAIL");
            Settings.RECOVER_LOGIN_USER = prop.getProperty("RECOVER_LOGIN_USER");
            Settings.RECOVER_LOGIN_PASSWORD = prop.getProperty("RECOVER_LOGIN_PASSWORD");

            Settings.RECOVER_LINK = prop.getProperty("RESET_LINK");
            Settings.RECOVER_MAIL = prop.getProperty("RESET_MAIL");
            Settings.MAIL_RECOVER_ATTEMPTS = Integer.parseInt(prop.getProperty("MAIL_RECOVER_ATTEMPTS"));
            Settings.MAIL_RECOVER_LOCK_SECONDS = Integer.parseInt(prop.getProperty("MAIL_RECOVER_LOCK_SECONDS"));

            Settings.DEFAULT_PROFILE_IMAGE_PATH = prop.getProperty("DEFAULT_PROFILE_IMAGE_PATH");
            Settings.NEW_PROFILE_IMAGE_PATH = prop.getProperty("NEW_PROFILE_IMAGE_PATH");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


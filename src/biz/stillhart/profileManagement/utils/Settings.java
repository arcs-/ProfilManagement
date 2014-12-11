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
    public static String DATABASE;
    public static String BASE_DN;
    public static String USER_DN;
    public static String USER_PASSWORD;
    /**
     * Number of login attempts
     */
    public static int LOGIN_ATTEMPTS;

    /**
     * If the user gets locked, how long should he be locked (in seconds)
     */
    public static int SECONDS_LOCKED;

    /**
     * The html code for the recover mail
     * ~~username~~ will get replaced with the username
     * ~~link~~ will get replaced with the recover link
     */
    //public static String RECOVER_MAIL = "<body style=margin:0;background-color:#f6f9fb><table style=\"@import url(https://fonts.googleapis.com/css?family=Cabin:400,700,400italic,700italic);font-family:Cabin,sans-serif;border-spacing:0;width:100%\"><tr><th><td style=\"padding:50px 0;width:446px\"><center><h1 style=text-align:center>Hello ~~username~~</h1></center><p>We heard that you lost your password for the BZZ profile page. Sorry about that!<br><br>Use the button below to recover your password</p><th><tr style=background-color:#dae6ee;text-align:center><td><td style=\"padding:60px 0\"><a style=display:inline;width:300px;background:#008bcb;color:#fff;border:0;padding:4%;font-size:19px;cursor:pointer;text-decoration:none href=~~link~~ target=_blank tabindex=-1 rel=external>Recover your Password</a><br><br><br><a style=text-decoration:none href=~~link~~ target=_blank tabindex=-1 rel=external>~~link~~</a><td><tr><td><td style=\"padding:30px 0;width:400px;color:#aeaeae\"><p>If you didn't asked for a password recovery, please ignore this mail</p><td></table>";
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


    public Settings() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("/home/benutzer/Downloads/Profil/config.properties");

            prop.load(input);
            Settings.DATABASE = prop.getProperty("DATABASE");
            Settings.BASE_DN = prop.getProperty("BASE_DN");
            Settings.USER_DN = prop.getProperty("USER_DN");
            Settings.USER_PASSWORD = prop.getProperty("USER_PASSWORD");

            Settings.LOGIN_ATTEMPTS = Integer.parseInt(prop.getProperty("LOGIN_ATTEMPTS"));
            Settings.SECONDS_LOCKED = Integer.parseInt(prop.getProperty("SECONDS_LOCKED"));

            Settings.RECOVER_LINK = prop.getProperty("RECOVER_LINK");
            Settings.RECOVER_MAIL = prop.getProperty("RECOVER_MAIL");
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


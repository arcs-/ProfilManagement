package biz.stillhart.profileManagement.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * These are some settings
 */
public class Settings {

    /**
     * The public home page (without .xhtml)
     */
    public static final String PUBLIC_HOME = "index";

    /**
     * Pages that are public (with .xhtml)
     * Most visited pages should be at the beginning
     */
    public static final List<String> PUBLIC_PAGES = Arrays.asList("index.xhtml", "recoverSend.xhtml", "newPassword.xhtml");

    /**
     * The private home page (without .xhtml)
     */
    public static final String PRIVATE_HOME = "profile";

    /**
     * Number of login attempts
     */
    public static final int LOGIN_ATTEMPTS = 3;

    /**
     * If the user gets locked, how long should he be locked (in seconds)
     */
    public static final int SECONDS_LOCKED = 5;

    /**
     * The html code for the recover mail
     * ~~username~~ will get replaced with the username
     * ~~link~~ will get replaced with the recover link
     */
    public static final String RECOVER_MAIL = "<body style=margin:0;background-color:#f6f9fb><table style=\"@import url(https://fonts.googleapis.com/css?family=Cabin:400,700,400italic,700italic);font-family:Cabin,sans-serif;border-spacing:0;width:100%\"><tr><th><td style=\"padding:50px 0;width:446px\"><center><h1 style=text-align:center>Hello ~~username~~</h1></center><p>We heard that you lost your password for the BZZ profile page. Sorry about that!<br><br>Use the button below to recover your password</p><th><tr style=background-color:#dae6ee;text-align:center><td><td style=\"padding:60px 0\"><a style=display:inline;width:300px;background:#008bcb;color:#fff;border:0;padding:4%;font-size:19px;cursor:pointer;text-decoration:none href=~~link~~ target=_blank tabindex=-1 rel=external>Recover your Password</a><br><br><br><a style=text-decoration:none href=~~link~~ target=_blank tabindex=-1 rel=external>~~link~~</a><td><tr><td><td style=\"padding:30px 0;width:400px;color:#aeaeae\"><p>If you didn't asked for a password recovery, please ignore this mail</p><td></table>";


    /**
     * Recover page ( Full length + ?code=)
     */
    public static final String RECOVER_LINK = "http://localhost:8080/Profil_war_exploded/newPassword.xhtml?code=";

    /**
     * Default profile picture path
     */
    public static final String DEFAULT_PROFILE_IMAGE_PATH = "/Profil_war_exploded/javax.faces.resource/profilPicture.png.xhtml?ln=images";
}


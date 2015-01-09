package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.service.DataBaseBean;
import biz.stillhart.profileManagement.utils.Settings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Patrick Stillhart on 11/2/14.
 * Contains ongoing recover information
 */
public class ResetPasswordBase implements Serializable {

    /**
     * The database
     */
    private DataBaseBean dataBaseBean;

    /**
     * List with user who have a reset key
     * <code , username>
     */
    private HashMap<String, String> keyList;

    /**
     * Secure random object
     */
    private SecureRandom random;


    /**
     * Constructs a RecoverBase
     *
     * @param dataBaseBean the database with user information
     */
    public ResetPasswordBase(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
        keyList = new HashMap<String, String>();

        random = new SecureRandom();
    }

    /**
     * Sends recover mail
     *
     * @param email       email to send the mail
     * @param name        name of the user
     * @param recoverCode the recover code
     */
    public static void sendResetMail(String email, String name, String recoverCode) {

        String from = Settings.RECOVER_LOGIN_MAIL;


        Properties properties = System.getProperties();
        if (Settings.RECOVER_LOGIN_HOST.equals("NoServer")) {
            properties.setProperty("mail.smtp.host", "localhost");

        } else {
            properties.setProperty("mail.transport.protocol", "pop3");
            properties.setProperty("mail.host", Settings.RECOVER_LOGIN_HOST);
            properties.setProperty("mail.user", Settings.RECOVER_LOGIN_USER);
            properties.setProperty("mail.password", Settings.RECOVER_LOGIN_PASSWORD);

        }

        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            message.setSubject("Recover your BZZ password");
            message.setText(Settings.RECOVER_MAIL
                    .replaceAll("~~username~~", name)
                    .replaceAll("~~link~~", Settings.RECOVER_LINK + recoverCode)
                    , "utf-8", "html");

            // Send message
            Transport.send(message);
            System.out.println("Sent mail to " + email);
        } catch (MessagingException e) {
            System.err.println("Error sending mail to: " + email + " -> " + e.getMessage());
        }

    }

    /**
     * Init recover process
     *
     * @param username the username of the user which want to recover
     */
    public void sendMail(String username) {
        try {
            Student student = dataBaseBean.getDataBase().getStudent(username);
            if (null != student.getPrivateMail()) {
                String code = generateKey();
                while (keyList.containsKey(code)) code = generateKey();

                keyList.put(code, username);
                sendResetMail(student.getPrivateMail(), student.getFirstName(), code);

            }
        } catch (NullPointerException e) {
            // Ignore, user gave wrong username... don't react
        } catch (NamingException e) {
            // Ignore, user gave wrong username... don't react
        }
    }

    /**
     * creates random key
     *
     * @return the recovery key
     */
    private String generateKey() {
        return new BigInteger(130, random).toString(32);
    }

    /**
     * Gets a username with a recover key
     *
     * @param key the recover key
     * @return the username
     */
    public String getUsernameByKey(String key) {
        return keyList.get(key);
    }

}

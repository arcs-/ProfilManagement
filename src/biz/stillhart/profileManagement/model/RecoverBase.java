package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.service.DataBaseBean;
import biz.stillhart.profileManagement.utils.Settings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Patrick Stillhart on 11/2/14.
 */
public class RecoverBase implements Serializable {

    // This value is set in the constructor
    private DataBaseBean dataBaseBean;

    // <code , username>
    private HashMap<String, String> keyList;
    private SecureRandom random;


    public RecoverBase(DataBaseBean dataBaseBean) {
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
    public static void sendRecoverMail(String email, String name, String recoverCode) {

        // Sender's email ID needs to be mentioned
        String from = "recover@bzz.ch";

        //     Properties properties = new Properties();
        //     properties.setProperty("mail.transport.protocol", "pop3");
        //     properties.setProperty("mail.host", "pop.udag.de");
        //     properties.setProperty("mail.user", "stillhartbiz-0002");
        //     properties.setProperty("mail.password", "YouDontSeeMe");

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "localhost");

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
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    /**
     * Init recover process
     *
     * @param username the username of the user which want to recover
     */
    public void recover(String username) {
        try {
            Student student = dataBaseBean.getDataBase().getStudent(username);
            if (null != student.getPrivateMail()) {
                String code = generateKey();
                while (keyList.containsKey(code)) code = generateKey();

                keyList.put(code, username);
                sendRecoverMail(student.getPrivateMail(), student.getFirstName(), code);

            }
        } catch (NullPointerException e) {
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

package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.OpenLDAPConnection;

import javax.naming.NamingException;
import javax.naming.directory.*;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Created by Patrick Stillhart on 11/1/14.
 */
public class DataBase implements Serializable {

    OpenLDAPConnection connection;

    public DataBase() {

        connection = new OpenLDAPConnection("localhost",
                "dc=openiam,dc=com",
                "cn=Manager",
                "123456");

    }

    /**
     * Check if credentials correspond to a user
     *
     * @param credentials Credentials object with username and password
     * @return true if the match a user
     */
    public boolean isUser(Credentials credentials) {
        SearchResult result = connection.getUser(credentials.getUsername());

        if (result != null) {
            try {
                String origPW = decodeByte(result.getAttributes().get("userPassword").get(0));
                if (origPW.equals(credentials.getPassword())) return true;

            } catch (NamingException e) {
                return false;
            }
        }
        return false;

    }

    public void save(Student student) {

        ModificationItem[] mods = new ModificationItem[4];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("cn", student.getFirstName()));
        mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("sn", student.getLastName()));
        mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", encodeByte(student.getPassword())));
        mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("homeDirectory", student.getPrivateMail()));

        connection.updateUser(student.getUserName(), mods);
    }

    /**
     * Get a student by username
     *
     * @param username the username
     * @return the student with this username
     */
    public Student getStudent(String username) {
        SearchResult result = connection.getUser(username);

        try {
            return new Student(result.getAttributes().get("uid").get(0).toString(),
                    result.getAttributes().get("cn").get(0).toString(),
                    result.getAttributes().get("sn").get(0).toString(),
                    decodeByte(result.getAttributes().get("userPassword").get(0)),
                    " ",
                    " ",
                    " ");
        } catch (NamingException e) {
            return null;
        }
    }

    private String encodeByte(String obj) {
        return new String(obj.getBytes(Charset.forName("UTF-8")));
    }

    private String decodeByte(Object obj) {
        return new String((byte[]) obj);
    }

}


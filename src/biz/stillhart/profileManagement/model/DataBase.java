package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.OpenLDAPConnection;
import biz.stillhart.profileManagement.utils.Settings;

import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchResult;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * Holds the connection to the database
 */
public class DataBase implements Serializable {

    /**
     * The connection
     */
    private OpenLDAPConnection connection;

    /**
     * Establish a database connection
     */
    public DataBase() {

        connection = new OpenLDAPConnection(Settings.DATABASE,
                Settings.USER_DN,
                Settings.LOGIN_DN,
                Settings.USER_PASSWORD);

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
                if (null == result.getAttributes().get("userPassword")) return false;

                String origPW = decodeByte(result.getAttributes().get("userPassword").get(0));
                if (origPW.equals(credentials.getPassword())) return true;

            } catch (NamingException e) {
                return false;
            }
        }
        return false;

    }

    /**
     * Saves a student to database
     *
     * adds a ' before fields that can be null
     *
     * @param student the student
     */
    public void save(Student student) {

        //ToDO: talk about this

        // add ' because to later check if field was empty
        ModificationItem[] mods = new ModificationItem[8];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("displayName", student.getFirstName().trim() + " " + student.getLastName().trim()));
        mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", encodeByte(student.getPassword())));
        mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail", "'"+student.getPrivateMail()));
        mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("telephoneNumber", "'"+student.getPhoneNumber()));
        mods[4] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("street", "'"+student.getAddressStreet()));
        mods[5] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("postalAddress", "'"+student.getAddressCity()));
        mods[6] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("labeledURI", "'"+student.getProfilePicturePath()));
        mods[7] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("description", "'"+student.getDeviceString()));

        connection.updateUser(student.getUserName(), mods);
    }

    /**
     * Get a student by username
     *
     * removes a ' before fields that can be null
     *
     * @param username the username
     * @return the student with this username
     */
    public Student getStudent(String username) {
        SearchResult result = connection.getUser(username);

        try {
            // Get name
            String displayName = result.getAttributes().get("displayName").get(0).toString().trim();
            String firstName = displayName.substring(0, displayName.indexOf(' '));
            String lastName =  displayName.substring(displayName.indexOf(' ') + 1);

            // Get password
            String password =  decodeByte(result.getAttributes().get("userPassword").get(0));

            // Get rest
            String mail = result.getAttributes().get("mail").get(0).toString().substring(1);
            String telephoneNumber = result.getAttributes().get("telephoneNumber").get(0).toString().substring(1);
            String street = result.getAttributes().get("street").get(0).toString().substring(1);
            String postalAddress = result.getAttributes().get("postalAddress").get(0).toString().substring(1);
            String labeledURI = result.getAttributes().get("labeledURI").get(0).toString().substring(1);


            // Load devices
            ArrayList<Device> devices = new ArrayList<Device>();
            String[] deviceParts = result.getAttributes().get("description").get(0).toString().substring(1).split(";");
            for (int i = 0; i < deviceParts.length; i += 3) {
                String name, mac;
                try {
                    name = deviceParts[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    name = " ";
                }
                try {
                    mac = deviceParts[i + 2];
                } catch (ArrayIndexOutOfBoundsException e) {
                    mac = " ";
                }

                devices.add(new Device(deviceParts[i].equals("1"), name, mac));
            }


            return new Student(result.getAttributes().get("uid").get(0).toString(),
                    firstName,
                    lastName,
                    password,
                    mail,
                    telephoneNumber,
                    street,
                    postalAddress,
                    labeledURI,
                    devices);
        } catch (NamingException e) {
            return null;
        }
    }

    /**
     * Encode a string to database format
     *
     * @param obj The string to encode
     * @return the encoded string
     */
    private String encodeByte(String obj) {
        return new String(obj.getBytes(Charset.forName("UTF-8")));
    }

    /**
     * Decode a string from database format to normal
     *
     * @param obj The encoded string
     * @return the decoded string
     */
    private String decodeByte(Object obj) {
        return new String((byte[]) obj);
    }

}


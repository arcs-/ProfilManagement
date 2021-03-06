package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.Settings;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Patrick Stillhart on 10/31/14.
 * Holds user information
 */
public class Student extends Credentials implements Serializable {

    /**
     * First name
     */
    @Size(max = 30, message = "Maximal 30 Zeichen")
    @NotBlank(message = "Darf nicht leer sein!")
    @NotNull(message = "Darf nicht leer sein!")
    private String firstName;
    /**
     * Last name
     */
    @Size(max = 30, message = "Maximal 30 Zeichen")
    @NotBlank(message = "Darf nicht leer sein!")
    @NotNull(message = "Darf nicht leer sein!")
    private String lastName;

    /**
     * Private mail address
     */
    private String bzzMail;
    /**
     * Private mail address
     */
    @Size(max = 30, message = "Maximal 30 Zeichen")
    private String privateMail;

    /**
     * Phone number
     */
    @Size(max = 30, message = "Maximal 30 Zeichen")
    private String phoneNumber;
    /**
     * Private address street
     */
    @Size(max = 30, message = "Maximal 30 Zeichen")
    private String addressStreet;
    /**
     * Private address city
     */
    @Size(max = 30, message = "Maximal 30 Zeichen")
    private String addressCity;
    /**
     * Url path to profile picture
     */
    private String profilePicturePath;
    /**
     * Git SSH key
     */
    @Size(max = 700, message = "Zu grosser Key")
    @Pattern(regexp = "(ssh-rsa ([^@]*) (.+\\@.+\\..+))|($^)", message = "Kein gültiger SSH-Key!")
    private String gitPublicKey;
    /**
     * Email RSA key
     */
    @Size(max = 2500, message = "Zu grosser Key")
    @Pattern(regexp = "(-{5}BEGIN PUBLIC KEY-{5}[^-]*-{5}END PUBLIC KEY-{5})|($^)", message = "Kein gültiger Public-RSA-Key!")
    private String emailPublicKey;
    /**
     * List of devices
     */
    private ArrayList<Device> devices;

    /**
     * Constructs a user
     *
     * @param userName      The username
     * @param firstName     The first name
     * @param lastName      The last name
     * @param password      The password
     * @param bzzMail       The bzz mail
     * @param privateMail   The private mail address
     * @param addressStreet The address street
     * @param addressCity   The address city
     */
    public Student(String userName, String firstName, String lastName, String password, String bzzMail, String privateMail, String phoneNumber, String addressStreet, String addressCity, String gitPublicKey, String emailPublicKey, String profilePicturePath, ArrayList<Device> devices) {
        super();
        super.setUsername(userName);
        this.firstName = firstName;
        this.lastName = lastName;
        super.setPassword(password);
        this.bzzMail = bzzMail;
        this.privateMail = privateMail;
        this.phoneNumber = phoneNumber;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.gitPublicKey = gitPublicKey;
        this.emailPublicKey = emailPublicKey;
        this.profilePicturePath = profilePicturePath;
        this.devices = devices;
    }

    /**
     * Gets first name
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets first name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets username
     */
    public String getUserName() {
        return super.getUsername();
    }

    /**
     * Gets username
     */
    public void setUserName(String username) {
        super.setUsername(username);
    }

    /**
     * Gets school mail address
     *
     * @return school mail address
     */
    public String getBzzMail() {
        return bzzMail;
    }

    /**
     * Gets private mail address
     *
     * @return private mail address
     */
    public String getPrivateMail() {
        return privateMail;
    }

    /**
     * Sets private mail address
     */
    public void setPrivateMail(String privateMail) {
        this.privateMail = privateMail;
    }

    /**
     * Get The phone number
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets address street
     *
     * @return address street
     */
    public String getAddressStreet() {
        return addressStreet;
    }

    /**
     * Sets address street
     */
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    /**
     * Gets address city
     *
     * @return address city
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets address city
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * Gets profile picture
     *
     * @return profile picture path or default path
     */
    public String getProfilePicturePath() {
        if (profilePicturePath == null || profilePicturePath.isEmpty()) return Settings.DEFAULT_PROFILE_IMAGE_PATH;
        return profilePicturePath;
    }

    /**
     * Sets profile picture
     */
    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    /**
     * Gets profile picture with changing url
     * -> ignore browser cache
     *
     * @return profile picture path or default path
     */
    public String getProfilePicturePathTime() {
        if (profilePicturePath == null || profilePicturePath.isEmpty()) return Settings.DEFAULT_PROFILE_IMAGE_PATH;
        return profilePicturePath + "?dum=" + System.currentTimeMillis();
    }

    /**
     * gets public git key
     *
     * @return SSH key
     */
    public String getGitPublicKey() {
        return gitPublicKey;
    }

    /**
     * Sets public git key
     *
     * @param gitPublicKey SSH key
     */
    public void setGitPublicKey(String gitPublicKey) {
        this.gitPublicKey = gitPublicKey;
    }

    /**
     * Get email RSA key
     *
     * @return RSA key
     */
    public String getEmailPublicKey() {
        return emailPublicKey;
    }

    /**
     * Set email RSA key
     *
     * @param emailPublicKey RSA key
     */
    public void setEmailPublicKey(String emailPublicKey) {
        this.emailPublicKey = emailPublicKey;
    }

    /**
     * Gets device list
     *
     * @return list of devices
     */
    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    /**
     * Get device String
     *
     * @return a String fro database
     */
    public String getDeviceString() {
        StringBuilder builder = new StringBuilder();
        for (Device device : devices)
            builder.append(device.isPrimary() ? 1 : 0)
                    .append(";")
                    .append(device.getName())
                    .append(";")
                    .append(device.getMac())
                    .append(";");

        if (builder.length() > 1)
            builder.setLength(builder.length() - 1);

        return builder.toString();
    }
}


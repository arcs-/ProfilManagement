package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.Settings;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
    @NotBlank
    @NotNull
    private String firstName;
    /**
     * Last name
     */
    @NotBlank
    @NotNull
    private String lastName;
    /**
     * Private mail address
     */
    private String privateMail;

    /**
     * Private address street
     */
    private String addressStreet;
    /**
     * Private address city
     */
    private String addressCity;
    /**
     * Url path to profile picture
     */
    private String profilePicturePath;
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
     * @param privateMail   The private mail address
     * @param addressStreet The address street
     * @param addressCity   The address city
     */
    public Student(String userName, String firstName, String lastName, String password, String privateMail, String addressStreet, String addressCity) {
        super();
        super.setUsername(userName);
        this.firstName = firstName;
        this.lastName = lastName;
        super.setPassword(password);
        this.privateMail = privateMail;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
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
    public String getSchoolMail() {
        return getUsername() + "@bzz.ch";
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
        if (profilePicturePath == null) return Settings.DEFAULT_PROFILE_IMAGE_PATH;
        return profilePicturePath + "?dum=" + System.currentTimeMillis();
    }

    /**
     * Sets profile picture
     */
    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    /**
     * Gets device list
     *
     * @return list of devices
     */
    public ArrayList<Device> getDevices() {
        return devices;
    }


}


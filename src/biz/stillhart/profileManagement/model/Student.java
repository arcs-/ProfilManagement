package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.Settings;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 10/31/14.
 */
public class Student implements Serializable {

    private String userName;
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;


    @NotBlank @NotNull
    private String password;


    @NotBlank @NotNull
    private String privateMail;


    @NotBlank @NotNull
    private String addressStreet;
    @NotBlank @NotNull
    private String addressCity;

    private String profilePicturePath;

    public Student(String userName, String firstName, String lastName, String password, String privateMail, String addressStreet, String addressCity) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.privateMail = privateMail;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
    }

    public void update(Student student) {
        update(student.getUserName(),student.getFirstName(),student.getLastName(),student.getPassword(),student.getPrivateMail(),student.getAddressStreet(),student.getAddressCity());
    }

    public void update(String userName, String firstName, String lastName, String password, String privateMail, String addressStreet, String addressCity) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.privateMail = privateMail;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
    }

    public Student copy() {
        return new Student(userName,firstName,lastName,password,privateMail,addressStreet,addressCity);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolMail() {
        return userName + "@bzz.ch";
    }


    public String getPrivateMail() {
        return privateMail;
    }

    public void setPrivateMail(String privateMail) {
        this.privateMail = privateMail;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getProfilePicturePath() {
        if(profilePicturePath == null) return Settings.DEFAULT_PROFILE_IMAGE_PATH;
        return profilePicturePath+"?dum="+System.currentTimeMillis();
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}


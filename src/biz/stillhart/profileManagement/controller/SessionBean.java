package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.*;
import biz.stillhart.profileManagement.service.AttemptBean;
import biz.stillhart.profileManagement.service.DataBaseBean;
import biz.stillhart.profileManagement.utils.SessionUtils;
import biz.stillhart.profileManagement.utils.Settings;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/14/14.
 * Holder for session information
 */
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {

    @ManagedProperty("#{dataBaseBean}")
    private DataBaseBean dataBaseBean;

    @ManagedProperty("#{attemptBean}")
    private AttemptBean attemptBean;

    private Student student;
    private UserState userState = UserState.NOT_SET;
    private UserState recoverState = UserState.NOT_SET;

    private Information information;

    /**
     * Checks if user credentials are correct and if so logs the user in
     *
     * @param credentials The credentials
     * @return Enum userState
     */
    public UserState loginUser(Credentials credentials) {
        String ip = SessionUtils.getIp();

        attemptBean.getAttemptManager().add(ip, LockType.LOGIN);

        if (attemptBean.getAttemptManager().isLocked(ip, LockType.LOGIN)) { // Too many attempts
            return userState = UserState.LOCKED;

        } else if (dataBaseBean.getDataBase().isUser(credentials)) { // Correct credentials

            try {
                student = dataBaseBean.getDataBase().getStudent(credentials.getUsername());
            } catch (Exception e) {
                student = null;
                return userState = UserState.BROKEN;
            }

            HttpSession session = SessionUtils.getSession();
            // Set dummy attribute to hold session
            session.setAttribute("username", credentials.getUsername());

            return userState = UserState.CORRECT;

        } else { // Incorrect
            return userState = UserState.WRONG;
        }
    }

    /**
     * Logout user, forward to next page
     *
     * @return the next page
     */
    public String logoutUser() {
        userState = UserState.NOT_SET;
        student = null;

        HttpSession session = SessionUtils.getSession();
        session.invalidate();

        return Settings.PUBLIC_HOME + "?faces-redirect=true";
    }

    /**
     * Saves session user to database
     */
    public void saveStudent() {
        getDataBaseBean().getDataBase().save(student);
    }

    /**
     * Check if a information is present
     *
     * @return true if present
     */
    public boolean hasInformation() {
        return this.information != null;
    }

    /**
     * Get the current information
     *
     * @return the information
     */
    public Information getInformation() {
        return information;
    }

    /**
     * Sets a new Information
     *
     * @param information the new information object
     */
    public void setInformation(Information information) {
        this.information = information;
    }

    /**
     * Clears the information attribute
     */
    public void clearInformation() {
        this.information = null;
    }

    /**
     * Gets the current logged in user
     *
     * @return the current user
     */
    public Student getStudent() {
        return student;
    }

    /*
        Getter & Setter for JSF / View
     */

    public DataBaseBean getDataBaseBean() {
        return dataBaseBean;
    }

    public void setDataBaseBean(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }

    public void setAttemptBean(AttemptBean attemptBean) {
        this.attemptBean = attemptBean;
    }

    public UserState getUserState() {
        return userState;
    }

    public UserState getRecoverState() {
        return recoverState;
    }

    public void setRecoverState(UserState recoverState) {
        this.recoverState = recoverState;
    }

}

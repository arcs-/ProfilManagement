package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Credentials;
import biz.stillhart.profileManagement.utils.Settings;
import biz.stillhart.profileManagement.utils.UrlUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 10/31/14.
 * Bean for the login page
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    private Credentials credentials;

    private boolean successMessage;
    private boolean errorMessage;
    private boolean warningMessage;
    private String message;

    /**
     * Initialize a messages
     */
    public LoginBean() {
        if (UrlUtils.getDomainParameter("state") != null) {
            String state = UrlUtils.getDomainParameter("state");
            if (state.equals("success")) successMessage = true;
            else if (state.equals("error")) errorMessage = true;
            else if (state.equals("warning")) warningMessage = true;
        }

        if (UrlUtils.getDomainParameter("message") != null)
            message = UrlUtils.decode(UrlUtils.getDomainParameter("message"));
        credentials = new Credentials();
    }

    /**
     * Action for commandButton
     *
     * @return the next page
     */
    public String login() {

        switch (sessionBean.loginUser(credentials)) {
            case LOCKED:
                return Settings.PUBLIC_HOME + "?faces-redirect=true&state=error&message=" + UrlUtils.encode("Too many tris! Wait a couple of minutes");
            case WRONG:
                return Settings.PUBLIC_HOME + "?faces-redirect=true&state=error&message=" + UrlUtils.encode("Wrong username or password");
            case CORRECT:
                return Settings.PRIVATE_HOME + "?faces-redirect=true";
            default:
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
        }

    }

    /**
     * Get success message state
     *
     * @return true if there's a message
     */
    public boolean isSuccessMessage() {
        return successMessage;
    }

    /**
     * Get error message state
     *
     * @return true if there's a message
     */
    public boolean isErrorMessage() {
        return errorMessage;
    }

    /**
     * Get warning message state
     *
     * @return true if there's a message
     */
    public boolean isWarningMessage() {
        return warningMessage;
    }

    /**
     * Returns the message for success/warning/error
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /*
        Getter & Setter for JSF / View
     */

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

}

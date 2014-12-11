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
     * Bean for login page
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

    public String login() {

        switch (sessionBean.loginUser(credentials)) {
            case LOCKED:
                return Settings.PUBLIC_HOME + "?faces-redirect=true&state=error&message=" + UrlUtils.encode("Too many trys! Wait a couple of minutes");
            case WRONG:
                return Settings.PUBLIC_HOME + "?faces-redirect=true&state=error&message=" + UrlUtils.encode("Wrong username or password");
            case CORRECT:
                return Settings.PRIVATE_HOME + "?faces-redirect=true";
            default:
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
        }

    }

    public boolean isSuccessMessage() {
        return successMessage;
    }

    public boolean isErrorMessage() {
        return errorMessage;
    }

    public boolean isWarningMessage() {
        return warningMessage;
    }

    public String getMessage() {
        return message;
    }

    /*
     Following is JSF stuff
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

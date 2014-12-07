package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Credentials;
import biz.stillhart.profileManagement.model.UserState;
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
    private boolean mailSuccess;
    private boolean mailError;

    /**
     * Bean for login page
     */
    public LoginBean() {
        if(UrlUtils.getDomainParameter("success") != null) if(UrlUtils.getDomainParameter("success").equals("true")) mailSuccess = true; else mailError = true;
        credentials = new Credentials();
    }

    public String login() {

        switch (sessionBean.loginUser(credentials)) {
            case LOCKED:
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
            case WRONG:
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
            case CORRECT:
                return Settings.PRIVATE_HOME + "?faces-redirect=true";
            default:
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
        }

    }

    public boolean isLocked() {
        return sessionBean.getState() == UserState.LOCKED;
    }

    public boolean isWrong() {
        return sessionBean.getState() == UserState.WRONG;
    }

    public boolean isMailSuccess() { return mailSuccess; }

    public boolean isMailError() { return mailError; }


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

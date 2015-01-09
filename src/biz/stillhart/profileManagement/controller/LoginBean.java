package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Credentials;
import biz.stillhart.profileManagement.model.Information;
import biz.stillhart.profileManagement.model.InformationType;
import biz.stillhart.profileManagement.utils.Settings;
import biz.stillhart.profileManagement.utils.UrlUtils;

import javax.annotation.PostConstruct;
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
     * Initialize the bean and sets, if set, the information message
     */
    @PostConstruct
    public void init() {
        credentials = new Credentials();

        if (sessionBean.hasInformation()) {
            switch (sessionBean.getInformation().getInformationType()) {
                case ERROR:
                    errorMessage = true;
                    break;
                case WARNING:
                    warningMessage = true;
                    break;
                case SUCCESS:
                    successMessage = true;
                    break;
            }

            message = sessionBean.getInformation().getMessage();
            sessionBean.clearInformation();
        }

        // Has to be with URL, because JSF forgets
        String state = UrlUtils.getDomainParameter("state");
        if (state != null && state.equals("expired")) {
            warningMessage = true;
            message = "Session abgelaufen!";
        }
    }

    /**
     * Action for commandButton (login)
     *
     * @return the next page
     */
    public String login() {

        switch (sessionBean.loginUser(credentials)) {
            case LOCKED:
                sessionBean.setInformation(new Information(InformationType.ERROR, "Zuviele versuche! Warte ein paar Minuten"));
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
            case WRONG:
                sessionBean.setInformation(new Information(InformationType.ERROR, "Falscher Nutzername oder falsches Passwort"));
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
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

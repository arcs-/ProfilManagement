package biz.stillhart.profileManagement.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 12/7/14.
 * Bean for 'new password' page
 */
@ManagedBean
@RequestScoped
public class PasswordBean implements Serializable {

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    private String oldPassword;
    private String newPassword;

    private boolean success;

    /**
     * Action for commandButton
     * Saves the new password
     */
    public void save() {
        if (sessionBean.getStudent().getPassword().equals(oldPassword)) {
            sessionBean.getStudent().setPassword(newPassword);
            sessionBean.saveStudent();
            success = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("formContainer:oldPassword", new FacesMessage("Dies ist nicht dein derzeites Passwort"));
        }

    }

    /*
        Getter & Setter for JSF / View
     */

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public boolean isSuccess() {
        return success;
    }
}

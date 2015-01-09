package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Information;
import biz.stillhart.profileManagement.model.InformationType;
import biz.stillhart.profileManagement.model.Student;
import biz.stillhart.profileManagement.service.DataBaseBean;
import biz.stillhart.profileManagement.service.ResetPasswordBaseBean;
import biz.stillhart.profileManagement.utils.Settings;
import biz.stillhart.profileManagement.utils.UrlUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * Recover process: sets new password for user
 * Is ViewScoped to prevent loosing the key at refresh
 */
@ManagedBean
@ViewScoped
public class ResetPasswordSetBean implements Serializable {

    @ManagedProperty("#{dataBaseBean}")
    private DataBaseBean dataBaseBean;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    @ManagedProperty("#{resetPasswordBaseBean}")
    private ResetPasswordBaseBean resetPasswordBaseBean;

    private String password;
    private String key;

    /**
     * Set the recover code from url
     */
    public ResetPasswordSetBean() {
        key = UrlUtils.getDomainParameter("code");

        if (key == null) {
            try {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(Settings.PUBLIC_HOME + ".xhtml");
            } catch (IOException e) {
                // back to home if no code
            }
        }
    }

    /**
     * Action for commandButton
     * Sets the new password
     *
     * @return the next page
     */
    public String set() {
        String studentName = resetPasswordBaseBean.getRecoverBase().getUsernameByKey(key);
        if (studentName != null) {
            try {
                Student student = dataBaseBean.getDataBase().getStudent(studentName);
                student.setPassword(password);
                dataBaseBean.getDataBase().save(student);
            } catch (NamingException e) {
                sessionBean.setInformation(new Information(InformationType.ERROR, "Dein Daten sind kaput. Bitte Admin kontaktieren"));
                return Settings.PUBLIC_HOME + "?faces-redirect=true";
            }
        }
        sessionBean.setInformation(new Information(InformationType.SUCCESS, "Neues Passwort gesezt"));
        return Settings.PUBLIC_HOME + "?faces-redirect=true";
    }

    /*
          Getter & Setter for JSF / View
     */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDataBaseBean(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }

    public void setResetPasswordBaseBean(ResetPasswordBaseBean resetPasswordBaseBean) {
        this.resetPasswordBaseBean = resetPasswordBaseBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}

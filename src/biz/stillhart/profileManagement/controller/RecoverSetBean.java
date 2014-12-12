package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Student;
import biz.stillhart.profileManagement.service.DataBaseBean;
import biz.stillhart.profileManagement.service.RecoverBaseBean;
import biz.stillhart.profileManagement.utils.Settings;
import biz.stillhart.profileManagement.utils.UrlUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * Recover process: sets new password for user
 */
@ManagedBean
@ViewScoped
public class RecoverSetBean implements Serializable {

    @ManagedProperty("#{dataBaseBean}")
    private DataBaseBean dataBaseBean;

    @ManagedProperty("#{recoverBaseBean}")
    private RecoverBaseBean recoverBaseBean;

    private String password;
    private String key;

    /**
     * Set the recover code from url
     */
    public RecoverSetBean() {
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
        String studentName = recoverBaseBean.getDataBase().getUsernameByKey(key);
        if (studentName != null) {
            Student student = dataBaseBean.getDataBase().getStudent(studentName);
            student.setPassword(password);
            dataBaseBean.getDataBase().save(student);
        }
        return Settings.PUBLIC_HOME + "?faces-redirect=true&state=success&message=" + UrlUtils.encode("New password is set");
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

    public void setRecoverBaseBean(RecoverBaseBean recoverBaseBean) {
        this.recoverBaseBean = recoverBaseBean;
    }
}

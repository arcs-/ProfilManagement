package biz.stillhart.profileManagement.service;

import biz.stillhart.profileManagement.model.ResetPasswordBase;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/2/14.
 * Global bean to control recover processes
 */
@ManagedBean
@ApplicationScoped
public class ResetPasswordBaseBean implements Serializable {

    @ManagedProperty("#{dataBaseBean}")
    private DataBaseBean dataBaseBean;

    private ResetPasswordBase resetPasswordBase;

    @PostConstruct
    public void init() {
        resetPasswordBase = new ResetPasswordBase(dataBaseBean);
    }

    public ResetPasswordBase getRecoverBase() {
        return resetPasswordBase;
    }

    /*
     Getter & Setter for JSF / View
     */

    public void setDataBaseBean(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }
}

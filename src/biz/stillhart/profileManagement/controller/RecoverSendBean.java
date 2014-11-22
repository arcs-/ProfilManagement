package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.service.RecoverBaseBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 10/31/14.
 */
@ManagedBean
@RequestScoped
public class RecoverSendBean implements Serializable {

    private String username;

    @ManagedProperty("#{recoverBaseBean}")
    private RecoverBaseBean recoverBaseBean;

    public RecoverSendBean(){
        username = "";
    }

    public void recover() {
         recoverBaseBean.getDataBase().recover(username);
    }

    /*
      JSF Stuff
     */
    public RecoverBaseBean getRecoverBaseBean() {
        return recoverBaseBean;
    }

    public void setRecoverBaseBean(RecoverBaseBean recoverBaseBean) {
        this.recoverBaseBean = recoverBaseBean;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

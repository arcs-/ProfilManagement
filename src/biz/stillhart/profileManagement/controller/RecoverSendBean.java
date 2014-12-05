package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.LockType;
import biz.stillhart.profileManagement.model.UserState;
import biz.stillhart.profileManagement.service.AttemptBean;
import biz.stillhart.profileManagement.service.RecoverBaseBean;
import biz.stillhart.profileManagement.utils.SessionUtils;
import biz.stillhart.profileManagement.utils.Settings;

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

    @ManagedProperty("#{attemptBean}")
    private AttemptBean attemptBean;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public RecoverSendBean(){
        username = "";
    }

    public String recover() {
        String ip = SessionUtils.getIp();

        attemptBean.getAttemptManager().add(ip, LockType.RECOVER);

        if(attemptBean.getAttemptManager().isLocked(ip, LockType.RECOVER)) {
            sessionBean.setRecoverState(UserState.LOCKED);
        }

        recoverBaseBean.getDataBase().recover(username);
        return Settings.PUBLIC_HOME + "?faces-redirect=true";
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

    public void setAttemptBean(AttemptBean attemptBean) {
        this.attemptBean = attemptBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public boolean isLocked() {
        return sessionBean.getRecoverState() == UserState.LOCKED;
    }
}

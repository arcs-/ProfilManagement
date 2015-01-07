package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Information;
import biz.stillhart.profileManagement.model.InformationType;
import biz.stillhart.profileManagement.model.LockType;
import biz.stillhart.profileManagement.model.UserState;
import biz.stillhart.profileManagement.service.AttemptBean;
import biz.stillhart.profileManagement.service.ResetPasswordBaseBean;
import biz.stillhart.profileManagement.utils.SessionUtils;
import biz.stillhart.profileManagement.utils.Settings;
import biz.stillhart.profileManagement.utils.UrlUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 10/31/14.
 * Recover process: Sends a recover mail to a user
 */
@ManagedBean
@RequestScoped
public class ResetPasswordSendBean implements Serializable {

    @ManagedProperty("#{resetPasswordBaseBean}")
    private ResetPasswordBaseBean resetPasswordBaseBean;

    @ManagedProperty("#{attemptBean}")
    private AttemptBean attemptBean;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    private String username;

    /**
     * Action for commandButton
     * Sends a recover mail
     *
     * @return the next page
     */
    public String send() {
        String ip = SessionUtils.getIp();

        attemptBean.getAttemptManager().add(ip, LockType.RECOVER);

        if (attemptBean.getAttemptManager().isLocked(ip, LockType.RECOVER)) {
            sessionBean.setRecoverState(UserState.LOCKED);
            sessionBean.setInformation(new Information(InformationType.ERROR, "Ein Mail wurde bereits versendent, bitte warte"));
            return Settings.PUBLIC_HOME + "?faces-redirect=true";
        }

        resetPasswordBaseBean.getDataBase().sendMail(username);
        sessionBean.setInformation(new Information(InformationType.SUCCESS, "Ein Mail wurde gesendet"));
        return Settings.PUBLIC_HOME + "?faces-redirect=true";
    }

    /*
          JGetter & Setter for JSF / View
     */
    public void setResetPasswordBaseBean(ResetPasswordBaseBean resetPasswordBaseBean) {
        this.resetPasswordBaseBean = resetPasswordBaseBean;
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

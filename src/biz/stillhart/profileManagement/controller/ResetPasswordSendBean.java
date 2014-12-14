package biz.stillhart.profileManagement.controller;

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

    private String username;

    @ManagedProperty("#{resetPasswordBaseBean}")
    private ResetPasswordBaseBean resetPasswordBaseBean;

    @ManagedProperty("#{attemptBean}")
    private AttemptBean attemptBean;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public ResetPasswordSendBean() {
        username = "";
    }

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

            return Settings.PUBLIC_HOME + "?faces-redirect=true&state=error&message=" + UrlUtils.encode("A mail is already sent. Please wait");
        }

        resetPasswordBaseBean.getDataBase().sendMail(username);

        return Settings.PUBLIC_HOME + "?faces-redirect=true&state=success&message=" + UrlUtils.encode("A recover mail was sent");
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

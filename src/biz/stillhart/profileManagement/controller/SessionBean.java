package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Credentials;
import biz.stillhart.profileManagement.model.LockType;
import biz.stillhart.profileManagement.model.Student;
import biz.stillhart.profileManagement.model.UserState;
import biz.stillhart.profileManagement.service.AttemptBean;
import biz.stillhart.profileManagement.service.DataBaseBean;
import biz.stillhart.profileManagement.utils.SessionUtils;
import biz.stillhart.profileManagement.utils.Settings;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/14/14.
 */
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {

    @ManagedProperty("#{dataBaseBean}")
    private DataBaseBean dataBaseBean;

    @ManagedProperty("#{attemptBean}")
    private AttemptBean attemptBean;

    private Student student;
    private UserState userState = UserState.NOT_SET;
    private UserState recoverState = UserState.NOT_SET;

    /**
     * Checks if user credentials are correct and if so logs the user in
     * @param credentials The credentials
     * @return Enum userState
     */
    public UserState loginUser(Credentials credentials) {
        String ip = SessionUtils.getIp();

        attemptBean.getAttemptManager().add(ip, LockType.LOGIN);

        if(attemptBean.getAttemptManager().isLocked(ip, LockType.LOGIN)) { // Too many attempts
            userState = UserState.LOCKED;
            return userState;

        } else if(dataBaseBean.getDataBase().isUser(credentials)) { // Correct credentials
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", credentials.getUsername());

            student = dataBaseBean.getDataBase().getStudent(credentials.getUsername());

            userState = UserState.CORRECT;
            return userState;

        } else { // Incorrect

            userState = UserState.WRONG;
            return userState;

        }
    }

    /**
     * Logout user, forward to next page
     * @return the next page
     */
    public String logoutUser() {
        userState = UserState.NOT_SET;
        student = null;

        HttpSession session = SessionUtils.getSession();
        session.invalidate();

        return Settings.PUBLIC_HOME + "?faces-redirect=true";
    }

    /*
    JSF Stuff
     */

    public UserState getState() {
        return userState;
    }

    public DataBaseBean getDataBaseBean() {
        return dataBaseBean;
    }

    public void setDataBaseBean(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }

    public AttemptBean getAttemptBean() {
        return attemptBean;
    }

    public void setAttemptBean(AttemptBean attemptBean) {
        this.attemptBean = attemptBean;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public UserState getRecoverState() {
        return recoverState;
    }

    public void setRecoverState(UserState recoverState) {
        this.recoverState = recoverState;
    }
}

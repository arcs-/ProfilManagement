package biz.stillhart.profileManagement.service;

import biz.stillhart.profileManagement.model.AttemptManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/2/14.
 */
@ManagedBean
@ApplicationScoped
public class AttemptBean implements Serializable {

    private AttemptManager attemptManager;

    public AttemptBean() {
        attemptManager = new AttemptManager();
    }

    public AttemptManager getAttemptManager() {
        return attemptManager;
    }

}

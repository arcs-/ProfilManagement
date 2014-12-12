package biz.stillhart.profileManagement.service;

import biz.stillhart.profileManagement.model.RecoverBase;

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
public class RecoverBaseBean implements Serializable {

    @ManagedProperty("#{dataBaseBean}")
    private DataBaseBean dataBaseBean;

    private RecoverBase recoverBase;

    @PostConstruct
    public void init() {
        recoverBase = new RecoverBase(dataBaseBean);
    }

    public RecoverBase getDataBase() {
        return recoverBase;
    }

    public void setDataBaseBean(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }
}

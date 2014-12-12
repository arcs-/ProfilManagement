package biz.stillhart.profileManagement.service;

import biz.stillhart.profileManagement.model.DataBase;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * Global bean to hold the user database
 */
@ManagedBean
@ApplicationScoped
public class DataBaseBean implements Serializable {

    private DataBase dataBase;

    public DataBaseBean() {
        dataBase = new DataBase();
    }

    public DataBase getDataBase() {
        return dataBase;
    }

}

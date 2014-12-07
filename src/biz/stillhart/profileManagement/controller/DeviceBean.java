package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Device;
import biz.stillhart.profileManagement.model.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Patrick Stillhart on 11/1/14.
 */
@ManagedBean
@RequestScoped
public class DeviceBean implements Serializable {

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    private ArrayList<Device> devices;
    private Student student;

    @PostConstruct
    public void init() {
        student = sessionBean.getStudent();


        devices = new ArrayList<Device>();
        devices.add(new Device(true, "Laptop", "01-00-5e-7f-ff-ff"));
        devices.add(new Device(false, "Handy", "00-80-41-ae-fd-7e"));
    }

    /*
    mac evtl. doppelt über name finden
    name (mit username)
     */

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}

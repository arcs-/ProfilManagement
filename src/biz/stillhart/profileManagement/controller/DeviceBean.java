package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Device;

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
    private Device device;
    private String oldMac;

    @PostConstruct
    public void init() {
        oldMac = "/";
        device = new Device(false, "", "");
        devices = sessionBean.getStudent().getDevices();
    }

    public void save() {
        System.out.println("save " + oldMac + " x");
        if (oldMac == null || oldMac.trim().equals("") || oldMac.equals("/")) {
            devices.add(device);
        } else {
            for (Device de : devices) {
                if (de.getMac().equals(oldMac)) {
                    de.update(device);
                    System.out.println("found");
                    break;
                }

                if (device.isPrimary()) de.setPrimary(false);
            }
        }
        device = new Device(false, "", "");
        oldMac = "/";
        sessionBean.getStudent().setDevices(devices);
        sessionBean.saveStudent();
    }

    public void delete() {
        System.out.println("del " + oldMac + " x");
        if (oldMac != null && !oldMac.equals("/")) {
            System.out.println("check");
            for (Device de : devices)
                if (de.getMac().equals(oldMac)) {
                    devices.remove(de);
                    System.out.println("out");
                    break;
                }
        }
        device = new Device(false, "", "");
        oldMac = "/";
        sessionBean.getStudent().setDevices(devices);
        sessionBean.saveStudent();
    }

    /*
      Getter & Setter for JSF / View
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getOldMac() {
        return oldMac;
    }

    public void setOldMac(String oldMac) {
        this.oldMac = oldMac;
    }
}

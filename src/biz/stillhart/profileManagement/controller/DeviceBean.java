package biz.stillhart.profileManagement.controller;

import biz.stillhart.profileManagement.model.Device;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * Bean for device page
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
        oldMac = "";
        device = new Device(false, "", "");
        devices = sessionBean.getStudent().getDevices();
    }

    public void save() {
        if (oldMac == null || oldMac.trim().equals("") || oldMac.equals("")) {
            // if primary set all other so false
            if(device.isPrimary()) {
                for (Device de : devices) {
                    de.setPrimary(false);
                }
            }

            devices.add(device);
        } else {
            // Check if mac already is in use
            // ToDo: check all devices?
            for(Device de : devices) {
                if(de.getMac().equals(device.getMac()) && !device.getMac().equals(oldMac)){
                    FacesContext.getCurrentInstance().addMessage("formContainer:mac", new FacesMessage("Mac already in use"));
                    return;
                }
            }

            // Update devices
            for (Device de : devices) {
                if (de.getMac().equals(oldMac)) {
                    device.setMac(device.getMac().replace(":","-"));
                    de.update(device);
                } else if (device.isPrimary()) de.setPrimary(false);
            }
        }

        device = new Device(false, "", "");
        oldMac = "";
        sessionBean.getStudent().setDevices(devices);
        sessionBean.saveStudent();
    }

    public void delete() {
        if (oldMac != null && !oldMac.equals("")) {
            for (Device de : devices)
                if (de.getMac().equals(oldMac)) {
                    devices.remove(de);
                    break;
                }
        }

        device = new Device(false, "", "");
        oldMac = "";
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

package biz.stillhart.profileManagement.model;

import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 12/5/14.
 */
public class Device implements Serializable{

    private String name;
    private String mac;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}

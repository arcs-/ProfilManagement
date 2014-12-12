package biz.stillhart.profileManagement.model;

import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 12/5/14.
 * Holds device information
 */
public class Device implements Serializable {

    /**
     * Check if this is a primary device
     */
    private boolean primary;
    /**
     * Name from device
     */
    private String name;
    /**
     * Mac address
     */
    private String mac;

    /**
     * Constructs a device
     *
     * @param primary is primary device
     * @param name    the name
     * @param mac     the mac address
     */
    public Device(boolean primary, String name, String mac) {
        this.primary = primary;
        this.name = name;
        this.mac = mac;
    }

    /**
     * Overrides this device
     *
     * @param device another device
     */
    public void update(Device device) {
        this.primary = device.primary;
        this.name = device.getName();
        this.mac = device.getMac();
    }

    /**
     * Is primary
     *
     * @return if is primary
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * Set if this device is a primary or not
     *
     * @param primary set primary state
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the mac address
     *
     * @return the mac address
     */
    public String getMac() {
        return mac;
    }

    /**
     * Set the max address
     *
     * @param mac the mac address
     */
    public void setMac(String mac) {
        this.mac = mac;
    }
}

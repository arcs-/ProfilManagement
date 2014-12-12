package biz.stillhart.profileManagement.model;

import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 10/31/14.
 * Container for user login information
 */
public class Credentials implements Serializable {

    /**
     * The username
     */
    private String username;
    /**
     * The password
     */
    private String password;

    /**
     * Gets username
     *
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     *
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

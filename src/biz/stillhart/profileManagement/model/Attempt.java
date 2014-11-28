package biz.stillhart.profileManagement.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Patrick Stillhart on 11/2/14.
 */
public class Attempt implements Serializable {

    private Date entryDate;
    private int attempts;

    public Attempt() {
        this.entryDate = new Date();
        this.attempts = 1;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public int getAttempts() {
        return attempts;
    }

    public void addOne() {
        attempts++;
    }


}

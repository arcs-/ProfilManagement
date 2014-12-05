package biz.stillhart.profileManagement.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Patrick Stillhart on 11/2/14.
 */
public class Attempt implements Serializable {

    private LockType lockType;
    private Date entryDate;
    private int attempts;

    public Attempt(LockType lockType) {
        this.lockType = lockType;
        this.entryDate = new Date();
        this.attempts = 1;
    }

    public LockType getLockType() {
        return lockType;
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

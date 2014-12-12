package biz.stillhart.profileManagement.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Patrick Stillhart on 11/2/14.
 * Container for an Attempt
 */
public class Attempt implements Serializable {

    /**
     * Type of this attempt
     */
    private LockType lockType;
    /**
     * Date Attempt is created
     */
    private Date entryDate;
    /**
     * Counter: how many attempts
     */
    private int attempts;

    /**
     * Construct new Attempt
     *
     * @param lockType the type of this attempt
     */
    public Attempt(LockType lockType) {
        this.lockType = lockType;
        this.entryDate = new Date();
        this.attempts = 1;
    }

    /**
     * Add one to the attempt counter
     */
    public void addOne() {
        attempts++;
    }

    /**
     * Returns the lockType
     *
     * @return lockType
     */
    public LockType getLockType() {
        return lockType;
    }

    /**
     * Returns the lockType
     *
     * @return lockType
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * Returns the lockType
     *
     * @return lockType
     */
    public int getAttempts() {
        return attempts;
    }

}

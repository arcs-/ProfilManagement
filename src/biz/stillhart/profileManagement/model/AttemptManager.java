package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.Settings;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Patrick Stillhart on 11/2/14.
 */
public class AttemptManager implements Serializable {

    private HashMap<String, Attempt> attempts;

    public AttemptManager() {
        this.attempts = new HashMap<String, Attempt>();
    }

    /**
     * add or update a user in attempt list
     *
     * @param ip from the user
     */
    public void add(String ip, LockType lockType) {
        if (attempts.containsKey(ip) && attempts.get(ip).getLockType() == lockType)
            attempts.get(ip).addOne();
        else
            attempts.put(ip, new Attempt(lockType));
    }

    /**
     * Checks if a user is locked
     *
     * @param ip from the user
     * @return true if locked
     */
    public boolean isLocked(String ip, LockType lockType) {
        if (attempts.containsKey(ip)) {
            Attempt attempt = attempts.get(ip);
            // right lock and maximum attempts exceeded
            if (attempt.getLockType() == lockType && attempt.getAttempts() >= getAttempts(lockType)) {
                // However enough time passed
                if ((new Date().getTime() - attempt.getEntryDate().getTime()) / 1000 > getLockTime(lockType)) {
                    attempts.remove(ip);
                    return false; // Cool down
                }

                return true; // he's locked
            }

            return false; // He has tries left
        }

        return false; // He's not even in the list
    }

    public static int getAttempts(LockType lockType) {
        switch (lockType) {
            case LOGIN: return Settings.LOGIN_ATTEMPTS;
            case RECOVER: return Settings.MAIL_RECOVER_ATTEMPTS;
            default: return 3;
        }
    }

    public static int getLockTime(LockType lockType) {
        switch (lockType) {
            case LOGIN: return Settings.SECONDS_LOCKED;
            case RECOVER: return Settings.MAIL_RECOVER_LOCK_SECONDS;
            default: return 60;
        }
    }
}

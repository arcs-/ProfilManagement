package biz.stillhart.profileManagement.model;

import biz.stillhart.profileManagement.utils.Settings;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Patrick Stillhart on 11/2/14.
 */
public class AttemptManager implements Serializable{

    private HashMap<String, Attempt> attempts;

    public AttemptManager() {
        this.attempts = new HashMap<String, Attempt>();
    }

    /**
     * add or update a user in attempt list
     * @param ip from the user
     */
    public void add(String ip){
        if(attempts.containsKey(ip))
            attempts.get(ip).addOne();
        else
            attempts.put(ip, new Attempt());
    }

    /**
     * Checks if a user is locked
     * @param ip from the user
     * @return true if locked
     */
    public boolean isLocked(String ip) {
        if(attempts.containsKey(ip)) {
            Attempt attempt = attempts.get(ip);
            // Maximum attempts exceeded
            if(attempt.getAttempts() >= Settings.LOGIN_ATTEMPTS) {
                // However enough time passed
                if((new Date().getTime() - attempt.getEntryDate().getTime()) / 1000 > Settings.SECONDS_LOCKED) {
                    attempts.remove(ip);
                    return false; // Cool down
                }

                return true; // he's locked
            }

            return false; // He has tries left
        }

        return false; // He's not even in the list
    }
}

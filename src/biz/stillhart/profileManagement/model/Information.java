package biz.stillhart.profileManagement.model;

import java.io.Serializable;

/**
 * Created by Patrick Stillhart on 1/7/15.
 * Model containing an Information/Error -Message
 */
public class Information implements Serializable{

    /**
     * Type of that information
     */
    private InformationType informationType;
    /**
     * The message of this information
     */
    private String message;

    /**
     * Creates a new Information message
     * @param informationType information type
     * @param message information message
     */
    public Information(InformationType informationType, String message) {
        this.informationType = informationType;
        this.message = message;
    }

    /**
     * The information type
     * @return information type
     */
    public InformationType getInformationType() {
        return informationType;
    }

    /**
     * The information message
     * @return information message
     */
    public String getMessage() {
        return message;
    }
}

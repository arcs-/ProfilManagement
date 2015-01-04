package biz.stillhart.profileManagement.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Patrick Stillhart on 11/2/14.
 * Validates a mail address
 */
@FacesValidator(value = "macAddressValidator")
public class MacAddressValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String mac = String.valueOf(value);

        if (!mac.matches("^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$")) {
            throw new ValidatorException(new FacesMessage("Dies ist keine gültige Mac-Adresse."));
        }

    }
}
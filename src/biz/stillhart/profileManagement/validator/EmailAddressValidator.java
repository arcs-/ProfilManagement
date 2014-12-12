package biz.stillhart.profileManagement.validator;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

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
@FacesValidator(value = "emailAddressValidator")
public class EmailAddressValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = String.valueOf(value);

        if (email == null || email.isEmpty() || email.trim().length() == 0) return;

        if (!new EmailValidator().isValid(email, null)) {
            throw new ValidatorException(new FacesMessage("The email address you entered is not valid."));
        }
    }
}
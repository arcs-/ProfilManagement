package biz.stillhart.profileManagement.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Patrick Stillhart on 11/4/14.
 * Validates a new password
 */

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    /**
     * Validates password syntax and if match the confirm password
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = value.toString();

        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();

        if (!password.matches("((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,35})")) {
            throw new ValidatorException(new FacesMessage("Das Passwort muss mindestens 8 und maximal 35 Zeichen haben sowie Zahlen, gross- und klein Buchstaben beinhalten"));
        }

        // required="true" should does this job...
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            uiInputConfirmPassword.setValid(false);
            throw new ValidatorException(new FacesMessage("Die Passwörter stimmen nicht überein"));
        }

    }
}

package biz.stillhart.profileManagement.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 * Created by Patrick Stillhart on 11/18/14.
 * Validates a file upload
 */
@FacesValidator(value = "fileUploadValidator")
public class FileUploadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Part part = (Part) value;

        // 1. validate file name
        String fileName = getFileName(part);
        if (fileName.length() == 0) {
            FacesMessage message = new FacesMessage("Please select a file");
            throw new ValidatorException(message);
        } else if (fileName.length() > 50) {
            FacesMessage message = new FacesMessage("File name is too long!");
            throw new ValidatorException(message);
        }

        // 2. validate file type
        if (!part.getContentType().startsWith("image/")) {

            FacesMessage message = new FacesMessage("That's not an image");
            throw new ValidatorException(message);
        }

        // 3. validate file size
        if (part.getSize() > 1000000) {
            FacesMessage message = new FacesMessage("Image is too big!");
            throw new ValidatorException(message);
        }
    }

    // Extract file name from content-disposition header of file part
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return "";
    }


}

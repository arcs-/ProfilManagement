package biz.stillhart.profileManagement.utils;

import javax.faces.context.FacesContext;
import java.util.NoSuchElementException;

/**
 * Created by Patrick Stillhart on 11/4/14.
 * Utils to help to work with URLs
 */
public class UrlUtils {

    /**
     * Gets a domain parameter
     *
     * @param parameterName the searched parameter
     * @return the value from the parameter
     * @throws NoSuchElementException if the parameter couldn't be found
     */
    public static String getDomainParameter(String parameterName) throws NoSuchElementException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap().get(parameterName);
    }

}

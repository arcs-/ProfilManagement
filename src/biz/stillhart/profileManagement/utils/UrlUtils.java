package biz.stillhart.profileManagement.utils;

import javax.faces.context.FacesContext;
import java.util.NoSuchElementException;

/**
 * Created by Patrick Stillhart on 11/4/14.
 */
public class UrlUtils {

    public static String getDomainParameter(String parameterName) throws NoSuchElementException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap().get(parameterName);
    }

}

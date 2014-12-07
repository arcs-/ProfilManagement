package biz.stillhart.profileManagement.utils;

import javax.faces.context.FacesContext;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.NoSuchElementException;

/**
 * Created by Patrick Stillhart on 11/4/14.
 */
public class UrlUtils {

    public static String getDomainParameter(String parameterName) throws NoSuchElementException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap().get(parameterName);
    }

    public static String encode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return " ";
        }
    }

    public static String decode(String message) {
        try {
            return URLDecoder.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return " ";
        }
    }

}

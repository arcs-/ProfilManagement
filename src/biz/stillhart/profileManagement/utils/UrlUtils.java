package biz.stillhart.profileManagement.utils;

import javax.faces.context.FacesContext;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.NoSuchElementException;

/**
 * Created by Patrick Stillhart on 11/4/14.
 * <p/>
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

    /**
     * Formats a string fo match url specs
     *
     * @param message the string to encode
     * @return the encoded string
     */
    public static String encode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return " ";
        }
    }

    /**
     * Decode a url formatted string
     *
     * @param message the encoded string
     * @return the encoded string
     */
    public static String decode(String message) {
        try {
            return URLDecoder.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return " ";
        }
    }

}

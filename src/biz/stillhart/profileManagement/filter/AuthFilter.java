package biz.stillhart.profileManagement.filter;

import biz.stillhart.profileManagement.utils.Settings;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Patrick Stillhart on 11/1/14.
 */

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    public AuthFilter() {  }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {   }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String reqURI = req.getRequestURI();
            HttpServletResponse res = (HttpServletResponse) response;

            HttpSession ses = req.getSession(false);

            //  allow user to proceed if url is index.xhtml or user logged in
            if( ses != null && ses.getAttribute("username") != null ) {// Is logged in
                   if(isPublicPage(reqURI))
                       res.sendRedirect(req.getContextPath() + "/" + Settings.PRIVATE_HOME + ".xhtml");
                   else
                       chain.doFilter(request, response);
            } else { // Not logged in
                if(!isPublicPage(reqURI)  && !reqURI.contains("javax.faces.resource"))
                    res.sendRedirect(req.getContextPath() + "/" + Settings.PUBLIC_HOME + ".xhtml");
                else
                    chain.doFilter(request, response);
            }

        }
        catch(Throwable t) {
            System.out.println( t.getMessage());
        }
    }

    private boolean isPublicPage(String request){
        for(String compare : Settings.PUBLIC_PAGES)
            if(request.contains(compare)) return true;
        return false;
    }

    @Override
    public void destroy() {  }
}
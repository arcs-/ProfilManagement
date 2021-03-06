package biz.stillhart.profileManagement.filter;

import biz.stillhart.profileManagement.utils.Settings;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Patrick Stillhart on 11/1/14.
 * Checks if a user have access to a certain page and forwards if not
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Filter for all pages
     * Allow public page if not logged in (no session)
     * Allow private page if logged in
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            String reqURI = request.getRequestURI();
            HttpServletResponse res = (HttpServletResponse) response;

            HttpSession session = request.getSession(false);


            //  allow user to proceed if url is index.xhtml or user logged in
            if (session != null && session.getAttribute("username") != null) {// Is logged in
                if (isPublicPage(reqURI))
                    res.sendRedirect(request.getContextPath() + "/" + Settings.PRIVATE_HOME + ".xhtml");
                else
                    chain.doFilter(request, response);

            } else {
                if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid() && !isPublicPage(reqURI)) // session expired
                    res.sendRedirect(request.getContextPath() + "/" + Settings.PUBLIC_HOME + ".xhtml?state=expired");
                else if (!isPublicPage(reqURI) && !reqURI.contains("javax.faces.resource")) {
                    res.sendRedirect(request.getContextPath() + "/" + Settings.PUBLIC_HOME + ".xhtml");
                } else
                    chain.doFilter(request, response);
            }

        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }

    /**
     * Check if attribute is a public page
     *
     * @param request the page to check
     * @return true if public page
     */
    private boolean isPublicPage(String request) {
        for (String compare : Settings.PUBLIC_PAGES)
            if (request.contains(compare)) return true;
        return false;
    }

    @Override
    public void destroy() {
    }
}
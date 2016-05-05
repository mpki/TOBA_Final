/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.sessions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matt
 */
public class Filter {
    private FilterConfig filterConfig = null;
   
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(
                        ServletRequest request,
                        ServletResponse response,
                        FilterChain chain
                        ) throws ServletException
    {
        
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ServletContext sc = filterConfig.getServletContext();
        
        String filterName = filterConfig.getFilterName();
        String servletPath = "Servlet path: " + httpRequest.getServletPath();
        
        sc.log(filterName + " | "+ servletPath + " | before request");
        
        try {
            chain.doFilter(request, response);
        } catch (IOException ex) {
            Logger.getLogger(Filter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sc.log(filterName + " | " + servletPath + " | after request");
        
        
    }
    
    public void destroy()
    {
        filterConfig = null;
    }
}

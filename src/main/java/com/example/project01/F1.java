package com.example.project01;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "Filter")
public class F1 implements Filter {

    private ServletContext context;

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String requestPath = req.getRequestURI();
        System.out.println("request path:" + requestPath);
        if (requestPath.equals("/login") || requestPath.equals("/") || requestPath.equals("/register") || requestPath.equals("/index.jsp") || session != null && session.getAttribute("benutzer") != null) { // change "user" for the session attribute you have defined

            chain.doFilter(req, res); // Logged-in user found, so just continue request.

        } else {
            // No logged-in user found, so redirect to login page.
            res.sendRedirect("index.jsp");
        }
        //chain.doFilter(request, response);
    }
}

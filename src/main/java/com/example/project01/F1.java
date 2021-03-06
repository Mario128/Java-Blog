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
        if (requestPath.contains("/login")
                || requestPath.contains("/registration")
                || requestPath.equals("/")
                || requestPath.equals("/register")
                || requestPath.equals("/index.jsp")
                || session != null && session.getAttribute("benutzer") != null)
        {
            chain.doFilter(req, res);

        } else {
            // No logged-in user found, so redirect to login page.
            System.out.println("unallowed access request");
            res.sendRedirect("index.jsp");
        }
    }
}

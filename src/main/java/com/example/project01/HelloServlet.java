package com.example.project01;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Coming soon";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String an = request.getParameter("");
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
    public void destroy() {
    }
}
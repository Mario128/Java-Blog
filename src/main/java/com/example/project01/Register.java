package com.example.project01;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String u=request.getParameter("user");
        String p=request.getParameter("pwd");
        session.setAttribute("benutzer", u);
        request.setAttribute("benutzer", u);
        request.setAttribute("password", p);
        RequestDispatcher d = request.getRequestDispatcher("intern.jsp");
        DBManager dbm=DBManager.getInstance();
        try {

            Main db = new Main();
            db.addUser(dbm.getConnection(), u, p);
            d.forward(request,  response);
            dbm.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

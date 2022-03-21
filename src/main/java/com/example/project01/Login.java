package com.example.project01;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u = request.getParameter("user");
        String p = request.getParameter("pwd");

        HttpSession session = request.getSession();
        session.setAttribute("benutzer", u);
        request.setAttribute("benutzer", u);
        //session.setAttribute("password", p);
        request.setAttribute("password", p);
        RequestDispatcher d = request.getRequestDispatcher("intern.jsp");

        try {
            Class.forName("org.postgresql.Driver");

            //Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/webhz?serverTimezone=MET", "root","root");
            Main db = new Main();
            DBManager dbm= DBManager.getInstance();
            if(!db.authorized(dbm.getConnection(), u, p)) {
                request.setAttribute("meldung", "Password oder Username ist falsch!");
                d=request.getRequestDispatcher("index.jsp");
            }
            d.forward(request,  response);
            dbm.getConnection().close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

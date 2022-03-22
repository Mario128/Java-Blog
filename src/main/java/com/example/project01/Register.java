package com.example.project01;

import model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person p = new Person();
        p.setUsername(request.getParameter("Username"));
        p.setPassword(request.getParameter("Password"));
        p.setFirstname(request.getParameter("Firstname"));
        p.setEmail(request.getParameter("Email"));
        p.setIsAdmin(false);

        if (userValidation(p)) {

            session.setAttribute("benutzer", p.getUsername());
            request.setAttribute("benutzer", p.getUsername());
            request.setAttribute("password", p.getPassword());
            DBManager dbm = DBManager.getInstance();
            try {

                Main db = new Main();
                if(db.addUser(dbm.getConnection(), p)) {
                    RequestDispatcher d = request.getRequestDispatcher("intern.jsp");
                    d.forward(request, response);
                    dbm.getConnection().close();
                }
                else {
                    RequestDispatcher r = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("RegistrationMessage", "Fehlerhafte Eingabe");
                    r.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            RequestDispatcher r = request.getRequestDispatcher("index.jsp");
            request.setAttribute("RegistrationMessage", "Fehlerhafte Eingabe");
            r.forward(request, response);
        }
    }
    private boolean userValidation(Person p) {
        boolean isValid = false;
        if((p.getFirstname() != null)
                && (p.getEmail() != null)
                && (p.getEmail().contains("@"))
                && (p.getPassword().length() > 5)
                && (p.getUsername() != null)
        )
        {
            DBManager db = DBManager.getInstance();
            Main main = new Main();

            try {
                Connection con = db.getConnection();
                if((main.checkDoubleUsername(con, p.getUsername())) && (main.checkDoubleEmail(con, p.getEmail())))
                {
                    isValid = true;
                }
                else {
                    isValid = false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }
}

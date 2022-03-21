package com.example.project01;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/GetUser")
public class GetUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId=request.getParameter("userId");
        int userid=Integer.parseInt(strId);
        DBManager dm=DBManager.getInstance();
        Connection con=null;
        Main m=new Main();
        String username="";
        try {
            con=dm.getConnection();
            username=m.getUsername(con, userid);
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                dm.closeConnection(con);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(username);
        Gson gson= new Gson();
        String jsonResult=gson.toJson(username);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.append(jsonResult);
        out.flush();
    }
}

package com.example.project01;

import com.google.gson.Gson;
import model.Blog;
import model.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/GetBlogs")
public class GetBlogs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId=request.getParameter("themenId");
        int themenId=Integer.parseInt(strId);
        DBManager dm=DBManager.getInstance();
        Connection con=null;
        Main m=new Main();
        Category c=new Category();

        try {
            con=dm.getConnection();
            c=m.getCategory(con, themenId);
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
        Gson gson= new Gson();
        System.out.println(c.getBlogs().size());
        ArrayList<Blog> blogs = c.getBlogs();
        String jsonResult=gson.toJson(blogs);
        System.out.println("Json result string: " + jsonResult);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.append(jsonResult);
        out.flush();

    }
}

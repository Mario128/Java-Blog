package com.example.project01;
import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public boolean addUser(Connection con, Person p) throws SQLException {
        boolean success = false;
        String insert = "insert into users (username, password, firstname, isadmin, email)" +
                "values (?, ?, ?, false, ?)";
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(insert);
            stm.setString(1, p.getUsername());
            stm.setString(2, p.getPassword());
            stm.setString(3, p.getFirstname());
            stm.setString(4, p.getEmail());

            int anz = stm.executeUpdate();
            if (anz == 1) {
                success = true;
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            if(stm != null) {
                stm.close();
            }
        }
        return success;
    }

    public void addCategory(Connection con, String catname) {
        try {
            String inserString = "INSERT INTO category (cat_name) VALUES (?);";
            PreparedStatement updateCategory = con.prepareStatement(inserString);
            updateCategory.setString(1, catname);
            updateCategory.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addBlog(Connection con, String catname, String header, String content, String username) {
        try {
            String select = "SELECT * FROM category";
            PreparedStatement getSet = con.prepareStatement(select);
            ResultSet resultset = getSet.executeQuery();
            int catid = 0;
            while (resultset.next()) {
                if (resultset.getString("cat_name") == catname) {
                    catid = resultset.getInt("category_id");
                }
            }

            select = "SELECT * FROM users";
            getSet = con.prepareStatement(select);
            resultset = getSet.executeQuery();
            int userid = 0;
            while (resultset.next()) {
                if (resultset.getString("username") == username) {
                    userid = resultset.getInt("user_id");
                }
            }

            String inserString = "INSERT INTO blog (category_id, header, content, user_id) VALUES (?,?,?,?);";
            PreparedStatement updateBlog = con.prepareStatement(inserString);
            updateBlog.setInt(1, catid);
            updateBlog.setString(2, header);
            updateBlog.setString(3, content);
            updateBlog.setInt(4, userid);
            updateBlog.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addBlog(Connection con, int catid, String header, String content, int userid) {
        try {
            String inserString = "INSERT INTO blog (category_id, header, content, user_id) VALUES (?,?,?,?);";
            PreparedStatement updateBlog = con.prepareStatement(inserString);
            updateBlog.setInt(1, catid);
            updateBlog.setString(2, header);
            updateBlog.setString(3, content);
            updateBlog.setInt(4, userid);
            updateBlog.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addComment(Connection con, String content, String blogheader, String username) {
        try {
            String select = "SELECT * FROM blog";
            PreparedStatement getSet = con.prepareStatement(select);
            ResultSet resultset = getSet.executeQuery();
            int blogid = 0;
            while (resultset.next()) {
                if (resultset.getString("header") == blogheader) {
                    blogid = resultset.getInt("blog_id");
                }
            }

            select = "SELECT * FROM users";
            getSet = con.prepareStatement(select);
            resultset = getSet.executeQuery();
            int userid = 0;
            while (resultset.next()) {
                if (resultset.getString("username") == username) {
                    userid = resultset.getInt("user_id");
                }
            }

            String inserString = "INSERT INTO comments (content, blog_id, user_id) VALUES (?,?,?);";
            PreparedStatement updateCom = con.prepareStatement(inserString);
            updateCom.setString(1, content);
            updateCom.setInt(2, blogid);
            updateCom.setInt(3, userid);
            updateCom.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void addComment(Connection con, String content, int blogid, int userid) {
        try {
            String inserString = "INSERT INTO comments (content, blog_id, user_id) VALUES (?,?,?);";
            PreparedStatement updateCom = con.prepareStatement(inserString);
            updateCom.setString(1, content);
            updateCom.setInt(2, blogid);
            updateCom.setInt(3, userid);
            updateCom.executeUpdate();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public boolean authorized(Connection con, String username, String password) throws SQLException {
        String select = "SELECT * FROM users WHERE username=? AND password =?";
        PreparedStatement getSet = con.prepareStatement(select);
        getSet.setString(1, username);
        getSet.setString(2, password);
        ResultSet resultset = getSet.executeQuery();
        if(resultset.next()) {
            return true;
        }
        return false;
    }
    public void showUsers(Connection con) throws SQLException{
        String select="SELECT * FROM users";
        PreparedStatement getSet = con.prepareStatement(select);
        ResultSet resultset = getSet.executeQuery();
        while(resultset.next()) {
            System.out.println(resultset.getString("username"));
            System.out.println(resultset.getString("password"));
            System.out.println();
        }
    }
    public String getUsername(Connection con, int id) throws SQLException {
        String select="SELECT * FROM users WHERE user_id=?";
        PreparedStatement getSet = con.prepareStatement(select);
        getSet.setInt(1, id);
        ResultSet resultset = getSet.executeQuery();
        while(resultset.next()) {
            return resultset.getString("username");
        }
        return "UNKNOWN";
    }
    public Blog getBlog(Connection con, int bid) throws SQLException {
        String select="SELECT * FROM blog WHERE blog_id=?";
        PreparedStatement getSet=con.prepareStatement(select);
        getSet.setInt(1, bid);
        ResultSet resultset=getSet.executeQuery();
        Blog b=new Blog();
        while(resultset.next()) {
            b.setBlog_id(resultset.getInt("blog_id"));
            b.setCategory_id(resultset.getInt("category_id"));
            b.setHeader(resultset.getString("header"));
            b.setContent(resultset.getString("content"));
            b.setUser_id(resultset.getInt("user_id"));
        }

        //Get comments and add comments to blog

        return b;
    }
    public Category getCategory(Connection con, int cid) throws SQLException {
        String select="SELECT * FROM category WHERE category_id=?";
        PreparedStatement getSet=con.prepareStatement(select);
        getSet.setInt(1, cid);
        ResultSet resultset=getSet.executeQuery();
        Category c=new Category();
        while(resultset.next()){
            c.setCategory_id(resultset.getInt("category_id"));
            c.setCat_name(resultset.getString("cat_name"));
        }
        ArrayList<Blog> bs=new ArrayList<>();
        select="SELECT * FROM blog WHERE category_id=?";
        getSet=con.prepareStatement(select);
        getSet.setInt(1, cid);
        resultset=getSet.executeQuery();
        while(resultset.next()) {
            Blog b=this.getBlog(con, resultset.getInt("blog_id"));
            bs.add(b);
        }
        c.setBlogs(bs);
        return c;
    }
    public ArrayList<Category> getCategories(Connection con) throws SQLException{
        String select="SELECT * FROM category";
        PreparedStatement getSet=con.prepareStatement(select);
        ResultSet resultset=getSet.executeQuery();
        ArrayList<Category> c=new ArrayList<>();
        while(resultset.next()) {
            c.add(this.getCategory(con, resultset.getInt("category_id")));
        }
        return c;
    }
    public boolean checkDoubleUsername(Connection con, String usr) throws SQLException {
        PreparedStatement st = con.prepareStatement("Select * from users where username = ?");
        st.setString(1, usr);
        ResultSet rs = st.executeQuery();

        if(rs.next()) {
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkDoubleEmail(Connection con, String email) throws SQLException {
        PreparedStatement st = con.prepareStatement("Select * from users where email = ?");
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        if(rs.next()) {
            return false;
        }
        else{
            return true;
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       /*
        Main db = new Main();
        // db.addCategory(c, "Astronomy");
        // db.addCategory(c, "Spacecraft");
        // db.addCategory(c, "Aircraft");
        c.close();*/
    }

}

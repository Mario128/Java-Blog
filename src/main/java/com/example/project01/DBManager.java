package com.example.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private String dburl;
    private String user;
    private String password;
    private Connection con;
    private String driver;
    private static DBManager db=null;

    public static DBManager getInstance() {
        if(db==null) {
            db=new DBManager();
        }
        return db;
    }
    public void init(String url, String u, String p, String driver) throws SQLException {
        this.dburl=url;
        this.user=u;
        this.password=p;
        this.driver=driver;
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException{
        Connection con=DriverManager.getConnection(dburl,user,password);
        this.con=con;
        return con;
    }
    public String getDriver() {
        return driver;
    }
    public void closeConnection(Connection con) throws SQLException{
        con.close();
    }
    public void closeConnection() throws SQLException{
        con.close();
    }
}

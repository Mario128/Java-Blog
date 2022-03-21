package model;

import java.util.ArrayList;

public class Blog {
    private int blog_id;
    private int category_id;
    private String header;
    private String content;
    private int user_id;
    //private ArrayList<Comment> comments=new ArrayList<>();

    public Blog() {}
    public Blog(int bid, int cid, String head, String content, int uid) {
        blog_id=bid;
        category_id=cid;
        header=head;
        this.content=content;
        user_id=uid;
    }
    /*
    public Blog(int bid, int cid, String head, String content, int uid, ArrayList<Comment> comments) {
        blog_id=bid;
        category_id=cid;
        header=head;
        this.content=content;
        user_id=uid;
        this.comments=comments;
    }*/
    public int getBlog_id() {
        return blog_id;
    }
    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    /*
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }*/

}

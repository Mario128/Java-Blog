package model;

import java.util.ArrayList;

public class Comment {
    private int comment_id;
    private String content;
    private int blog_id;
    private int user_id;
    private String username;
    private String[] path;
    public Comment() {}
    public Comment(int cid, String content, int bid, int uid) {
        comment_id=cid;
        this.content=content;
        blog_id=bid;
        user_id=uid;
    }
    public Comment(int cid, String content, int bid, int uid, String[] path,String username) {
        comment_id=cid;
        this.content=content;
        blog_id=bid;
        user_id=uid;
        this.path=path;
        this.username=username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getComment_id() {
        return comment_id;
    }
    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getBlog_id() {
        return blog_id;
    }
    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String[] getPath() {
        return path;
    }
    public void setPath(String[] path) {
        this.path = path;
    }

}

package model;

import java.util.ArrayList;

public class Category {
    private int category_id;
    private String cat_name;
    private ArrayList<Blog> blogs;
    public Category() {}
    public Category(int cid, String can) {
        category_id=cid;
        cat_name=can;
    }
    public Category(int cid, String can,  ArrayList<Blog> blogs) {
        category_id=cid;
        cat_name=can;
        this.blogs=blogs;
    }
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public String getCat_name() {
        return cat_name;
    }
    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
    public ArrayList<Blog> getBlogs() {
        return blogs;
    }
    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }

}

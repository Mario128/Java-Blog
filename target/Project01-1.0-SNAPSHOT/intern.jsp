<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.ArrayList"%>
<%@ page import="model.Category" %>
<%@ page import="com.example.project01.Main" %>
<%@ page import="com.example.project01.DBManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Welcome!</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3" style="background-color:lavender;">
      <br>
      <br>
      <h5>
        Welcome
        <%
          String usr = (String) request.getAttribute("benutzer");
          out.println(usr);
          ArrayList<Category> c = new ArrayList<>();
          Main db = new Main();
          c = db.getCategories(DBManager.getInstance().getConnection());
        %>
      </h5>
      <br>
      <br>
      <h5>Available Categories:</h5>
      <%
        for (Category ca : c) {
          out.print("<button class='btn' type='button' onclick='AddBlog("+ca.getCategory_id()+")'>"+ca.getCat_name()+"</button>");
          out.print("<br><br>");

        }
      %>
      <br>
      <br>
      <div id="Blogname"></div>
      <div id="Blogliste"></div>
      <br>
      <button class="btn btn-primary" type="button" onclick="WriteBlog()">Add a Blog</button>
    </div>
    <div class="col-sm-9" style="background-color:lavenderblush;">
      <br>
      <br>
      <div id="WriteBlog"></div>
      <div id="Blogcontent"></div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function WriteBlog(){
    RemoveBlog();
    var text="<form method='post' action='AddBlog'>";
    text+="Überschrift:<br> <input type='text' class='form-control'";
    text+="name='header'><br> Inhalt: <input type='text'";
    text+="class='form-control' name='content'><br>";
    text+="<button class='btn btn-primary' type='submit'>Senden</button>";
    // MIt Ajax Request senden, und auf seite bleiben.
    text+="<br>";
    document.getElementById('WriteBlog').innerHTML=text;
  }
  var blogliste;

  function RemoveBlog(){
    document.getElementById('Blogcontent').innerHTML="";
  }
  function HideWrite(){
    document.getElementById('WriteBlog').innerHTML="";
  }
  function ArtikelArrayFormatieren(arr){
    var len, text, i , elem;
    len=arr.length;
    blogliste=arr;
    text="<dl>";
    for(i =0;i<len;i++){
      elem=arr[i];
      console.log("header nr" +i+ elem.header)
      text+="<dt onclick='BlogAnzeigen("+i+")' class='btn'>"+elem.header+"</dt><br>";
      //text+="<dt id='B"+i+"'></dt><br>";
      document.getElementById('Blogname').innerHTML="<h5>Available Blogs</h5>";

    }
    text+="</dl>";
    return text;
  }
  function AddBlog(themenId){
    console.log("inside addblog")
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange=function(){
      if(this.readyState==4 && this.status==200){
        var arr = JSON.parse(this.responseText);
        var htmlcode=ArtikelArrayFormatieren(arr);
        document.getElementById('Blogliste').innerHTML=htmlcode;
      }
    };
    xhttp.open("POST","GetBlogs",true);
    xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhttp.send("themenId="+themenId);
  }

  function BlogAnzeigen(index){
    HideWrite();
    var xhttp=new XMLHttpRequest();
    var blog=blogliste[index];
    xhttp.onreadystatechange=function(){
      if(this.readyState==4 && this.status==200){
        var text="<br><dt>"+blog.header+"</dt><br><br>";
        text+="<dt>"+blog.content+"</dt><br>";
        text+="<dt>Created by " + this.responseText+"</dt>";
        text+="<dt onclick='RemoveBlog()' class='btn btn-primary'>Hide Blog</dt>";
        document.getElementById('Blogcontent').innerHTML=text;
      }
    };
    xhttp.open("POST","GetUser",true);
    xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhttp.send("userId="+blog.user_id);
  }
</script>
</body>
<style>
  .comment{
    margin-left:50px;
  }
</style>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Log into your Account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="margin-left:10%;margin-right:50%;margin-top:5%;">

    <p style="font-size:30px;">Login:</p>
    <%@include file="Login/login.jsp"%>
    <br>
    <p style="font-size:30px;">Register:</p>
    <%@include file="Registration/registration.jsp"%>

<script src="Login/login.js" type="text/javascript"></script>
<script src="Registration/registration.js" type="text/javascript"></script>


</body>
</html>
<%@ page import="org.example.socialmedia_proxy.EnvLoader" %>
<%@ page import="org.example.socialmedia_proxy.DB_CRUD.UserProfile_DBCRUD" %>
<%@ page import="org.example.socialmedia_proxy.DB.MySQL_DB" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% EnvLoader.loadEnv();
    UserProfile_DBCRUD.getUserProfile_DBCRUD(MySQL_DB.setConnection(
            System.getProperty("MYSQL_HOST"),
            System.getProperty("MYSQL_PORT"),
            System.getProperty("MYSQL_DATABASE"),
            System.getProperty("MYSQL_USERNAME"),
            System.getProperty("MYSQL_PASSWORD")
    ));
    UserProfile_DBCRUD.getUserProfile_DBCRUD();
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="userProfile-servlet">Hello Servlet</a>
</body>
</html>
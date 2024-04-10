<%@ page import="org.example.socialmedia_proxy.DB_CRUD.DB_CRUD" %>
<%@ page import="org.example.socialmedia_proxy.DB.MySQL_DB" %>
<%@ page import="org.example.socialmedia_proxy.EnvLoader" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    EnvLoader.loadEnv();
   new DB_CRUD(
            MySQL_DB.setConnection(
            System.getProperty("MYSQL_HOST"),
            System.getProperty("MYSQL_PORT"),
            System.getProperty("MYSQL_DATABASE"),
            System.getProperty("MYSQL_USERNAME"),
            System.getProperty("MYSQL_PASSWORD")
    ));
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
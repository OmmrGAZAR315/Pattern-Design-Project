<%@ page import="org.example.socialmedia_proxy.EnvLoader" %>
<%@ page import="org.example.socialmedia_proxy.DB.DB" %>
<%@ page import="org.example.socialmedia_proxy.DB_Platforms.MySQL_DB" %>
<%@ page import="java.io.Writer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        try{
            EnvLoader.loadEnv();
            new DB(
                    MySQL_DB.setConnection(
                            System.getProperty("MYSQL_HOST"),
                            System.getProperty("MYSQL_PORT"),
                            System.getProperty("MYSQL_DATABASE"),
                            System.getProperty("MYSQL_USERNAME"),
                            System.getProperty("MYSQL_PASSWORD")
                    ));
        }catch (Exception e){
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Database connection failed: ");
        }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>

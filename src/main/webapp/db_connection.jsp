<%@ page import="org.example.socialmedia_proxy.DB.DB_Platforms.MySQL_DB" %>
<%@ page import="org.example.socialmedia_proxy.DB.EnvLoader" %>
<%@ page import="org.example.socialmedia_proxy.DB.DatabaseConfig" %>
<%@ page import="org.example.socialmedia_proxy.DB.DB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {
        EnvLoader.loadEnv();

System.out.println("DB_CLASS: " + DatabaseConfig.DB_CLASS.getValue());
        new MySQL_DB().setConnection(
                DatabaseConfig.DB_CLASS.getValue(),
                DatabaseConfig.DB_CONNECTION.getValue(),
                DatabaseConfig.HOST.getValue(),
                DatabaseConfig.PORT.getValue(),
                DatabaseConfig.DATABASE.getValue(),
                DatabaseConfig.USERNAME.getValue(),
                DatabaseConfig.PASSWORD.getValue()
        );
    } catch (Exception e) {
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write("Database connection failed!: " + e.getMessage());
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>

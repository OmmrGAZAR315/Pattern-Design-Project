<%@ page import="org.example.dpproject.Model.UserProfile" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="protectPage.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<% Object user = request.getSession().getAttribute("user");
    UserProfile userProfile = null;
    if (user != null) {
        userProfile = (UserProfile) user;
    }
%>
<h1><%="Hell World, MR." + userProfile.getName()%>
    <h2><%="username: " + userProfile.getUsername()%>
    </h2>
    <h2 style="display: flex; align-items: center;">
        <%= "password: " + userProfile.getPassword() %>
        <form action="showPassword" method="post" style="margin-left: 1%;">
<%--            <input type="hidden" name="id" value="<%= request.getSession().getAttribute("id") %>"/>--%>
            <button type="submit">Show</button>
        </form>
    </h2>

    <br/>
    <a href="user_ids.jsp">Hello Servlet</a>
    <a href="logout">Logout</a>
</body>
</html>

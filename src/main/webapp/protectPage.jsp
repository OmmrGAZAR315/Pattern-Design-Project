<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession session2 = request.getSession(false);
    if (session2 == null || session2.getAttribute("authenticated") == null) {
%>
<jsp:forward page="login.jsp"/>
<% } %>
<html>
<head>
</head>
<body>
<!-- Your content here -->
</body>
</html>

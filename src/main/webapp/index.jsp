<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
                "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Manager</title>
</head>
<body>
    <%
        String userAgent = request.getHeader("user-agent");

        if(userAgent.matches(".*BlackBerry.*") || userAgent.matches(".*Android.*") || userAgent.matches(".*iPhone.*") || userAgent.matches(".*iPad.*")) {
            response.sendRedirect(request.getContextPath()+"/pages/mobile/index.xhtml");
        } else {
            response.sendRedirect(request.getContextPath()+"/pages/web/index.xhtml");
        }
    %>
    <jsp:forward page="/pages/mobile/index.xhtml" />
</body>
</html>
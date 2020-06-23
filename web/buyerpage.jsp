<%@include file="header.jsp" %>

<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
    int n = session.getMaxInactiveInterval();
%>
<html>
    <head>
        <title>E-CommerceWebApp</title>
    </head>

    <body>
        <h1>Welcome <%=user%></h1>
        <h3>Your session will expire in <%=n%> seconds.</h3>
        <hr>

        <pre>
        <a href="CategoryPage">Explore Store</a>
        <a href="search.jsp">Search</a>
        <a href="DisplayCart">View Cart</a>
        <a href="EndSession">LogOut</a>
        </pre>
        <hr>
    </body>
</html>

<%@include file="footer.jsp" %>

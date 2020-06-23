<%@page errorPage="myerror.jsp" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Connection.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>

<%!
    int getDiscount(int price) {
        int discount = 0;
        if (price >= 1000) {
            discount = (price * 20) / 100;
        } else {
            discount = (price * 10) / 100;
        }
        return discount;
    }
%>

<%
    int code = Integer.parseInt(request.getParameter("t1"));
    Connection con = ConnectionProvider.connect();
    String sql = "SELECT * FROM products WHERE pcode=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, code);
    ResultSet rs = ps.executeQuery();
    rs.next();
    String s1 = rs.getString("pcode");
    String s2 = rs.getString("pname");
    String s3 = rs.getString("pdesc");
    String s4 = rs.getString("pcatg");
    String s5 = rs.getString("price");
    con.close();
%>

<%int disc = getDiscount(Integer.parseInt(s5));%>

<html>
    <head>
        <title>E-CommerceWebApp</title>
    </head>
    <body>
        <h1>Search Result</h1>
        <hr>
        <form>
            <pre>
                Product Code:   <%=s1%>
                Name        :   <%=s2%>
                Description :   <%=s3%>
                Category    :   <%=s4%>
                MRP         :   <%=s5%>
                Discount    :   <%=disc%>
                Final Price :   <%=Integer.parseInt(s5) - disc%>
                        
            </pre>
        </form>
        <hr>
        <a href="search.jsp">Search More</a><br>
        <a href="buyerpage.jsp">Home</a>
        <hr>
    </body>
</html>

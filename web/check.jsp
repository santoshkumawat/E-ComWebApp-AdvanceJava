<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Connection.ConnectionProvider"%>
<%
    String id = request.getParameter("id");
    String sql = "SELECT email FROM users WHERE email=?";
    Connection con = ConnectionProvider.connect();
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, id);
    ResultSet rs = ps.executeQuery();
    boolean b = rs.next();
    if (b == false) {
        out.println("Available");
    } else {
        out.println("Not Available");
    }
    con.close();
%>

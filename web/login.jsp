<%@taglib uri="/WEB-INF/tlds/mylib.tld" prefix="sample" %>

<%
    String v1 = "", v2 = "";

    //read all the cookies coming with reuest
    Cookie ck[] = request.getCookies();

    //search for the desired one
    if (ck != null) {
        for (int i = 0; i < ck.length; i++) {
            String name = ck[i].getName();
            if (name.equals("id")) {
                v1 = ck[i].getValue();
            } else if (name.equals("pass")) {
                v2 = ck[i].getValue();
            }
        }
    }
%>

<html>
    <head>
        <title>E-CommerceWebApp</title>
    </head>

    <body>
        <sample:info/>
        <hr>
        <h1>LogIn Here...</h1>
        <hr>
        <form action="VerifyUser" method="post">
            <table>
                <tr>   
                    <td>Email ID</td><td><input type="text" name="email" value="<%=v1%>"/></td>
                </tr>
                <tr>
                    <td>Password</td><td><input type="password" name="password" value="<%=v2%>"/></td>
                </tr>
                <tr>
                    <td>User Type</td><td><select name="utype"><option>Admin</option><option>Buyer</option></select></td>
                </tr>
                <tr>
                    <td>Remember Me</td><td><input type="checkbox" checked="checked" name="savep" value="yes"></td>
                </tr>
                <tr>
                    <td></td><td><input type="reset" value="Reset"/><input type="submit" value="LogIn"/></td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="register.jsp">New User</a>
        <hr>
    </body>
</html>





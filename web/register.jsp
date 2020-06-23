
<html>
    <head>
        <title>E-CommerceWebApp</title>
    </head>

    <script>

        var request;

        function one() {
            var v1 = document.getElementById("email").value;
            request = new XMLHttpRequest();
            request.onreadystatechange = two;
            request.open("get","check.jsp?id=" + v1, true);
            request.send(null);
        }

        function two() {
            if (request.status == 200 && request.readyState == 4) {
                document.getElementById("data").innerHTML = request.responseText;
            }
        }
    </Script>

    <body>
        <h1>Register Here...</h1>
        <hr>
        <form action="SaveUser" method="post">
            <table>
                <tr>   
                    <td>First Name</td><td><input type="text" name="fname"/></td>
                </tr>
                <tr>   
                    <td>Last Name</td><td><input type="text" name="lname"/></td>
                </tr>
                <tr>   
                    <td>Email Address</td><td><input type="text" id="email" name="email"/></td><td><input type="button" value="Check" onclick="one()"/></td><td><div id="data">Result</div></td>
                </tr>
                <tr>
                    <td>Password</td><td><input type="password" name="password"/></td>
                </tr>
                <tr>   
                    <td>Confirm Password</td><td><input type="password" name="cpassword"/></td>
                </tr>
                <tr>   
                    <td>Phone Number</td><td><input type="text" name="phone"/></td>
                </tr>
                <tr>
                    <td></td><td><input type="reset" value="Reset"/><input type="submit" value="Register"/></td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="login.jsp">Already a user! Login Here</a>
        <hr>
    </body>
</html>

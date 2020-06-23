
<html>
    <head>
        <title>E-CommerceWebApp</title>
    </head>

    <body>
        <h1>Add Products</h1>
        <hr>
        <form action="SaveProducts" method="post">
            <table>
                <tr>   
                    <td>Product Code</td><td><input type="text" name="pcode"/></td>
                </tr>
                <tr>   
                    <td>Product Name</td><td><input type="text" name="pname"/></td>
                </tr>
                <tr>   
                    <td>Product Description</td><td><input type="text" name="pdesc"/></td>
                </tr>
                <tr>   
                    <td>Price</td><td><input type="text" name="price"/></td>
                </tr>
                <tr>
                    <td>Product Category</td>
                    <td>
                        <select name="pcatg">
                            <option>Electronics</option>
                            <option>Computers</option>
                            <option>Mobiles</option>
                            <option>Books</option>
                            <option>Fashions</option>
                            <option>Others</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td><td><input type="reset" value="Reset"/><input type="submit" value="Save Product"/></td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="adminpage.jsp">Go Back to Admin Dashboard</a>
        <hr>
    </body>
</html>

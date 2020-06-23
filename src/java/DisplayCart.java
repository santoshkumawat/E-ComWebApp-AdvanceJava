
import Connection.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //show the cart to user
        HttpSession session = request.getSession();
        HashSet<String> set = (HashSet<String>) session.getAttribute("cart");

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Your Cart</h1><hr>");
        if (set == null) {
            out.println("<h3>Your Cart is Empty</h3>");
            out.println("<hr>");
            out.println("<h3><a href=CategoryPage>Start Buying</a></h3>");
        } else {
            out.println("<h3>Your Products in Cart is:</h3>");

            try {
                String sql = "SELECT * FROM products WHERE pcode IN " + set;
                sql = sql.replace('[', '(');
                sql = sql.replace(']', ')');
                Connection con = ConnectionProvider.connect();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                out.println("<table border=1>");
                out.println("<tr>");
                out.println("<th>Code</th>");
                out.println("<th>Name</th>");
                out.println("<th>Description</th>");
                out.println("<th>Category</th>");
                out.println("<th>Price</th>");
                out.println("</tr>");

                int sum = 0;
                while (rs.next()) {
                    String s1 = rs.getString("pcode");
                    String s2 = rs.getString("pname");
                    String s3 = rs.getString("pdesc");
                    String s4 = rs.getString("pcatg");
                    int s5 = rs.getInt("price");
                    sum = sum + s5;
                    out.println("<tr>");
                    out.println("<td>" + s1 + "</td>");
                    out.println("<td>" + s2 + "</td>");
                    out.println("<td>" + s3 + "</td>");
                    out.println("<td>" + s4 + "</td>");
                    out.println("<td align=right>" + s5 + "</td>");
                    out.println("<td align=center><a href=RemoveProduct?code=" + s1 + ">X</a></td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td></td><td></td><td></td>");
                out.println("<td>Total</td><td align=right>" + sum + "</td>");
                out.println("</tr>");
                out.println("</table>");
            } catch (Exception e) {
                out.println(e);
            }
            out.println("<hr>");
            out.println("<h3><a href=CategoryPage>Add More Products</a></h3>");

        }

        out.println("<h3><a href=buyerpage.jsp>Home</a></h3>");
        out.println("</body>");
        out.println("</html>");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

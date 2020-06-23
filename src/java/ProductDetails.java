
import Connection.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetails extends HttpServlet {

    private Connection con;
    private PreparedStatement ps;

    @Override
    public void init() {
        try {
            String sql = "SELECT * FROM PRODUCTS where pcode=?";
            con = ConnectionProvider.connect();
            ps = con.prepareStatement(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String code = request.getParameter("code");

        try {
            ps.setInt(1, Integer.parseInt(code));
            ResultSet rs = ps.executeQuery();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>E-CommerceWebApp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Product Details</h1>");
            out.println("<hr>");
            rs.next();
            String s1 = rs.getString(1);    //pcode
            String s2 = rs.getString(2);    //pname
            String s3 = rs.getString(3);    //pdesc
            String s4 = rs.getString(4);    //price
            String s5 = rs.getString(5);    //pcatg

            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<td>Product Code</td>");
            out.println("<td>" + s1 + "</td");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Name</td>");
            out.println("<td>" + s2 + "</td");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Description</td>");
            out.println("<td>" + s3 + "</td");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Price</td>");
            out.println("<td>" + s4 + "</td");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Category</td>");
            out.println("<td>" + s5 + "</td");
            out.println("</tr>");
            out.println("</table>");

            out.println("<hr>");
            out.println("<a href=CartManager?code="+s1+">Add to Cart</a><br><br>");
            out.println("<a href=CategoryPage>Categories</a><br><br>");
            out.println("<a href=buyerpage.jsp>Go Back to Buyer Dashboard</a>");
            out.println("<hr>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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

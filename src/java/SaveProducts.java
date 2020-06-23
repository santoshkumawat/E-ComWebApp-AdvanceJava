
import Connection.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProducts extends HttpServlet {

    private Connection con;
    private PreparedStatement ps;

    @Override
    public void init() {
        try {
            String sql = "INSERT INTO products VALUES(?,?,?,?,?)";
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

        String s1 = request.getParameter("pcode");
        String s2 = request.getParameter("pname");
        String s3 = request.getParameter("pdesc");
        String s4 = request.getParameter("price");
        String s5 = request.getParameter("pcatg");

        try {
            ps.setInt(1, Integer.parseInt(s1));
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setInt(4, Integer.parseInt(s4));
            ps.setString(5, s5);
            ps.executeUpdate();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>E-CommerceWebApp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<hr>");
            out.println("<h1>Product successfully added</h1>");
            out.println("<h3><a href=adminpage.jsp>Go Back to Admin Page</a></h3>");
            out.println("<h3><a href=addproducts.jsp>Add More Produts</a></h3>"); 
            out.println("<h3><a href=login.jsp>Home</a></h3>"); 
            out.println("<hr>");
            out.println("</body>");
            out.println("</html>");
        } catch (NumberFormatException | SQLException e) {
            out.println(e);
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

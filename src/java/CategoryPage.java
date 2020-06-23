
import Connection.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CategoryPage extends HttpServlet {

    private Connection con;
    private PreparedStatement ps;

    @Override
    public void init() {
        try {
            String sql = "SELECT DISTINCT pcatg FROM PRODUCTS ORDER BY pcatg";
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
        response.setContentType("text/html;charset=UTF-8");
        int n = 0;
        //reading the email from session
        //1. Fetch the session
        HttpSession session = request.getSession();
        //2. read the data from session
        String user = (String) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        }

        HashSet<String> set = (HashSet<String>) session.getAttribute("cart");
        if (set != null) {
            n = set.size();
        }

        PrintWriter out = response.getWriter();
        try {
            ResultSet rs = ps.executeQuery();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>E-CommerceWebApp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome " + user + "</h1>");
            out.println("<h3>Select Category</h3>");
            out.println("<hr>");
            while (rs.next()) {
                String s = rs.getString(1);
                out.println("<a href=ProductList?ct=" + s + ">");
                out.println(s);
                out.println("</a>");
                out.println("<br>");
            }
            out.println("<hr>");
            out.println("<h3>Total Products In Carts : " + n + "</h3>");
            out.println("<a href=buyerpage.jsp>Go Back to Buyer Dashboard</a>");
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

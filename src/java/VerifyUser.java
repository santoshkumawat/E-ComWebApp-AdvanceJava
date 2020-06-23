
import Connection.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyUser extends HttpServlet {

    private Connection con;
    private PreparedStatement ps;

    @Override
    public void init() {
        try {
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            con = ConnectionProvider.connect();
            ps = con.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
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

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String utype = request.getParameter("utype");

        if (utype.equals("Admin")) {
            if (email.equals("admin@gmail.com") && password.equals("admin")) {
                response.sendRedirect("adminpage.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else if (utype.equals("Buyer")) {
            try {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                boolean b = rs.next();
                if (b == true) {
                    //storing email in session

                    //1. fetch the session
                    HttpSession session = request.getSession();
                    //2. store the data in session
                    session.setAttribute("user", email);
                    
                    String ch = request.getParameter("savep");
                    if (ch != null) {
                        //creating cookie object
                        Cookie ck1 = new Cookie("id", email);
                        Cookie ck2 = new Cookie("pass", password);
                        //setting the maximum age of cookie(in seconds)
                        ck1.setMaxAge(60 * 60 * 24 * 7);
                        ck2.setMaxAge(60 * 60 * 24 * 7);
                        //add cookie to response
                        response.addCookie(ck1);
                        response.addCookie(ck2);
                    }
                    response.sendRedirect("buyerpage.jsp");
                } else {
                    response.sendRedirect("login.jsp");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

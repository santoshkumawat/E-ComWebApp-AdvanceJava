package mypkg;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class InfoHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            out.println("<h3>XYZ Company</h3>");
            out.println("<p>Its an Ecommerce Company</p>");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

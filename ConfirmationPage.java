import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class ConfirmationPage extends HttpServlet {
    private String title;
  
  public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    title = "Confirmation";

    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession(true);
    
    response.setContentType("text/html");
    
    String docType = "<!DOCTYPE html>";
    out.println(docType +
        "<html>\n" +
        "<head>\n" +
        "<title>" + title + "</title>\n" +
        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">" +
        "</head>\n" +
        "<body>\n" +
        "<header>\n" +
        "<table border=\"1\" width=\"100%\">\n" +
        "<tr>\n" +
        "<td width=\"80%\">\n" +
        "<h1><a href=\"index.html\">Best Deal</a></h1>\n" +
        "</td>\n" +
        "<td width=\"20%\">\n" +
        "<h4><a href=\"#\">Cart</a></h4>\n" +
        "</td>\n" +
        "</tr>\n" +
        "</table>\n" +
        "<table border=\"1\" width=\"100%\">\n" +
        "<tr>\n" +
        "<td width=\"40%\">\n" +
        "</td>\n" +
        "<td width=\"30%\">\n" +
        "<a href=\"#\">Weekly Deals</a>\n" +
        "</td>\n" +
        "<td width=\"30%\">\n" +
        "<a href=\"#\">Sign in</a>\n" +
        "</td>\n" +
        "</tr>\n" +
        "</table>\n" +
        "</header>\n");
    out.println(
        "<nav>\n" +
        "<form action=\"/ecom/CatalogPage\">\n" +
        "<table>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<input id=\"button\" type=\"submit\" name =\"product\" VALUE=\"Phones\">\n" +
        "</td>\n" +
        "</tr>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<input id=\"button\" type=\"submit\" name =\"product\" VALUE=\"Tablets\">\n" +
        "</td>\n" +
        "</tr>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<input id=\"button\" type=\"submit\" name =\"product\" VALUE=\"Laptop\">\n" +
        "</td>\n" +
        "</tr>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<input id=\"button\" type=\"submit\" name =\"product\" VALUE=\"TV\">\n" +
        "</td>\n" +
        "</tr>\n" +
        "</table>\n" +
        "</form>\n" +
        "</nav>\n" +
        "<aside>\n");

    String cartElements = (String)session.getAttribute("cart");
    out.println("<h2>Thank you for you purchase on "+  new Date() + "!<h2>");
    out.println("<br>");
    out.println("<br>");
    out.println("<h3>Items:<h3>");
    String delims = "[|]";
    String[] tokens = cartElements.split(delims);
    
    for (int i = 0; i < tokens.length; i++){
        out.println("<h3>" + tokens[i] + " - Quantity: " + (Integer)session.getAttribute(tokens[i]) + "<h3>");
        session.setAttribute(tokens[i], 0);
    }

    cartElements = new String();
    session.setAttribute("cart", cartElements);

    out.println("<br>");
    out.println("<br>");
    out.println("<h3>Confirmation Number: " + (1000000 + (Math.random() * (9999999 - 1000000))) + "<h3>");
    long theFuture = System.currentTimeMillis() + (86400 * 14 * 1000);
    out.println("<h3>Delivery Date: " + new Date(theFuture) + "<h3>");
    out.println("</aside>");
    out.println("</body>");
    out.println("</html>");
  }
}

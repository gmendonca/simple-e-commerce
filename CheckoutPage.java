import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class CheckoutPage extends HttpServlet {
  private String title;
  private String username;
  
  public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    PrintWriter out = response.getWriter();
    title = "Checkout";

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
        "<h1><a href=\"/ecom/HomePage\">Best Deal</a></h1>\n" +
        "</td>\n" +
        "<td width=\"20%\">\n" +
        "<h4><a href=\"/ecom/CartPage\">Cart</a></h4>\n" +
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
        "<td width=\"30%\">\n");
    if(session.getAttribute("username") != null){
        username = (String)session.getAttribute("username");
        out.println("Hi, <a href=\"/ecom/OrderPage\">" + username + "</a>\n");
    }else{
        out.println("<a href=\"/ecom/SignInPage\">Sign in</a>\n");
    }
    out.println(
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
        "<aside>\n" +
        "<h1 align=\"center\">" + title + "</h1>");
    out.println("<form action=\"/ecom/ConfirmationPage\" method=\"POST\">\n");
    out.println("First name: <input type=\"text\" name=\"firstname\"><br>\n");
    out.println("Last name: <input type=\"text\" name=\"lastname\"><br>\n");

    out.println("Address Line 1: <input type=\"text\" name=\"address1\"><br>\n");
    out.println("Address Line 2: <input type=\"text\" name=\"address2\"><br>\n");
    out.println("City: <input type=\"text\" name=\"city\"><br>\n");
    out.println("State: <input type=\"text\" name=\"state\"><br>\n");
    out.println("Zip Code: <input type=\"text\" name=\"zipcode\"><br>\n");
    out.println("Country: <input type=\"text\" name=\"country\"><br>\n");

    out.println("Credit Card: <input type=\"text\" name=\"creditcard\"><br>\n");
    out.println("Name Printed: <input type=\"text\" name=\"nameprinted\"><br>\n");
    out.println("Expiration Date: <input type=\"text\" name=\"expiration\"><br>\n");
    out.println("CVC: <input type=\"text\" name=\"cvc\"><br>\n");

    out.println("<input id=\"buybutton\" type=\"submit\" name =\"confirm\" VALUE=\"Confirm\">\n");
    out.println("</form>\n");
    out.println("</aside>");
    out.println("</body>");
    out.println("</html>");
  }
}

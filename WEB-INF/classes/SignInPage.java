import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class SignInPage extends HttpServlet {
  private String title;
  private String username;

  public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    PrintWriter out = response.getWriter();
    title = "Sign in";

    HttpSession session = request.getSession(true);

    response.setContentType("text/html");

    String docType = "<!DOCTYPE html>";
    out.println(docType +
        "<html>\n" +
        "<head>\n" +
        "<title>" + title + "</title>\n" +
        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">" +
        "<script type=\"text/javascript\" src=\"javascript/javascript.js\"></script>\n" +
        "</head>\n" +
        "<body onload=\"init()\">\n" +
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
        "<a href=\"#\">Weekly Deals</a>\n" +
        "</td>\n" +
        "<td width=\"30%\">\n" +
        "<form name=\"autofillform\" action=\"autocomplete\">" +
        "<table border=\"0\" cellpadding=\"5\">" +
        "<tbody>" +
        "<tr>" +
        "<td><strong>Search:</strong></td>" +
        "<td>" +
        "<input type=\"text\" size=\"40\" id=\"complete-field\" autocomplete=\"off\" onkeyup=\"doCompletion()\">" +
        "</td>" +
        "</tr>" +
        "<tr>" +
        "<td id=\"auto-row\" colspan=\"2\">" +
        "<table id=\"complete-table\" class=\"popupBox\"></table>" +
        "</td>" +
        "</tr>" +
        "</tbody>" +
        "</table>" +
        "</form>\n" +
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
    out.println("<form action=\"/ecom/HomePage\" method=\"post\">\n");
    out.println("Username: <input type=\"text\" name=\"username\"><br>\n");
    out.println("<input id=\"buybutton\" type=\"submit\" name =\"confirm\" VALUE=\"OK\">\n");
    out.println("</form>\n");
    out.println("</aside>\n");
    out.println("</body>\n");
    out.println("</html>\n");
  }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import system.products.*;

@SuppressWarnings("unchecked")

public class CatalogPage extends HttpServlet {
  private String title;
  private String username;
  private ProductData prodData;
  private HashMap catalog;


  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    prodData = new ProductData();
    catalog = prodData.getProducts();

    HttpSession session = request.getSession(true);

    title = request.getParameter("product");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
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

    Set set = catalog.entrySet();
    Iterator i = set.iterator();


    out.println("<form action=\"/ecom/CartPage\">");
    out.println("<table border=\"1\"");
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         String comp = (String)me.getValue();
         if(title.compareTo(comp) == 0){
            out.println("<tr>\n<td>");
            out.println("<img src=\"img/" + ((String)me.getKey()).replaceAll("\\s+","") + ".jpg\" alt=\"" + ((String)me.getKey()) + "\" width=\"50\" heigth=\"50\" >");
            out.println("</td>\n<td>");
            out.println("<input id=\"buybutton\" type=\"submit\" name =\"item\" label=\"ok\" value=\"Buy " + ((String)me.getKey()) + "\">\n");
            out.println("</td>\n</tr>");
         }
      }
    out.println("</table>");

    out.println("</aside>");
    out.println("</body>");
    out.println("</html>");
  }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class CatalogPage extends HttpServlet {
  private String title;
  private HashMap catalog;

  private void populateHashMap(){
    catalog.put("Droid MAXX", "Phones");
    catalog.put("Moto X", "Phones");
    catalog.put("iPhone 5S", "Phones");
    catalog.put("iPhone 5C", "Phones");
    catalog.put("Galaxy S3", "Phones");
    catalog.put("Galaxy S4", "Phones");

    catalog.put("Kindle", "Tablets");
    catalog.put("Nexus", "Tablets");
    catalog.put("Surface", "Tablets");
    catalog.put("Galaxy", "Tablets");
    catalog.put("iPad", "Tablets");
    
    catalog.put("MacBook", "Laptop");
    catalog.put("Asus", "Laptop");
    catalog.put("Sony", "Laptop");
    catalog.put("Lenovo", "Laptop");

    catalog.put("Panasonic", "TV");
    catalog.put("Samsung", "TV");
    catalog.put("Sony", "TV");
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    catalog = new HashMap();

    populateHashMap();

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

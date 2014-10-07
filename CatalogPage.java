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
    
    title = request.getParameter("product");
    
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType = "<!DOCTYPE html>";
    out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align=\"center\">" + title + "</h1>");

    Set set = catalog.entrySet();
    Iterator i = set.iterator();
    
    out.println("<table border=\"1\"");
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         String comp = (String)me.getValue();
         if(title.compareTo(comp) == 0){
            out.println("<tr>\n<td>");
            out.println(me.getKey());
            out.println("</td>\n</tr>");
         }
      }
    out.println("</table>");

    out.println("</body>");
    out.println("</html>");
  }
}

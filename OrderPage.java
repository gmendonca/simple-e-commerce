import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

@SuppressWarnings("unchecked")

public class OrderPage extends HttpServlet {
  private String cancel;
  private String title;
  private String username;
  
  public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    PrintWriter out = response.getWriter();
    title = "Orders";

    HttpSession session = request.getSession(true);

    String orders = new String();
    if(session.getAttribute("orders") != null) orders = (String)session.getAttribute("orders");

    cancel = request.getParameter("cancel");

    if(cancel != null){
        String[] justCancel = cancel.split("\\s+");
        String delims = "[|]";
        String[] tokens = orders.split(delims);
        orders = new String();
        for(int i = 0; i < tokens.length; i++){
            if(Integer.parseInt(justCancel[1]) == (i + 1)){
                continue;
            }else{
                orders = new String(tokens[i] + "|" + orders); 
            }
        }

        session.setAttribute("orders", orders);
    }
    
    
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
        "<table align=\"center\">\n" +
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
    Date d =new Date();

    //Calendar calendar = Calendar.getInstance();
    //calendar.add(Calendar.DAY_OF_MONTH, 10);
    //d = calendar.getTime();

    SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
    out.println("<p align=\"center\"> Today's date: " + s.format(d) + "</p>");

    if(orders.compareTo("") == 0){
        out.println("You don't have any open order!");            
    }else{
        out.println("<form action=\"/ecom/OrderPage\">");
        out.println("<table border=\"1\">");
        String delims = "[|]";
        String[] tokens = orders.split(delims);
        for (int i = 0; i < tokens.length; i++){
            out.println("<tr>\n<td>");
            out.println("Order " + (i + 1));
            out.println("</td>\n<td>");
            out.println(tokens[i]);
            out.println("</td>\n<td>");
            out.println("Cancelation date until: ");
            out.println("</td>\n<td>");

            Date date = new Date();
            if(session.getAttribute(tokens[i]) != null) date = (Date)session.getAttribute(tokens[i]);
            out.println(s.format(date));

            out.println("</td>\n");
            
            if (d.compareTo(date) <= 0){
                out.println("<td>\n");
                out.println("<input id=\"buybutton\" type=\"submit\" name =\"cancel\" VALUE=\"Cancel " + (i + 1) + "\">\n");
                out.println("</td>\n");
            }
        }
        out.println("</tr>");
        out.println("</table>");
    }

    out.println("</aside>");
    out.println("</body>");
    out.println("</html>");
  }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class CartPage extends HttpServlet {
  private String item;
  private String remove;
  private String title;
  private String username;

  public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    PrintWriter out = response.getWriter();
    title = "Shopping Cart";

    HttpSession session = request.getSession(true);

    item = request.getParameter("item");

    String cartElements = new String();
    if(session.getAttribute("cart") != null) cartElements = (String)session.getAttribute("cart");

    if(item != null){
        String[] justItem = item.split("\\s+");
        if(justItem.length == 2) item = justItem[1];
        else item = justItem[1] + justItem[2];

        Integer countOrder = (Integer)session.getAttribute(item);

        if (countOrder == null) {
            countOrder = new Integer(1);
        } else {
          countOrder = new Integer(countOrder.intValue() + 1);
        }
        session.setAttribute(item, countOrder);

        if (cartElements == null) {
            cartElements = new String(item);
        } else {
            Boolean newItem = true;
            String delims = "[|]";
            String[] tokens = cartElements.split(delims);
            for(int i = 0; i < tokens.length; i++){
                if(item.compareTo(tokens[i]) == 0){
                    newItem = false;
                    break;
                }
            }
            if(newItem) cartElements = new String(item + "|" + cartElements);
        }
        session.setAttribute("cart", cartElements);
    }

    remove = request.getParameter("remove");

    if(remove != null){
        String[] justRemove = remove.split("\\s+");
        if(justRemove.length == 2) remove = justRemove[1];
        else remove = justRemove[1] + justRemove[2];

        String delims = "[|]";
        String[] tokens = cartElements.split(delims);
        cartElements = new String();
        for(int i = 0; i < tokens.length; i++){
            if(remove.compareTo(tokens[i]) == 0){
                session.setAttribute(tokens[i], 0);
            }else{
                cartElements = new String(tokens[i] + "|" + cartElements);
            }
        }

        session.setAttribute("cart", cartElements);
    }


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

    if(cartElements.compareTo("") == 0){
        out.println("No items here yet! :(");
    }else{
        out.println("<form action=\"/ecom/CartPage\">");
        out.println("<table align=\"center\" border=\"1\">");
        String delims = "[|]";
        String[] tokens = cartElements.split(delims);
        for (int i = 0; i < tokens.length; i++){
            out.println("<tr>\n<td>");
            out.println("<img src=\"img/" + tokens[i] + ".jpg\" alt=\"" + tokens[i] + "\" width=\"50\" heigth=\"50\" >");
            out.println("</td>\n<td>");
            out.println((Integer)session.getAttribute(tokens[i]));
            out.println("</td>\n<td>");
            out.println("<input id=\"buybutton\" type=\"submit\" name =\"remove\" VALUE=\"Remove " + tokens[i] + "\">\n");
            out.println("</td>\n</tr>");
        }

        out.println("<tr>\n<td>");
        out.println("</form>\n");
        out.println("<form action=\"/ecom/CheckoutPage\">\n");
        out.println("<input id=\"buybutton\" type=\"submit\" name =\"checkout\" VALUE=\"Checkout\">\n");
        out.println("</form>\n");
        out.println("</td>\n</tr>");
        out.println("</table>");
    }

    out.println("</aside>");
    out.println("</body>");
    out.println("</html>");
  }
}

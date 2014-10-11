import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

@SuppressWarnings("unchecked")

public class ConfirmationPage extends HttpServlet {
    private String title;
    private String username;
  
  public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        "<aside>\n");

    String cartElements = (String)session.getAttribute("cart");

    if(cartElements.compareTo("") == 0){
        out.println("<form action=\"/ecom/HomePage\">\n");
    out.println("<p>Oh no, something is not right! There are no products in your Cart! Try to buy something first!\n");
    out.println("<br><input id=\"buybutton\" type=\"submit\" name =\"back\" VALUE=\"OK\">\n");
    out.println("</form>\n");
    }else{
        if(session.getAttribute("username") != null){
            username = (String)session.getAttribute("username");

            String orders = (String)session.getAttribute("orders");

            Date date = new Date();

            out.println("<h2>" + username + ", thank you for you purchase on "+  date + "!</h2>");
            out.println("<p>Items:</p>");
            String delims = "[|]";
            String[] tokens = cartElements.split(delims);

            for (int i = 0; i < tokens.length; i++){
                out.println("<p>" + tokens[i] + " - Quantity: " + (Integer)session.getAttribute(tokens[i]) + "</p>");
                session.setAttribute(tokens[i], 0);
            }

            String printedName = new String();
            if(request.getParameter("nameprinted") != null)
                printedName = (String)request.getParameter("nameprinted");
            String cardNumber = new String();
            if(request.getParameter("creditcard") != null)
                cardNumber = (String)request.getParameter("creditcard");

            out.println("<br>");
            out.println("<br>");
            out.println("<p>Bought with the card: " + printedName + " - **** **** **** " + (cardNumber.substring(cardNumber.length() - 4)) + "</p>");


            cartElements = new String();
            session.setAttribute("cart", cartElements);

            out.println("<br>");
            out.println("<br>");

            Double confNumber = (1000000 + (Math.random() * (9999999 - 1000000)));
            out.println("<h3>Confirmation Number: " + confNumber + "</h3>");

            orders = new String(confNumber + "|" + orders);
            session.setAttribute("orders", orders);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            date = calendar.getTime();

            SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
            out.println("<h3>Delivery Date: " + s.format(date) + "</h3>");
        }
        else{
            out.println("<form action=\"/ecom/SignInPage\">\n");
            out.println("<p>Sign in first!</p><br>");
            out.println("<input id=\"buybutton\" type=\"submit\" name =\"signin\" VALUE=\"Sign In\">\n");
            out.println("</form>\n");
        }
    }
    out.println("</aside>");
    out.println("</body>");
    out.println("</html>");
  }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import system.products.*;

@SuppressWarnings("unchecked")

public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
    private ProductData prodData = new ProductData();
    private HashMap products = prodData.getProducts();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator it = products.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                    Product product = (Product) products.get(id);

                    if ( // targetId matches product name
                         product.getProductName().toLowerCase().startsWith(targetId) ){

                        sb.append("<product>");
                        sb.append("<id>" + product.getId() + "</id>");
                        sb.append("<productName>" + product.getProductName() + "</productName>");
                        sb.append("</product>");
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {


            if ((targetId != null) && products.containsKey(targetId.trim())) {
                request.setAttribute("product", products.get(targetId));
                context.getRequestDispatcher("/CatalogPage").forward(request, response);
            }
        }
    }
}

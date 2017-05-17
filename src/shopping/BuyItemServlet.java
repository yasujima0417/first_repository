package shopping;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuyItemServlet")
public class BuyItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public BuyItemServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration<String> names = request.getParameterNames();

        String name;
        String item_id = "";
        String purchased_num;

        while (names.hasMoreElements()) {
            name = names.nextElement();

            if ("購入".equals(request.getParameter(name))) {
                item_id = name;
            }
        }

        purchased_num = request.getParameter(item_id + "list");
        Shopping shopping = new Shopping();
        System.out.println("item_id: " + item_id);
        ItemBean item_bean = shopping.getItem(item_id);
        System.out.println("item_bean: " + item_bean);
        request.setAttribute("item_bean", item_bean);
        request.setAttribute("purchased_num", purchased_num);
        RequestDispatcher rd = request.getRequestDispatcher("./jsp/confirm.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
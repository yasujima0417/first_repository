package shopping;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginUserBean;

@WebServlet(name = "ShoppingServlet", urlPatterns = {"/ShoppingServlet"})
public class ShoppingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        RequestDispatcher rd;

        if (session.getAttribute("login_state").equals("login")) {
            String para = request.getParameter("submit");

            if (para.equals("history")) {
                Shopping shopping = new Shopping();
                String user_id = ((LoginUserBean) session.getAttribute("login_user_bean")).getId();
                ArrayList<HistoryBean> history_beans = shopping.getHistory(user_id);
                request.setAttribute("history", history_beans);
                rd = request.getRequestDispatcher("./jsp/history.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Shopping shopping = new Shopping();
        ArrayList<ItemBean> item_list = shopping.getItem();
        System.out.println("item_list" + item_list);
        request.setAttribute("itemList", item_list);
        RequestDispatcher rd = request.getRequestDispatcher("./jsp/itemList.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
  }
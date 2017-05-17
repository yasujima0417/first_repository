package shopping;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginUserBean;

@WebServlet(urlPatterns = {"/ResultServlet"})
public class ResultServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  request.setCharacterEncoding("UTF-8");
  String user_id = ((LoginUserBean) request.getSession().getAttribute("login_user_bean")).getId();
  String item_id = request.getParameter("item_id");
  int purchased_num = Integer.parseInt(request.getParameter("item_quantity"));
  ShoppingDao dao = null;
  try {
      dao = new ShoppingDao();
      dao.updateItem(item_id, purchased_num);
      dao.updateHistory(user_id, item_id, purchased_num);
  } catch (SQLException sqle) {
      sqle.printStackTrace();
  } finally {
      if (dao != null) {
          dao.close();
      }
  }
  RequestDispatcher rd = request.getRequestDispatcher("./jsp/result.jsp");
  rd.forward(request, response);

  }
}
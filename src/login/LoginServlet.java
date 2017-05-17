package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String para = request.getParameter("submit");

        HttpSession session = request.getSession();

        if (para.equals("logout")) {
            session.removeAttribute("login_state");
            session.removeAttribute("login_user_bean");
            response.sendRedirect("./");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String btn = request.getParameter("submit");

        HttpSession session = request.getSession();
        RequestDispatcher rd;

        if (btn.equals("ログイン")) {
            String id = request.getParameter("id");
            String pass = request.getParameter("pass");
            LoginDB login = new LoginDB();
            LoginUserBean bean = login.getUserData(id, pass);

            if (bean != null) {
                session.setAttribute("login_user_bean", bean);
                session.setAttribute("login_state", "login");
                rd = request.getRequestDispatcher("./ShoppingServlet");
            } else {
                rd = request.getRequestDispatcher("./jsp/loginFailed.jsp");
            }

            rd.forward(request, response);

        } else if (btn.equals("ログアウト")) {
            session.removeAttribute("login_state");
            session.removeAttribute("login_user_bean");
            response.sendRedirect("./");
        }

    }
}
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="login_user_bean" scope="session" class="login.LoginUserBean" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ショッピングサイト ログイン画面</title>

    </head>

    <body>

            <h1>ショッピングサイトへようこそ！</h1>
            <p>ログインIDとパスワードを入力して下さい</p>

            <form action="./LoginServlet" method="post">
                <table>
                    <tbody>
                        <tr>
                            <%-- ログイン済みの場合はIDを表示 --%>
                            <th>ログインID</th>
                            <td><input type="text" name="id" value="<%=login_user_bean.getId()%>"/></td>
                        </tr>
                        <tr>
                            <th>パスワード</th>
                            <td><input type="password" name="pass"/></td>
                        </tr>
                    </tbody>
                </table>

                <div>
                    <input type="submit" name="submit"  value="ログイン"/>

                    <% if ("login".equals(session.getAttribute("login_state"))) { %>
                    <input type="submit" name="submit" value="ログアウト"/>
                    <% }%>
                </div>
            </form>

    </body>
</html>
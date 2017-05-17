<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.HistoryBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="login_user_bean" scope="session" class="login.LoginUserBean"/>

<!DOCTYPE html>
<html>
    <head>
        <title>ショッピングサイト 購入履歴</title>
    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <h1><jsp:getProperty name="login_user_bean" property="name" />さんの購入履歴</h1>
        <% ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>) request.getAttribute("history"); %>
        <table>
            <tbody>
                <tr>
                    <th>商品ID</th>
                    <th>商品名</th>
                    <th>購入数</th>
                </tr>

                <% for (HistoryBean bean : historyList) {%>
                <tr>
                    <%-- 商品ID --%>
                    <td><%= bean.getItemId()%></td>
                    <%-- 商品名 --%>
                    <td><%= bean.getItemName()%></td>
                    <%-- 数量（在庫） --%>
                    <td class="int"><%= bean.getQuantity()%></td>
                </tr>
                <% }%>
            </tbody>
        </table>

        <form action="./ShoppingServlet" method="post">
            <input type="submit" value="一覧に戻る">
        </form>
    </body>
</html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>ショッピングサイト 商品一覧画面</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

    <main>
        <h1>商品一覧</h1>
    <% ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList"); %>

    <form action="./BuyItemServlet">
        <table>
            <tbody>
                <tr>
                    <th>商品ID</th>
                    <th>商品名</th>
                    <th>価格</th>
                    <th>在庫数</th>
                    <th>数量</th>
                </tr>

                <%-- テーブルを作成 --%>
                <% for (ItemBean bean : itemList) {%>
                <tr>

                    <td><%= bean.getItemId()%></td>
                    <td><%= bean.getItemName()%></td>
                    <td><%= bean.getPrice()%></td>
                    <td><%= bean.getQuantity()%></td>


                    <% if (bean.getQuantity() != 0) {%>
                    <td>
                        <select name="<%= bean.getItemId()%>list">
                            <% for (int i = 0; i <= bean.getQuantity(); i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="購入" name="<%= bean.getItemId()%>">
                    </td>
                    <% } else { %>
                    <td>売り切れ！</td>
                    <% } %>
                </tr>
                <% }%>
            </tbody>
        </table>
        <a  href="./">戻る</a>
    </form>
</main>
</body>
</html>
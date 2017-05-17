package shopping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shopping {

    public ArrayList<ItemBean> getItem() {
        ArrayList<ItemBean> bean_list = new ArrayList<>();
        ShoppingDao dao = null;
        ResultSet rs;

        try {
            dao = new ShoppingDao();
            rs = dao.selectItem();

            while (rs.next()) {
                String item_id = rs.getString("item_id");
                String item_name = rs.getString("item_name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");

                ItemBean item_bean = new ItemBean(item_id, item_name, price, quantity);
                System.out.println("itembean: " + item_bean);
                bean_list.add(item_bean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
        return bean_list;
    }

    public ItemBean getItem(String item_id) {
        ItemBean item_bean = null;
        ShoppingDao dao = null;
        ResultSet rs;

        try {
            dao = new ShoppingDao();
            rs = dao.selectItem(item_id);

            while (rs.next()) {
                String item_name = rs.getString("item_name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                item_bean = new ItemBean(item_id, item_name, price, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
        return item_bean;
    }

    public ArrayList<HistoryBean> getHistory(String user_id) {
        ArrayList<HistoryBean> history_bean_list = new ArrayList<>();
        ShoppingDao dao;
        ResultSet rs;

        try {
            dao = new ShoppingDao();
            rs = dao.selectHistory(user_id);

            while (rs.next()) {
                String item_id = rs.getString("item_id");
                String item_name = rs.getString("item_name");
                int quantity = rs.getInt("quantity");
                HistoryBean history_bean = new HistoryBean(item_id, item_name, quantity);
                history_bean_list.add(history_bean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return history_bean_list;
    }
}
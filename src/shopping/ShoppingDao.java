package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingDao {
    private Connection con_ = null;
    private ResultSet rs_ = null;
    private PreparedStatement ps_ = null;

    public ShoppingDao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.con_ = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping_sample",
                    "root",
                    "aljep008");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public ResultSet selectItem() throws SQLException {
        this.ps_ = this.con_.prepareStatement(
                "select item.item_id, item.item_name, item.price, stock.quantity from item inner join stock on item.item_id = stock.item_id"
        );
        this.rs_ = this.ps_.executeQuery();
        return this.rs_;
    }

    public ResultSet selectItem(String item_id) throws SQLException {
        this.ps_ = this.con_.prepareStatement(
                "select item.item_name, item.price, stock.quantity from item inner join stock on item.item_id = stock.item_id where item.item_id = ?"
        );
        this.ps_.setString(1, item_id);
        this.rs_ = this.ps_.executeQuery();
        return this.rs_;
    }

    public ResultSet selectHistory(String user_id) throws SQLException {
        this.ps_ = this.con_.prepareStatement("select history.item_id, item.item_name, history.quantity from history inner join item on history.id = ? and history.item_id = item.item_id");
        this.ps_.setString(1, user_id);
        this.rs_ = this.ps_.executeQuery();
        return this.rs_;
    }

    public void updateItem(String item_id, int purchased_num) throws SQLException {
        this.ps_ = this.con_.prepareStatement("update stock set quantity = quantity - ? where item_id = ?");
        this.ps_.setInt(1, purchased_num);
        this.ps_.setString(2, item_id);
        this.ps_.executeUpdate();
    }

    public void updateHistory(String user_id, String item_id, int purchased_num) throws SQLException {
        this.ps_ = this.con_.prepareStatement("insert into history(id, item_id, quantity) values (?, ?, ?)");
        this.ps_.setString(1, user_id);
        this.ps_.setString(2, item_id);
        this.ps_.setInt(3, purchased_num);
        this.ps_.executeUpdate();
    }

    public void close() {
        try {
            if (this.con_ != null) {
                this.con_.close();
            }
            if (this.ps_ != null) {
                this.ps_.close();
            }
            if (this.rs_ != null) {
                this.rs_.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
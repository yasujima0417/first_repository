package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private Connection con_ = null;
    private ResultSet rs_ = null;
    private PreparedStatement ps_ = null;

    public LoginDao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con_ = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping_sample",
                    "root",
                    "aljep008");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public ResultSet selectUser(String id, String pass) throws SQLException {
        this.ps_ = this.con_.prepareStatement("select name, age from user where id = ? and pass = ?");
        this.ps_.setString(1, id);
        this.ps_.setString(2, pass);
        this.rs_ = this.ps_.executeQuery();
        return this.rs_;
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
package xin.liujiajun.servlet.db;

import java.sql.*;

/**
 * @author liujiajun
 * @date 2019-08-27 12:55
 **/
public class MyConnection {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306;databasename=test", "sa", "13456");
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select  * from  test");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

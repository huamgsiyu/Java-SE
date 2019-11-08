package com.syh.chaptertwelve;

import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class CachedRowSetTest {
    /**
     * 离线RowSet——CachedRowSet测试
     */
    @Test
    public void cachedRowSet () {
        CachedRowSet cachedRowSet = cachedRowSet("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                "root",
                "1234",
                "select * from tbl_employee where id = ?");
        try {
            //8 获取数据，并修改数据
            while (cachedRowSet.next()) {
                System.out.print("id = " + cachedRowSet.getInt(1));
                System.out.println(", last_name = " + cachedRowSet.getString(2));
                cachedRowSet.updateInt(1, 50);
                cachedRowSet.updateString(2, "哈哈");

                cachedRowSet.updateRow();
                System.out.println(", last_name = " + cachedRowSet.getString(2));

            }
            //9 重新获取连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                    "root",
                    "1234");
            connection.setAutoCommit(false);

            //10 把对RowSet的修改提交到数据库
            cachedRowSet.acceptChanges(connection);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public CachedRowSet cachedRowSet (String url,
                              String username,
                              String password,
                              String sql) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        CachedRowSet cachedRowSet = null;

        try {
            //1 获取连接
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            //2 获取Statement
            statement = connection.prepareStatement(sql);

            //3 插入SQL
            statement.setString(1, "50");

            //4 执行查询，得到ResultSet结果集
            resultSet = statement.executeQuery();

            //5 获取RowSetFactory
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();

            //6 获取CachedRowSet
            cachedRowSet = rowSetFactory.createCachedRowSet();

            //7 把RowSet结果集装载到CachedRowSet中
            cachedRowSet.populate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cachedRowSet;
    }
}

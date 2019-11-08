package com.syh.chaptertwelve;

import org.junit.Test;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

/**
 * CachedRowSet分页测试
 */
public class CachedRowSetPageTest {
    @Test
    public void cachedRowSetPage () {
        CachedRowSet cachedRowSet = cachedRowSetPage("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                "root",
                "1234",
                "select * from tbl_employee where id = ? or id = ?");
        try {
            while (cachedRowSet.next()) {
                int id = cachedRowSet.getInt(1);
                String last_name = cachedRowSet.getString(2);
                System.out.print("id = " + id);
                System.out.println(", last_name = " + last_name);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    public CachedRowSet cachedRowSetPage (String url,
                                      String username,
                                      String password,
                                      String sql) {
        CachedRowSet cachedRowSet = null;
        ResultSet rs = null;
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement(sql);
                ){
            statement.setString(1, "50");
            statement.setString(2, "49");
            rs = statement.executeQuery();

            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            cachedRowSet = rowSetFactory.createCachedRowSet();
//            1 第一种：从startRow开始装填
//            cachedRowSet.populate(rs, 1);
//            2 第二种：根据页数和每页显示条数，计算返回多少条数据
            int page = 1;
            int pagesize = 1;
            cachedRowSet.setPageSize(1);
            cachedRowSet.populate(rs, (page - 1) * pagesize + 1);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return cachedRowSet;
    }
}

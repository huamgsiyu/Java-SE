package com.syh;

import org.junit.Test;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class RowSetFactoryTest {
    /**
     *
     */
    @Test
    public void jdbcRowSetConnection () {
        jdbcRowSetConnection("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                "root",
                "1234",
                "select * from tbl_employee");
    }

    public void jdbcRowSetConnection (String url,
                                      String username,
                                      String password,
                                      String sql) {
        try {
            //1 通过RowSetProvider获取RowSetFactory
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();

            //2 通过rowSetFactory.createJdbcRowSet()获取JdbcRowSet
            JdbcRowSet jdbcRowSet = rowSetFactory.createJdbcRowSet();

            //3 设置数据库连接信息
            jdbcRowSet.setUrl(url);
            jdbcRowSet.setUsername(username);
            jdbcRowSet.setPassword(password);
            jdbcRowSet.setCommand(sql);

            //4 执行查询
            jdbcRowSet.execute();

            //5 查看结果集
            while (jdbcRowSet.next()) {
                int id = jdbcRowSet.getInt(1);
                System.out.print("id = " + id);
                String last_name = jdbcRowSet.getString(2);
                System.out.println(", last_name = " + last_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

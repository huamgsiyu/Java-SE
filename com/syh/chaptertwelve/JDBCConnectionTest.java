package com.syh.chaptertwelve;

import org.junit.Test;

import java.sql.*;

/**
 * JDBC连接数据库步骤
 */
public class JDBCConnectionTest {
    /**
     * 1、try...catch...finally中关闭资源
     */
    @Test
    public void connection () {
        connection("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                "root",
                "1234",
                "select * from tbl_employee");
    }

    /**
     * 2、自动关闭资源的try
     */
    @Test
    public void connectionAutoClose () {
        connectionAutoClose("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                "root",
                "1234",
                "select * from tbl_employee");
    }


    public static void connection (String url,
                            String user,
                            String pass,
                            String sql) {
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            //1 加载驱动,注意：目前有的JDBC驱动已经可以通过SPI自动注册驱动类，在JDBC驱动JAR包中的META-INF\services路
            // 径会只包含一个java.sql.Driver文件，该文件制定了JDBC驱动类。因此，如果使用这种最新的驱动JAR包，这一步可以省略
//            Class.forName("com.mysql.jdbc.Driver");
            //2 获取Connection
            connection = DriverManager.getConnection(url, user, pass);

            //3 获取Statement
            Statement statement = connection.createStatement();
            //4 通过Statement执行SQL
            resultSet = statement.executeQuery(sql);

            //5 获取结果集
            while (resultSet.next()) {
                System.out.println("resultSet = " + resultSet.getInt(1));
            }
        } catch (SQLException cnfe) {
            cnfe.printStackTrace();
        } finally {
            //6 关闭资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }


    public static void connectionAutoClose (String url,
                                            String user,
                                            String pass,
                                            String sql) {
        try (
                /**
                 * 1、加载资源（SPI自动注册驱动类，所以也不需要注册）
                 * 2、获取Connection连接
                 * 3、获取Statement
                 * 4、使用Statement执行SQL
                 * 5、使用结果集
                 * 6、关闭资源（因为是使用自动关闭资源try块，所以不需要手段关闭）
                 */
                Connection conn = DriverManager.getConnection(url, user, pass);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
        ) {
            while (rs.next()) {
                System.out.println("rs.getInt(1) = " + rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3、使用PrepareStatement，更快速，还能够防止SQL注入
     */
    @Test
    public void connectionPrepareStatement () {
        connectionPrepareStatement("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC",
                "root",
                "1234");
    }
    public void connectionPrepareStatement (String url,
                                            String user,
                                            String pass) {
        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = conn.prepareStatement("insert into tbl_employee (id, last_name) values(?,?)");
                ) {
            StringBuilder stringBuilder = new StringBuilder("SSM");
            int sum = 0;
            for (int i = 1; i < 100; i++) {
                preparedStatement.setString(1, String.valueOf(i));
                stringBuilder.append(i);
                preparedStatement.setString(2, stringBuilder.toString());
                sum += 1;
            }
            System.out.println("sum = " + sum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

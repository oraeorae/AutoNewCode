package com.example.autoNewCode.test;

import com.example.autoNewCode.pojo.DataBase;
import com.example.autoNewCode.pojo.Table;
import com.example.autoNewCode.utils.DataBaseUtils;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class dbTset {
    /**
     * 获取数据库基本信息
     *
     * @throws Exception
     */
    @Test
    public void testDB() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        //没有指定具体哪个数据库，现在获取的是整个连接
        String url = "jdbc:mysql://112.124.9.174:3306?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        //获取连接
        //注册驱动
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库基本信息
        System.out.println(metaData.getUserName());
        //是否支持事务
        System.out.println(metaData.supportsTransactions());
        //数据库类型（MYSQL）
        System.out.println(metaData.getDatabaseProductName());
        connection.close();
    }

    /**
     * 获取数据库列表
     *
     * @throws Exception
     */
    @Test
    public void testGetDBList() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        //没有指定具体哪个数据库，现在获取的是整个连接
        String url = "jdbc:mysql://112.124.9.174:3306?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        //获取连接
        //注册驱动
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库列表名称
        ResultSet resultSet = metaData.getCatalogs();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
        connection.close();
    }

    /**
     * 获取指定数据库中的表信息
     *
     * @throws Exception
     */
    @Test
    public void testGetTable() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        //没有指定具体哪个数据库，现在获取的是整个连接
        String url = "jdbc:mysql://112.124.9.174:3306?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        //获取连接
        //注册驱动
        Class.forName(driver);//注册驱动
        Connection connection = DriverManager.getConnection(url, username, password);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库中表信息（mysql可以这样写，oracle会有一点区别）
        //参数1：当前操作的数据库 参数2：mysql可为空，oracle填写用户名（要大写） 参数3：null是查询所有表 非空是查询目标表 参数4：类型 table是表，view是视图
        ResultSet resultSet = metaData.getTables("protect", null, null, new String[]{"TABLE"});
        while (resultSet.next()) {
            //会打印出该数据库下的所有表名
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
        resultSet.close();
        connection.close();
    }

    /**
     * 获取指定表中的字段信息
     *
     * @throws Exception
     */
    @Test
    public void testGetTableField() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://112.124.9.174:3306/protect?characterEncoding=utf8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        //获取连接
        //注册驱动
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet city = metaData.getColumns("protect", null, "asd", null);
        while (city.next()) {
            //会打印出指定表的所有字段名
            System.out.println(city.getString("COLUMN_NAME"));
        }
    }

    /**
     * 测试参数元数据
     * 通过preparedStatement获取
     * 目的：获取sql参数中的属性信息
     */
    @Test
    public void test4() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://112.124.9.174:3306/protect?characterEncoding=utf8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        //获取连接
        //注册驱动
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select * from user where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设第一个参数为int 1
        preparedStatement.setInt(1, 1);
        //获取参数元数据
        ParameterMetaData metaData = preparedStatement.getParameterMetaData();
        //得到参数的个数
        //打印 1 （只有一个id参数）
        int count = metaData.getParameterCount();
        System.out.println(count);
        preparedStatement.close();
        connection.close();
    }

    /**
     * 测试结果集元数据
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://112.124.9.174:3306/protect?characterEncoding=utf8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        //获取连接
        //注册驱动
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select * from asd where column_1=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);//设第一个参数为int 1
        //查询
        ResultSet resultSet = preparedStatement.executeQuery();
        //获取结果集元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        //获取查询字段个数
        int count = metaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            //获取列名
            String columnName = metaData.getColumnName(i);//第i个列
            //获取字段类型  sql类型 varchar
            int columnType = metaData.getColumnType(i);
            //获取java类型  String
            String columnClassName = metaData.getColumnClassName(i);
            System.out.println(columnName + "---" + columnType + "---" + columnClassName);
        }
        preparedStatement.close();
        connection.close();
    }

    /**
     * 测试工具类
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://112.124.9.174:3306/protect?characterEncoding=utf8&serverTimezone=UTC";
        String username = "wc";
        String password = "1140";
        String ip = "112.124.9.174";
        String db = "protect";
        DataBase dataBase = new DataBase("MYSQL",ip,"3306",db);
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<Table> tables = DataBaseUtils.getDbInfo(dataBase, "protect");
        for (Table table : tables) {
            //对每个table进行代码生成
            System.out.println(table.toString());
        }
    }
}

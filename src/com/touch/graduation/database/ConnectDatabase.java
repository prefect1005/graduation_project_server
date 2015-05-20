package com.touch.graduation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.touch.graduation.util.Util;

public class ConnectDatabase {

	public static Connection getConnection() {
		
		Connection conn = null;

		// 加载驱动程序
		try {
			Class.forName(Util.DRIVER);

			// 连续数据库
			conn = DriverManager.getConnection(Util.URL, Util.DATA_USER,
					Util.DATA_PASSWORD);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			/*
			 * // statement用来执行SQL语句 Statement statement =
			 * conn.createStatement();
			 * 
			 * // 要执行的SQL语句 String sql = "select * from book";
			 * 
			 * // 结果集 ResultSet rs = statement.executeQuery(sql);
			 * 
			 * 
			 * String name = null;
			 * 
			 * while (rs.next()) {
			 * 
			 * // 选择sname这列数据 name = rs.getString("book_name");
			 * 
			 * // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。 //
			 * 然后使用GB2312字符集解码指定的字节数组 name = new
			 * String(name.getBytes("ISO-8859-1"), "GB2312");
			 * 
			 * // 输出结果 System.out.println(rs.getString("book_name") + "\t" +
			 * name); }
			 * 
			 * rs.close();
			 */

		} catch (ClassNotFoundException e) {
			System.out
					.println("ClassNotFoundException, fail connecting to the Database!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out
					.println("SQLException, fail connecting to the Database!");
			e.printStackTrace();
		}
		
		
		return conn;

	}
	
	public static void main (String[] agrs) {
		getConnection();
	}

}

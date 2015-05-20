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

		// ������������
		try {
			Class.forName(Util.DRIVER);

			// �������ݿ�
			conn = DriverManager.getConnection(Util.URL, Util.DATA_USER,
					Util.DATA_PASSWORD);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			/*
			 * // statement����ִ��SQL��� Statement statement =
			 * conn.createStatement();
			 * 
			 * // Ҫִ�е�SQL��� String sql = "select * from book";
			 * 
			 * // ����� ResultSet rs = statement.executeQuery(sql);
			 * 
			 * 
			 * String name = null;
			 * 
			 * while (rs.next()) {
			 * 
			 * // ѡ��sname�������� name = rs.getString("book_name");
			 * 
			 * // ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С� //
			 * Ȼ��ʹ��GB2312�ַ�������ָ�����ֽ����� name = new
			 * String(name.getBytes("ISO-8859-1"), "GB2312");
			 * 
			 * // ������ System.out.println(rs.getString("book_name") + "\t" +
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

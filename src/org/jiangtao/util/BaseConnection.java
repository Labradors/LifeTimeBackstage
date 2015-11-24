package org.jiangtao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 对数据库的连接和关闭
 * 
 * @author mr-jiang
 * 
 */
public class BaseConnection {
	/**
	 * 连接数据库
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/LifeTime", "root", "1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
/**
 * 关闭两种资源
 * @param conn
 * @param ps
 */
	public static void closeRes(Connection conn, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	/**
	 * 关闭三种资源
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeRes(Connection conn, PreparedStatement ps,ResultSet rs) {
		try {
			if (rs!=null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	

}

package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBUtil {
	//SQL�������
			public static PreparedStatement prepare(Connection conn, String sql) {
				PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(sql);
				} catch (SQLException e) {
					System.out.println("ERR IN SQL QUERY!!");
					e.printStackTrace();
				}
				return ps;
			}
			//���أ��ر����ݿ�����
			public static void close(Connection conn) {
				
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//���أ��ر�SQL��������
			public static void close(Statement stmt) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//���أ��رս����������
			public static void close(ResultSet rs) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			public static Connection CreateConn() {
				// TODO Auto-generated method stub
				 String url="jdbc:mysql://localhost:3306/hnuzyj?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
				 String driverClass="com.mysql.cj.jdbc.Driver";
				 String username="root";
				 String password="123456";

				Connection conn = null;
				try {
					Class.forName(driverClass);
					conn = DriverManager.getConnection(url, username, password);
				} catch (ClassNotFoundException e) {
					System.out.println("ERR IN DB CONNECT!");
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return conn;
			}
}

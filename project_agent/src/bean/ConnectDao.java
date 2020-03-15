package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDao {
	public Connection getConnection() {
		// 数据库连接
		Connection conn = null;
		try {
			// 加载数据库驱动，注册到驱动管理器
			Class.forName("com.mysql.jdbc.Driver");
			// 数据库连接字符串
			String url = "jdbc:mysql://localhost:3306/hnuzyj?characterEncoding=utf-8&useSSL=false";
			// 数据库用户名
			String username = "root";
			// 数据库密码
			String password = "123456";
			// 创建Connection连接
			conn = DriverManager.getConnection(url, username, password);
			//System.out.println("1111");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回数据库连接
		return conn;
	}
}

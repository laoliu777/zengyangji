package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps;
	
	//打印出该代理商的信息
		public User findUser(String uid) {
			User p = new User();
			// 分页查询的SQL语句
			String sql = "SELECT * FROM agent where  uid=? ;";
			try {
				// 获取PreparedStatement
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的第1个参数赋值
				ps.setString(1, uid);
				// 执行查询操作
				ResultSet rs = ps.executeQuery();
				// 光标向后移动，并判断是否有效
				while (rs.next()) {
					// 对id属性赋值
					p.setAccount(rs.getString("uid"));
					p.setTel(rs.getString("utel"));
				}
				// 关闭ResultSet
				rs.close();
				// 关闭PreparedStatement
				ps.close();
				// 关闭Connection
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return p;
		}
}


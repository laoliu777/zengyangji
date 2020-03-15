package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps,ps1;
	
	//打印出该管理员的信息
		public User findUser(String uid) {
			User p = new User();
			// 分页查询的SQL语句
			String sql = "SELECT * FROM admin where aid=?;";
			try {
				// 获取PreparedStatement
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的第1个参数赋值
				ps.setString(1, uid);
				// 执行查询操作
				ResultSet rs = ps.executeQuery();
				// 光标向后移动，并判断是否有效
				while (rs.next()) {
					// 实例化Product
					// 对id属性赋值
					p.setAccount(rs.getString("aid"));
					p.setTel(rs.getString("atel"));
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
		
		//打印所有人员的信息
		public List<User> findAllUser() {
			// 创建List
			List<User> list = new ArrayList<User>();			
			String sql0 = "SELECT * FROM admin;";
			String sql1 = "SELECT * FROM agent;";
			String sql2 = "SELECT * FROM member;";		
			try {
				ps = conn.prepareStatement(sql0);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {					
					User p = new User();
					p.setAccount(rs.getString("aid"));
					p.setRole(0);
					p.setTel(rs.getString("atel"));
					list.add(p);
				}
				
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				while (rs.next()) {					
					User p = new User();
					p.setAccount(rs.getString("uid"));
					p.setRole(1);
					p.setTel(rs.getString("utel"));
					list.add(p);
				}
				
				ps = conn.prepareStatement(sql2);
				rs = ps.executeQuery();
				while (rs.next()) {
					User p = new User();
					p.setAccount(rs.getString("mid"));
					p.setRole(2);
					p.setTel(rs.getString("mtel"));
					list.add(p);
				}
				// 关闭ResultSet
				rs.close();
				// 关闭PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public User findUserDtail(String account,int role) {
			// 创建List
			User p = new User();		
			switch (role) {
			case 0:
				String sql0 = "SELECT * FROM admin where aid=?;";
				try {
					// 获取PreparedStatement
					ps = conn.prepareStatement(sql0);
					ps.setString(1, account);
					// 执行查询操作
					ResultSet rs = ps.executeQuery();
					// 光标向后移动，并判断是否有效
					while (rs.next()) {					
						// 对id属性赋值
						p.setAccount(rs.getString("aid"));
						p.setRole(0);
						p.setTel(rs.getString("atel"));
					}				
					// 关闭ResultSet
					rs.close();
					// 关闭PreparedStatement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 1:
				String sql1 = "SELECT * FROM agent where uid=?;";
				try {
					// 获取PreparedStatement
					ps = conn.prepareStatement(sql1);
					ps.setString(1, account);
					// 执行查询操作
					ResultSet rs = ps.executeQuery();
					// 光标向后移动，并判断是否有效
					while (rs.next()) {					
						// 对id属性赋值
						p.setAccount(rs.getString("uid"));
						p.setRole(1);
						p.setTel(rs.getString("utel"));
					}				
					// 关闭ResultSet
					rs.close();
					// 关闭PreparedStatement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				String sql2 = "SELECT * FROM member where mid=?;";		
				try {
					// 获取PreparedStatement
					ps = conn.prepareStatement(sql2);
					ps.setString(1, account);
					// 执行查询操作
					ResultSet rs = ps.executeQuery();
					// 光标向后移动，并判断是否有效
					while (rs.next()) {					
						// 对id属性赋值
						p.setAccount(rs.getString("mid"));
						p.setRole(2);
						p.setTel(rs.getString("tel"));
					}				
					// 关闭ResultSet
					rs.close();
					// 关闭PreparedStatement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
								
			return p;
		}
		
		public boolean user_edit(String account, int role,String tel) {	
			switch (role) {
			case 0:
				try {
		            ps = conn.prepareStatement("update admin set atel=?"
		            		+ " where aid=? ;");
		            ps.setString(1, tel);
		            ps.setString(2, account);		    
		            ps.executeUpdate();
		            
		            return true;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }	
				break;
			case 1:
				try {
		            ps = conn.prepareStatement("update agent set utel=?"
		            		+ " where uid=? ;");
		            ps.setString(1, tel);
		            ps.setString(2, account);		    
		            ps.executeUpdate();
		            
		            return true;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }	
				break;
			case 2:
				try {
		            ps = conn.prepareStatement("update member set mtel=?"
		            		+ " where mid=? ;");
		            ps.setString(1, tel);
		            ps.setString(2, account);		    
		            ps.executeUpdate();
		            
		            return true;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }	
				break;
			default:
				break;
			}
			
			return false;
		}
}

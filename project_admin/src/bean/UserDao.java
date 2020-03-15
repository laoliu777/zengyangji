package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	Connection conn=new ConnectDao().getConnection();                                     //���ݿ����Ӷ���
	PreparedStatement ps,ps1;
	
	//��ӡ���ù���Ա����Ϣ
		public User findUser(String uid) {
			User p = new User();
			// ��ҳ��ѯ��SQL���
			String sql = "SELECT * FROM admin where aid=?;";
			try {
				// ��ȡPreparedStatement
				ps = conn.prepareStatement(sql);
				// ��SQL����еĵ�1��������ֵ
				ps.setString(1, uid);
				// ִ�в�ѯ����
				ResultSet rs = ps.executeQuery();
				// �������ƶ������ж��Ƿ���Ч
				while (rs.next()) {
					// ʵ����Product
					// ��id���Ը�ֵ
					p.setAccount(rs.getString("aid"));
					p.setTel(rs.getString("atel"));
				}
				// �ر�ResultSet
				rs.close();
				// �ر�PreparedStatement
				ps.close();
				// �ر�Connection
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return p;
		}
		
		//��ӡ������Ա����Ϣ
		public List<User> findAllUser() {
			// ����List
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
				// �ر�ResultSet
				rs.close();
				// �ر�PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public User findUserDtail(String account,int role) {
			// ����List
			User p = new User();		
			switch (role) {
			case 0:
				String sql0 = "SELECT * FROM admin where aid=?;";
				try {
					// ��ȡPreparedStatement
					ps = conn.prepareStatement(sql0);
					ps.setString(1, account);
					// ִ�в�ѯ����
					ResultSet rs = ps.executeQuery();
					// �������ƶ������ж��Ƿ���Ч
					while (rs.next()) {					
						// ��id���Ը�ֵ
						p.setAccount(rs.getString("aid"));
						p.setRole(0);
						p.setTel(rs.getString("atel"));
					}				
					// �ر�ResultSet
					rs.close();
					// �ر�PreparedStatement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 1:
				String sql1 = "SELECT * FROM agent where uid=?;";
				try {
					// ��ȡPreparedStatement
					ps = conn.prepareStatement(sql1);
					ps.setString(1, account);
					// ִ�в�ѯ����
					ResultSet rs = ps.executeQuery();
					// �������ƶ������ж��Ƿ���Ч
					while (rs.next()) {					
						// ��id���Ը�ֵ
						p.setAccount(rs.getString("uid"));
						p.setRole(1);
						p.setTel(rs.getString("utel"));
					}				
					// �ر�ResultSet
					rs.close();
					// �ر�PreparedStatement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				String sql2 = "SELECT * FROM member where mid=?;";		
				try {
					// ��ȡPreparedStatement
					ps = conn.prepareStatement(sql2);
					ps.setString(1, account);
					// ִ�в�ѯ����
					ResultSet rs = ps.executeQuery();
					// �������ƶ������ж��Ƿ���Ч
					while (rs.next()) {					
						// ��id���Ը�ֵ
						p.setAccount(rs.getString("mid"));
						p.setRole(2);
						p.setTel(rs.getString("tel"));
					}				
					// �ر�ResultSet
					rs.close();
					// �ر�PreparedStatement
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

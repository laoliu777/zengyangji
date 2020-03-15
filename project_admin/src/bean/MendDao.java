package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MendDao {
	Connection conn=new ConnectDao().getConnection();                            
	PreparedStatement ps,ps1;
	
			public List<Mend> findAllMend() {
				// 创建List
				List<Mend> list = new ArrayList<Mend>();			
				String sql = "SELECT * FROM mend order by mendid desc ;";
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {					
						Mend p = new Mend();
						p.setMendid(rs.getInt("mendid"));
						p.setVid(rs.getString("vid"));
						p.setProblem(rs.getString("problem"));
						p.setMstate(rs.getInt("mstate"));
						p.setMtime(rs.getTimestamp("mtime"));
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
			
			public Mend findMendDtail(int mendid) {
				// 创建List
				Mend p = new Mend();		
					String sql0 = "SELECT * FROM mend where mendid=?;";
					try {
						// 获取PreparedStatement
						ps = conn.prepareStatement(sql0);
						ps.setInt(1, mendid);
						// 执行查询操作
						ResultSet rs = ps.executeQuery();
						// 光标向后移动，并判断是否有效
						while (rs.next()) {					
							// 对id属性赋值
							p.setMendid(rs.getInt("mendid"));
							p.setVid(rs.getString("vid"));
							p.setProblem(rs.getString("problem"));
							p.setMstate(rs.getInt("mstate"));
							p.setMtime(rs.getTimestamp("mtime"));

						}				
						// 关闭ResultSet
						rs.close();
						// 关闭PreparedStatement
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		
				return p;
			}
			
			public boolean mend_edit(int mendid,int mstate) {	
					try {
			            ps = conn.prepareStatement("update mend set mstate=? "
			            		+ " where mendid=? ;");
			            ps.setInt(1, mstate);
			            ps.setInt(2, mendid);
			            ps.executeUpdate();
			            
			            return true;
			        } catch (Exception e) {
			            e.printStackTrace();
			        }	
					return false;
			}
}


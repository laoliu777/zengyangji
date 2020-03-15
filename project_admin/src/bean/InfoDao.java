package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps,ps1;
	
			public List<Info> findAllInfo() {
				// 创建List
				List<Info> list = new ArrayList<Info>();			
				String sql = "SELECT * FROM iteminfo;";
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {					
						Info p = new Info();
						p.setType(rs.getString("type"));
						p.setZujing(rs.getFloat("zujing"));
						p.setYajing(rs.getInt("yajing"));
						p.setShoujia(rs.getInt("shoujia"));
						p.setBrand(rs.getString("brand"));
						p.setSpec(rs.getString("spec"));

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
			
			public Info findInfoDtail(String type) {
				// 创建List
				Info p = new Info();		
					String sql0 = "SELECT * FROM iteminfo where type=?;";
					try {
						// 获取PreparedStatement
						ps = conn.prepareStatement(sql0);
						ps.setString(1, type);
						// 执行查询操作
						ResultSet rs = ps.executeQuery();
						// 光标向后移动，并判断是否有效
						while (rs.next()) {					
							// 对id属性赋值
							p.setType(rs.getString("type"));
							p.setZujing(rs.getFloat("zujing"));
							p.setYajing(rs.getInt("yajing"));
							p.setShoujia(rs.getInt("shoujia"));
							p.setBrand(rs.getString("brand"));
							p.setSpec(rs.getString("spec"));

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
			
			public boolean info_edit(String type,float zujing,int yajing,int shoujia,String brand,String spec) {	
					try {
			            ps = conn.prepareStatement("update iteminfo set zujing=?,"
			            		+ "yajing=?,"
			            		+ "shoujia=?,"
			            		+ "brand=?,"
			            		+ "spec=?"
			            		+ " where type=? ;");
			            ps.setFloat(1, zujing);
			            ps.setInt(2, yajing);
			            ps.setInt(3, shoujia);
			            ps.setString(4, brand);
			            ps.setString(5, spec);		    
			            ps.setString(6, type);		    

			            ps.executeUpdate();
			            
			            return true;
			        } catch (Exception e) {
			            e.printStackTrace();
			        }	
					return false;
			}
}


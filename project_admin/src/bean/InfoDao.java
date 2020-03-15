package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDao {
	Connection conn=new ConnectDao().getConnection();                                     //���ݿ����Ӷ���
	PreparedStatement ps,ps1;
	
			public List<Info> findAllInfo() {
				// ����List
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
					// �ر�ResultSet
					rs.close();
					// �ر�PreparedStatement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}	
			
			public Info findInfoDtail(String type) {
				// ����List
				Info p = new Info();		
					String sql0 = "SELECT * FROM iteminfo where type=?;";
					try {
						// ��ȡPreparedStatement
						ps = conn.prepareStatement(sql0);
						ps.setString(1, type);
						// ִ�в�ѯ����
						ResultSet rs = ps.executeQuery();
						// �������ƶ������ж��Ƿ���Ч
						while (rs.next()) {					
							// ��id���Ը�ֵ
							p.setType(rs.getString("type"));
							p.setZujing(rs.getFloat("zujing"));
							p.setYajing(rs.getInt("yajing"));
							p.setShoujia(rs.getInt("shoujia"));
							p.setBrand(rs.getString("brand"));
							p.setSpec(rs.getString("spec"));

						}				
						// �ر�ResultSet
						rs.close();
						// �ر�PreparedStatement
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


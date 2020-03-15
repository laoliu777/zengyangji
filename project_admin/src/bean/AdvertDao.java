package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps,ps1;
	
			public List<Advert> findAllAdvert() {
				// 创建List
				List<Advert> list = new ArrayList<Advert>();			
				String sql = "SELECT * FROM advert order by advid desc ;";
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {					
						Advert p = new Advert();
						p.setAdvid(rs.getInt("advid"));
						p.setAdtitle(rs.getString("adtitle"));
						p.setAdcontent(rs.getString("adcontent"));
						p.setAdpic(rs.getString("adpic"));
						p.setAdtime(rs.getDate("adtime"));

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
			
			public Advert findAdvertDtail(int advid) {
				// 创建List
				Advert p = new Advert();		
					String sql0 = "SELECT * FROM advert where advid=?;";
					try {
						// 获取PreparedStatement
						ps = conn.prepareStatement(sql0);
						ps.setInt(1, advid);
						// 执行查询操作
						ResultSet rs = ps.executeQuery();
						// 光标向后移动，并判断是否有效
						while (rs.next()) {					
							// 对id属性赋值
							p.setAdvid(rs.getInt("advid"));
							p.setAdtitle(rs.getString("adtitle"));
							p.setAdcontent(rs.getString("adcontent"));
							p.setAdpic(rs.getString("adpic"));
							p.setAdtime(rs.getDate("adtime"));
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
			
			public boolean advert_edit(int advid,String adtitle,String adcontent) {	
					try {
			            ps = conn.prepareStatement("update advert set adtitle=?,"
			            		+ "adcontent=? "
			            		+ " where advid=? ;");
			            ps.setString(1, adtitle);
			            ps.setString(2, adcontent);		    
			            ps.setInt(3, advid);

			            ps.executeUpdate();
			            
			            return true;
			        } catch (Exception e) {
			            e.printStackTrace();
			        }	
					return false;
			}
			
			public int get_result() throws SQLException {
				int result=0;
				String sql="select max(advid) from advert ";
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					result=rs.getInt(1);
				}
				//rs.close();
				//ps.close();
				return result;
			}
			
			public boolean advert_add(String adtitle,String adcontent)  {
				try{
					String sql2="insert into advert(adtitle,adcontent,adtime) "
							+ "values(?,?,curdate())";
					PreparedStatement ps1=conn.prepareStatement(sql2);
					ps1.setString(1, adtitle);
					ps1.setString(2, adcontent);
					//ps1.setString(3, filePath);
					ps1.executeUpdate();            
					//ps.close();
									       
					return true;
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
			public boolean advert_add1(int advid,String filePath)  {
				try{
					String sql2="update advert set adpic=?  "
							+ "where advid=? ;";
					PreparedStatement ps1=conn.prepareStatement(sql2);
					ps1.setString(1, filePath);
					ps1.setInt(2, advid);
					//ps1.setString(3, filePath);
					ps1.executeUpdate();            
					//ps.close();
									       
					return true;
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
			public boolean advert_add2(String adtitle,String adcontent,String filePath)  {
				try{
					String sql2="insert into advert(adtitle,adcontent,adpic,adtime) "
							+ "values(?,?,?,curdate())";
					PreparedStatement ps1=conn.prepareStatement(sql2);
					ps1.setString(1, adtitle);
					ps1.setString(2, adcontent);
					ps1.setString(3, filePath);
					ps1.executeUpdate();            
					//ps.close();
									       
					return true;
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
}


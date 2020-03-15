package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps,ps1;
	
	//打印出该代理商所有的设备（未分页）
	public List<Item> findItem(String agent) {
		// 创建List
		List<Item> list = new ArrayList<Item>();
		// 获取数据库连接
		
		// 分页查询的SQL语句
		String sql = "SELECT * FROM iteminfo,iorder where uid=? and iorder.type=iteminfo.type "
				+ "order by oid desc ; ";
		try {
			// 获取PreparedStatement
			ps = conn.prepareStatement(sql);
			// 对SQL语句中的第1个参数赋值
			ps.setString(1, agent);
			// 执行查询操作
			ResultSet rs = ps.executeQuery();
			// 光标向后移动，并判断是否有效
			while (rs.next()) {
				// 实例化Product
				Item p = new Item();
				// 对id属性赋值
				p.setType(rs.getString("iorder.type"));
				p.setMember(rs.getString("mid"));
				p.setOrdertime(rs.getTimestamp("otime"));
				p.setOrderstate(rs.getInt("ostate"));
				p.setOid(rs.getInt("oid"));
				if(rs.getInt("renttime")>0) {
					p.setRenttime(rs.getInt("renttime"));
					p.setZujing(rs.getFloat("zujing"));
					p.setYajing(rs.getInt("yajing"));
					float zujing=p.getZujing();
					int yajing=p.getYajing();
					int renttime=p.getRenttime();
					int zongjia=(int)(zujing*renttime+yajing);
					p.setZongjia(zongjia);
					p.setBuystate(0);
				}else {
					p.setZongjia(rs.getInt("shoujia"));
					p.setBuystate(1);
				}

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

	public Item findItemDetail(int oid) {
		// 创建List
		Item p = new Item();	
		int oostate=0;
		
		try {
			String sql0 = "SELECT ostate FROM iorder where oid=?; ";
			ps = conn.prepareStatement(sql0);
			// 对SQL语句中的第1个参数赋值
			ps.setInt(1, oid);
			// 执行查询操作
			ResultSet rs0;
			rs0 = ps.executeQuery();
			// 光标向后移动，并判断是否有效
			while (rs0.next()) {		
				oostate=rs0.getInt("ostate");
			}
			rs0.close();			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		if(oostate<2||oostate==3) {
			String sql = "SELECT * FROM iorder,iteminfo "
					+ "where oid=? and iteminfo.type=iorder.type ; ";
			try {
				// 获取PreparedStatement
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的第1个参数赋值
				ps.setInt(1, oid);
				// 执行查询操作
				ResultSet rs = ps.executeQuery();
				// 光标向后移动，并判断是否有效
				while (rs.next()) {
					// 对id属性赋值
					p.setType(rs.getString("iorder.type"));
					p.setMember(rs.getString("mid"));
					p.setOrdertime(rs.getTimestamp("otime"));
					p.setOrderstate(rs.getInt("ostate"));
					p.setKdcompany(rs.getString("kdcompany"));
					p.setKdno(rs.getString("kdno"));
					p.setBrand(rs.getString("brand"));
					p.setOid(rs.getInt("oid"));
					
					if(rs.getInt("renttime")>0) {
						p.setRenttime(rs.getInt("renttime"));
						p.setZujing(rs.getFloat("zujing"));
						p.setYajing(rs.getInt("yajing"));
						float zujing=p.getZujing();
						int yajing=p.getYajing();
						int renttime=p.getRenttime();
						int zongjia=(int)(zujing*renttime+yajing);
						p.setZongjia(zongjia);
						p.setBuystate(0);
					}else {
						p.setZongjia(rs.getInt("shoujia"));
						p.setBuystate(1);
					}

				}
				// 关闭ResultSet
				rs.close();
				// 关闭PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			String sql = "SELECT * FROM iorder,item,iteminfo "
					+ "where oid=? and iteminfo.type=iorder.type and item.vid=iorder.vid; ";
			try {
				// 获取PreparedStatement
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的第1个参数赋值
				ps.setInt(1, oid);
				// 执行查询操作
				ResultSet rs = ps.executeQuery();
				// 光标向后移动，并判断是否有效
				while (rs.next()) {
					// 对id属性赋值
					p.setType(rs.getString("iorder.type"));
					p.setMember(rs.getString("mid"));
					p.setOrdertime(rs.getTimestamp("otime"));
					p.setOrderstate(rs.getInt("ostate"));
					p.setKdcompany(rs.getString("kdcompany"));
					p.setKdno(rs.getString("kdno"));
					p.setBrand(rs.getString("brand"));
					p.setSswitch(rs.getInt("sswitch"));
					p.setIno(rs.getString("iorder.vid"));
					p.setOid(rs.getInt("oid"));

					if(rs.getInt("renttime")>0) {
						p.setRenttime(rs.getInt("renttime"));
						p.setZujing(rs.getFloat("zujing"));
						p.setYajing(rs.getInt("yajing"));
						float zujing=p.getZujing();
						int yajing=p.getYajing();
						int renttime=p.getRenttime();
						int zongjia=(int)(zujing*renttime+yajing);
						p.setZongjia(zongjia);
						p.setBuystate(0);
						p.setStarttime(rs.getDate("starttime"));
						p.setEndtime(rs.getDate("endtime"));

					}else {
						p.setZongjia(rs.getInt("shoujia"));
						p.setBuystate(1);
					}
				}
				// 关闭ResultSet
				rs.close();
				// 关闭PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;
	}
	
	//,int renttime,int sswitch,String starttime,String endtime
	public boolean item_edit(int oid,int ostate,String kdcompany,String kdno) {	
		// 查询总记录数SQL语句
		try {
            ps = conn.prepareStatement("update iorder,item set ostate=?,"
            		+ "kdcompany=?,"
            		+ "kdno=? "
            		//+ "renttime=?,"
            		//+ "starttime=?,"
            		//+ "endtime=?,"
            		//+ "sswitch=? "
            		+ " where oid =? and iorder.vid=item.vid ;");
            ps.setInt(1, ostate);
            ps.setString(2, kdcompany);
            ps.setString(3, kdno);
            //ps.setInt(4, renttime);
            //ps.setString(5, starttime);
            //ps.setString(6, endtime);
            //ps.setInt(7, sswitch);
            ps.setInt(4, oid);
            ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean item_kd_add(int oid,String kdcompany,String kdno) {	
		// 查询总记录数SQL语句
		try {
            ps = conn.prepareStatement("update iorder set ostate=1,"
            		+ "kdcompany=?,"
            		+ "kdno=?"            		
            		+ " where oid =?  ;");
            ps.setString(1, kdcompany);
            ps.setString(2, kdno);
            ps.setInt(3, oid);
            ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
}


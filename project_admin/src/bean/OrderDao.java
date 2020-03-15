package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps,ps1;
	
	//打印出所有（未分页）
	public List<Order> findOrder() {
		// 创建List
		List<Order> list = new ArrayList<Order>();
		// 获取数据库连接
		
		// 分页查询的SQL语句
		String sql = "SELECT * FROM iteminfo,iorder where iorder.type=iteminfo.type "
				+ "order by iorder.oid desc ; ";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 实例化Product
				Order p = new Order();
				// 对id属性赋值
				p.setType(rs.getString("iorder.type"));
				p.setMember(rs.getString("mid"));
				p.setAgent(rs.getString("uid"));
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

	public List<Order> findOrderAdd() {
		// 创建List
		List<Order> list = new ArrayList<Order>();
		// 获取数据库连接
		
		// 分页查询的SQL语句
		String sql = "SELECT * FROM iteminfo,iorder "
				+ "where iorder.type=iteminfo.type and ostate=1 "
				+ "order by oid desc ; ";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 实例化Product
				Order p = new Order();
				// 对id属性赋值
				p.setType(rs.getString("iorder.type"));
				p.setMember(rs.getString("mid"));
				p.setAgent(rs.getString("uid"));
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
	
	public Order findOrderDetail(int oid) {
		// 创建List
		Order p = new Order();	
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
			
		if(oostate<2) {
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
					p.setAgent(rs.getString("uid"));
					p.setOrdertime(rs.getTimestamp("otime"));
					p.setOrderstate(rs.getInt("ostate"));
					p.setKdcompany(rs.getString("kdcompany"));
					p.setKdno(rs.getString("kdno"));
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
					p.setAgent(rs.getString("uid"));
					p.setOrdertime(rs.getTimestamp("otime"));
					p.setOrderstate(rs.getInt("ostate"));
					p.setKdcompany(rs.getString("kdcompany"));
					p.setKdno(rs.getString("kdno"));
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
	
	public boolean order_edit(int oid,int ostate,String kdcompany,String kdno) {	
		// 查询总记录数SQL语句
		try {
            ps = conn.prepareStatement("update iorder set ostate=?,"
            		+ "kdcompany=?,"
            		+ "kdno=?"            	
            		+ " where oid =? ;");
            ps.setInt(1, ostate);
            ps.setString(2, kdcompany);
            ps.setString(3, kdno);
            ps.setInt(4, oid);
            ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
}


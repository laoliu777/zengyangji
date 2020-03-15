package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement ps;
	
	//打印出该代理商所有的设备（未分页，未指定代理商）
	public List<Item> findItem() {
		// 创建List
		List<Item> list = new ArrayList<Item>();
		// 获取数据库连接
		
		// 分页查询的SQL语句
		String sql = "SELECT * FROM item,iorder "
				+ "where item.vid=iorder.vid and ostate>1 "
				+ "order by iorder.oid desc ;";
		try {
			// 获取PreparedStatement
			ps = conn.prepareStatement(sql);
			// 执行查询操作
			ResultSet rs = ps.executeQuery();
			// 光标向后移动，并判断是否有效
			while (rs.next()) {
				// 实例化Product
				Item p = new Item();
				// 对id属性赋值
				p.setType(rs.getString("type"));
				p.setIno(rs.getString("item.vid"));
				p.setMember(rs.getString("mid"));
				p.setAgent(rs.getString("uid"));
				p.setSswitch(rs.getInt("sswitch"));
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
	
	public Item findItemDtail(String ino) {
		// 创建List
		Item p = new Item();		
		// 分页查询的SQL语句
		String sql = "SELECT * FROM item,iorder "
				+ "where item.vid=? and item.vid=iorder.vid; ";
		try {
			// 获取PreparedStatement
			ps = conn.prepareStatement(sql);
			// 对SQL语句中的第1个参数赋值
			ps.setString(1, ino);
			// 执行查询操作
			ResultSet rs = ps.executeQuery();
			// 光标向后移动，并判断是否有效
			while (rs.next()) {
				// 对id属性赋值
				p.setType(rs.getString("type"));
				p.setIno(rs.getString("item.vid"));
				p.setMember(rs.getString("mid"));
				p.setAgent(rs.getString("uid"));
				p.setSswitch(rs.getInt("sswitch"));
				
				if(rs.getInt("renttime")>0) {
					p.setStarttime(rs.getDate("starttime"));
					p.setEndtime(rs.getDate("endtime"));
					p.setBuystate(0);
				}else {
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
		return p;
	}
	
	public boolean item_edit(String ino,String starttime,String endtime,int sswitch) {	
		// 查询总记录数SQL语句
		try {
            ps = conn.prepareStatement("update item set starttime=?,"
            		+ "endtime=?,"
            		+ "sswitch=?"
            		+ " where vid=? ;");
            ps.setString(1, starttime);
            ps.setString(2, endtime);
            ps.setInt(3, sswitch);
            ps.setString(4, ino);
            ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	
}


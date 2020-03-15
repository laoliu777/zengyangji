package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
	Connection conn=new ConnectDao().getConnection();                                     //���ݿ����Ӷ���
	PreparedStatement ps;
	
	//��ӡ���ô��������е��豸��δ��ҳ��δָ�������̣�
	public List<Item> findItem() {
		// ����List
		List<Item> list = new ArrayList<Item>();
		// ��ȡ���ݿ�����
		
		// ��ҳ��ѯ��SQL���
		String sql = "SELECT * FROM item,iorder "
				+ "where item.vid=iorder.vid and ostate>1 "
				+ "order by iorder.oid desc ;";
		try {
			// ��ȡPreparedStatement
			ps = conn.prepareStatement(sql);
			// ִ�в�ѯ����
			ResultSet rs = ps.executeQuery();
			// �������ƶ������ж��Ƿ���Ч
			while (rs.next()) {
				// ʵ����Product
				Item p = new Item();
				// ��id���Ը�ֵ
				p.setType(rs.getString("type"));
				p.setIno(rs.getString("item.vid"));
				p.setMember(rs.getString("mid"));
				p.setAgent(rs.getString("uid"));
				p.setSswitch(rs.getInt("sswitch"));
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
	
	public Item findItemDtail(String ino) {
		// ����List
		Item p = new Item();		
		// ��ҳ��ѯ��SQL���
		String sql = "SELECT * FROM item,iorder "
				+ "where item.vid=? and item.vid=iorder.vid; ";
		try {
			// ��ȡPreparedStatement
			ps = conn.prepareStatement(sql);
			// ��SQL����еĵ�1��������ֵ
			ps.setString(1, ino);
			// ִ�в�ѯ����
			ResultSet rs = ps.executeQuery();
			// �������ƶ������ж��Ƿ���Ч
			while (rs.next()) {
				// ��id���Ը�ֵ
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
			// �ر�ResultSet
			rs.close();
			// �ر�PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public boolean item_edit(String ino,String starttime,String endtime,int sswitch) {	
		// ��ѯ�ܼ�¼��SQL���
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


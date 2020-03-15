package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDao {
	Connection conn=new ConnectDao().getConnection();                                     //���ݿ����Ӷ���
	PreparedStatement ps,ps1;
	
	//��ӡ���ô��������е��豸��δ��ҳ��
	public List<Item> findItem(String agent) {
		// ����List
		List<Item> list = new ArrayList<Item>();
		// ��ȡ���ݿ�����
		
		// ��ҳ��ѯ��SQL���
		String sql = "SELECT * FROM iteminfo,iorder where uid=? and iorder.type=iteminfo.type "
				+ "order by oid desc ; ";
		try {
			// ��ȡPreparedStatement
			ps = conn.prepareStatement(sql);
			// ��SQL����еĵ�1��������ֵ
			ps.setString(1, agent);
			// ִ�в�ѯ����
			ResultSet rs = ps.executeQuery();
			// �������ƶ������ж��Ƿ���Ч
			while (rs.next()) {
				// ʵ����Product
				Item p = new Item();
				// ��id���Ը�ֵ
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
			// �ر�ResultSet
			rs.close();
			// �ر�PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Item findItemDetail(int oid) {
		// ����List
		Item p = new Item();	
		int oostate=0;
		
		try {
			String sql0 = "SELECT ostate FROM iorder where oid=?; ";
			ps = conn.prepareStatement(sql0);
			// ��SQL����еĵ�1��������ֵ
			ps.setInt(1, oid);
			// ִ�в�ѯ����
			ResultSet rs0;
			rs0 = ps.executeQuery();
			// �������ƶ������ж��Ƿ���Ч
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
				// ��ȡPreparedStatement
				ps = conn.prepareStatement(sql);
				// ��SQL����еĵ�1��������ֵ
				ps.setInt(1, oid);
				// ִ�в�ѯ����
				ResultSet rs = ps.executeQuery();
				// �������ƶ������ж��Ƿ���Ч
				while (rs.next()) {
					// ��id���Ը�ֵ
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
				// �ر�ResultSet
				rs.close();
				// �ر�PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			String sql = "SELECT * FROM iorder,item,iteminfo "
					+ "where oid=? and iteminfo.type=iorder.type and item.vid=iorder.vid; ";
			try {
				// ��ȡPreparedStatement
				ps = conn.prepareStatement(sql);
				// ��SQL����еĵ�1��������ֵ
				ps.setInt(1, oid);
				// ִ�в�ѯ����
				ResultSet rs = ps.executeQuery();
				// �������ƶ������ж��Ƿ���Ч
				while (rs.next()) {
					// ��id���Ը�ֵ
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
				// �ر�ResultSet
				rs.close();
				// �ر�PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;
	}
	
	//,int renttime,int sswitch,String starttime,String endtime
	public boolean item_edit(int oid,int ostate,String kdcompany,String kdno) {	
		// ��ѯ�ܼ�¼��SQL���
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
		// ��ѯ�ܼ�¼��SQL���
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


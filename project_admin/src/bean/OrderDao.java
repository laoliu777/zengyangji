package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
	Connection conn=new ConnectDao().getConnection();                                     //���ݿ����Ӷ���
	PreparedStatement ps,ps1;
	
	//��ӡ�����У�δ��ҳ��
	public List<Order> findOrder() {
		// ����List
		List<Order> list = new ArrayList<Order>();
		// ��ȡ���ݿ�����
		
		// ��ҳ��ѯ��SQL���
		String sql = "SELECT * FROM iteminfo,iorder where iorder.type=iteminfo.type "
				+ "order by iorder.oid desc ; ";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// ʵ����Product
				Order p = new Order();
				// ��id���Ը�ֵ
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
			// �ر�ResultSet
			rs.close();
			// �ر�PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Order> findOrderAdd() {
		// ����List
		List<Order> list = new ArrayList<Order>();
		// ��ȡ���ݿ�����
		
		// ��ҳ��ѯ��SQL���
		String sql = "SELECT * FROM iteminfo,iorder "
				+ "where iorder.type=iteminfo.type and ostate=1 "
				+ "order by oid desc ; ";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// ʵ����Product
				Order p = new Order();
				// ��id���Ը�ֵ
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
			// �ر�ResultSet
			rs.close();
			// �ر�PreparedStatement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Order findOrderDetail(int oid) {
		// ����List
		Order p = new Order();	
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
			
		if(oostate<2) {
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
				// �ر�ResultSet
				rs.close();
				// �ر�PreparedStatement
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
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
	
	public boolean order_edit(int oid,int ostate,String kdcompany,String kdno) {	
		// ��ѯ�ܼ�¼��SQL���
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


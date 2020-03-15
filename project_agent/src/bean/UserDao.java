package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	Connection conn=new ConnectDao().getConnection();                                     //���ݿ����Ӷ���
	PreparedStatement ps;
	
	//��ӡ���ô����̵���Ϣ
		public User findUser(String uid) {
			User p = new User();
			// ��ҳ��ѯ��SQL���
			String sql = "SELECT * FROM agent where  uid=? ;";
			try {
				// ��ȡPreparedStatement
				ps = conn.prepareStatement(sql);
				// ��SQL����еĵ�1��������ֵ
				ps.setString(1, uid);
				// ִ�в�ѯ����
				ResultSet rs = ps.executeQuery();
				// �������ƶ������ж��Ƿ���Ч
				while (rs.next()) {
					// ��id���Ը�ֵ
					p.setAccount(rs.getString("uid"));
					p.setTel(rs.getString("utel"));
				}
				// �ر�ResultSet
				rs.close();
				// �ر�PreparedStatement
				ps.close();
				// �ر�Connection
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return p;
		}
}


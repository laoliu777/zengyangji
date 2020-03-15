package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.ConnectDao;
import bean.Login;

public class LoginDao {
	Connection conn=new ConnectDao().getConnection();                                     //数据库连接对象
	PreparedStatement pstmt;
	
	public Login checkLogin(String name,String password){ //验证用户名密码
		try{
			pstmt=conn.prepareStatement("select * from agent where uid=? and upassword=? ;");
			pstmt.setString(1, name);                     //设置SQL语句参数
			pstmt.setString(2, password);                 //设置SQL语句参数
			ResultSet rs=pstmt.executeQuery();            //执行查询，返回结果集
			if(rs.next()){                                //通过JavaBean保存值
				Login login=new Login();
				login.setAccount(rs.getString("uid"));
				return login;                             //返回JavaBean对象
			}
			return null;                                  //验证失败返回null
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean checkRegister(String name) {
		boolean flag = false;
		String sql = "select * from agent where uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
				flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Login getName(int id){
		Login login = new Login();
		try{
			pstmt=conn.prepareStatement("select * from user where account=?");
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				 login.setName(rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return login;
	}
	
	public boolean insertUser(String account,String name,String tel,String password){
		try{
			pstmt=conn.prepareStatement("insert into user(account,name,role,tel,password) values(?,?,?,?,?)");
			pstmt.setString(1, account);				
			pstmt.setString(2, name);
			pstmt.setInt(3, 0);				
			pstmt.setString(4, tel);				
			pstmt.setString(5, password);				
			pstmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.ConnectDao;

/**
 * Servlet implementation class UserAdd
 */
@WebServlet("/UserAdd")
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        int role = Integer.parseInt(request.getParameter("role"));
		String account = request.getParameter("account");
        String tel=request.getParameter("tel");

        /*
        String name = null;
        if(request.getParameter("name")!=null&&(!request.getParameter("name").equals("null"))) {
        	name=request.getParameter("name");
        }             
        */
        
        Connection conn = null;
        PreparedStatement stat = null;
    	PreparedStatement ps= null;
    	
    	switch (role) {
		case 0:
			try {
            	conn=new ConnectDao().getConnection();
            	                     	
                stat = conn.prepareStatement("INSERT INTO admin(aid,atel,apassword) "
                		+ "VALUES(?,?,?)");
                stat.setString(1, account);
                stat.setString(2, tel);
                stat.setString(3, "111111");
                stat.executeUpdate();            
                response.sendRedirect("user_success.jsp");           
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("user_fail.jsp");           
            }finally{
                if(stat!=null){
                    try{
                        stat.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                if(conn!=null){
                    try{
                        conn.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
			break;
		case 1:
			try {
            	conn=new ConnectDao().getConnection();
            	                     	
                stat = conn.prepareStatement("INSERT INTO agent(uid,utel,upassword) "
                		+ "VALUES(?,?,?)");
                stat.setString(1, account);
                stat.setString(2, tel);
                stat.setString(3, "111111");
                stat.executeUpdate();            
                response.sendRedirect("user_success.jsp");           
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("user_fail.jsp");           
            }finally{
                if(stat!=null){
                    try{
                        stat.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                if(conn!=null){
                    try{
                        conn.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
			break;
		case 2:
			try {
            	conn=new ConnectDao().getConnection();
            	                     	
                stat = conn.prepareStatement("INSERT INTO member(mid,mtel,mpassword) "
                		+ "VALUES(?,?,?)");
                stat.setString(1, account);
                stat.setString(2, tel);
                stat.setString(3, "111111");
                stat.executeUpdate();            
                response.sendRedirect("user_success.jsp");           
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("user_fail.jsp");           
            }finally{
                if(stat!=null){
                    try{
                        stat.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                if(conn!=null){
                    try{
                        conn.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
			break;
		default:
			break;
		}
    			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

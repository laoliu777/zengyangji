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
 import net.sf.json.JSONArray;
 import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//编码必须和页面编码一致
		String tel=request.getParameter("telephone"); 
        String password=request.getParameter("password");
        boolean type=false;//用于判断账号和密码是否与数据库中查询结果一致
        response.setContentType("text/json");
	    response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONArray jsonarray = new JSONArray();  
        JSONObject jsonobj = new JSONObject(); 
        con=DBUtil.CreateConn();
         sql="select * from member where mid=? and mpassword=? ";
        try
        {
        	prestmt = con.prepareStatement(sql);
			prestmt.setString(1, tel);
			prestmt.setString(2, password);
			rs = prestmt.executeQuery();
		    while(rs.next())
		    {
		    	 jsonobj.put("mid", rs.getString("mid"));  
		    	 //jsonobj.put("userfriend", rs.getInt("userfriend"));   
		    	 jsonarray.put(jsonobj); 
		    	 type=true;
		    }
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        finally
        {
        	out.print(jsonarray);
        	DBUtil.close(rs);
        	DBUtil.close(prestmt);
        	DBUtil.close(con);
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

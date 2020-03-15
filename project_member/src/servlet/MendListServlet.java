package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MendListServlet
 */
@WebServlet("/MendListServlet")
public class MendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MendListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//编码必须和页面编码一致
		String mid=request.getParameter("mid"); 
		//String mid="啊啊啊";
		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
  
    	//sql="select * from iorder,mend where mid=? and iorder.vid=mend.vid and ostate>1 ";
	    	sql="select * from mend "
	    			+ "where vid in (select vid from iorder where mid=? ) "
	    			+ "order by mendid desc ; ";

    	 try
         {
         	prestmt = con.prepareStatement(sql);
           	prestmt.setString(1,mid);//自动添加单引号 （包装后的参数）

 			rs = prestmt.executeQuery();
 		    while(rs.next())
 		    {
		        jsonobj.put("mendid", rs.getInt("mendid"));  
 		    	jsonobj.put("vid", rs.getString("mend.vid"));  
 		    	jsonobj.put("problem", rs.getString("problem"));  
		        jsonobj.put("mtime", rs.getString("mtime"));  
				SimpleDateFormat df1=new SimpleDateFormat("yyyyMMdd");
				String mmid1=df1.format(rs.getDate("mtime"));
				String mmid=mmid1+String.format("%03d",rs.getInt("mendid"));
		        //String mmendid=rs.getString("mtime").replaceAll("-", "")+String.format("%03d",rs.getInt("mendid"));
		        jsonobj.put("mmendid", mmid);  
		        jsonobj.put("mstate", rs.getInt("mstate"));  

		        jsonarray.put(jsonobj); 
		        jsonobj=new JSONObject();
 		    }
         } catch(Exception ex)
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

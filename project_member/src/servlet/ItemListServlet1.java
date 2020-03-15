package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ItemListServlet1
 */
@WebServlet("/ItemListServlet1")
public class ItemListServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql0 = null,sql1 = null;
          
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemListServlet1() {
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
		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
    
	        sql0="update  item set sswitch=0 where endtime<CURDATE(); ";
	        sql1="select * from iorder, item where mid=? and iorder.vid=item.vid and ostate=2 and endtime<CURDATE() ";
      	
      	 try
           {
      		prestmt = con.prepareStatement(sql0);
			prestmt.execute();
			 
           	prestmt = con.prepareStatement(sql1);
           	prestmt.setString(1,mid);//自动添加单引号 （包装后的参数）
   			rs = prestmt.executeQuery();
   		    while(rs.next())
   		    {
   		    	jsonobj.put("type", rs.getString("type"));  
		        jsonobj.put("ino", rs.getString("iorder.vid"));  
		        jsonobj.put("sswitch", rs.getString("sswitch"));  
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

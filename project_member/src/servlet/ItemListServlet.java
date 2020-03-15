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
 * Servlet implementation class ItemListServlet
 */
@WebServlet("/ItemListServlet")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null,sql1 = null,sql2 = null,sql3 = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemListServlet() {
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
    
	    /*
	    sql=" select * from iorder,item "
      			+ "where mid=? and iorder.vid=item.vid and ostate=2 and (endtime>CURDATE() or renttime=0) "
      			+ "order by endtime ";
	     */
      	sql=" select * from iorder,item "
      			+ "where mid=? and iorder.vid=item.vid and ostate=2 and endtime>CURDATE() "
      			+ "order by endtime ";
      	sql1=" select * from iorder,item "
      			+ "where mid=? and iorder.vid=item.vid and ostate=2 and renttime=0 "
      			+ "order by otime ";
      	sql2="update  item set sswitch=0 where endtime<CURDATE(); ";
        sql3="select * from iorder, item where mid=? and iorder.vid=item.vid and ostate=2 and endtime<CURDATE() ";
       
      	
      	 try
           {
      		 //找到租的
           	prestmt = con.prepareStatement(sql);
           	prestmt.setString(1,mid);//自动添加单引号 （包装后的参数）
   			rs = prestmt.executeQuery();
   		    while(rs.next())
   		    {
   		    	jsonobj.put("type", rs.getString("type"));  
		        jsonobj.put("ino", rs.getString("iorder.vid"));  
		        jsonobj.put("sswitch", rs.getString("sswitch"));  
		        int renttime=rs.getInt("renttime");
		    	 if(renttime>0) {
			    	 jsonobj.put("endtime", rs.getString("endtime"));   
			    	 jsonobj.put("buystate", 0);   
		    	 }else {
			    	 jsonobj.put("buystate", 1);   
		    	 }
		        jsonarray.put(jsonobj); 
		        jsonobj=new JSONObject();
   		    }
   		    
   		    //找到买的
   		    prestmt = con.prepareStatement(sql1);
        	prestmt.setString(1,mid);//自动添加单引号 （包装后的参数）
			rs = prestmt.executeQuery();
		    while(rs.next())
		    {
		    	jsonobj.put("type", rs.getString("type"));  
		        jsonobj.put("ino", rs.getString("iorder.vid"));  
		        jsonobj.put("sswitch", rs.getString("sswitch"));  
		        int renttime=rs.getInt("renttime");
		    	 if(renttime>0) {
			    	 jsonobj.put("endtime", rs.getString("endtime"));   
			    	 jsonobj.put("buystate", 0);   
		    	 }else {
			    	 jsonobj.put("buystate", 1);   
		    	 }
		        jsonarray.put(jsonobj); 
		        jsonobj=new JSONObject();
		    }
		    
		    //找到租到期的
		    prestmt = con.prepareStatement(sql2);
			prestmt.execute();
			 
           	prestmt = con.prepareStatement(sql3);
           	prestmt.setString(1,mid);//自动添加单引号 （包装后的参数）
   			rs = prestmt.executeQuery();
   		    while(rs.next())
   		    {
   		    	jsonobj.put("type", rs.getString("type"));  
		        jsonobj.put("ino", rs.getString("iorder.vid"));  
		        jsonobj.put("sswitch", rs.getString("sswitch"));  
		    	jsonobj.put("buystate", 2);   
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

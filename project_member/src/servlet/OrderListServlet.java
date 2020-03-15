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
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListServlet() {
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
		int ordertype=Integer.parseInt(request.getParameter("ordertype")); 
		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
  
    	sql="select * from iorder,iteminfo where mid=? and ostate=? and iorder.type=iteminfo.type order by oid desc ";
    	
    	 try
         {
         	prestmt = con.prepareStatement(sql);
           	prestmt.setString(1,mid);//自动添加单引号 （包装后的参数）
           	prestmt.setInt(2,ordertype);//自动添加单引号 （包装后的参数）

 			rs = prestmt.executeQuery();
 		    while(rs.next())
 		    {
 		    	jsonobj.put("oid", rs.getString("oid"));  
 		    	jsonobj.put("type", rs.getString("iorder.type"));  
		        jsonobj.put("uid", rs.getString("uid"));  
		        jsonobj.put("otime", rs.getString("otime"));  
		        int month=rs.getInt("renttime");
		    	 if(month>0) {
				        int yajing=rs.getInt("yajing");
				        float zujing=rs.getFloat("zujing");
				        int zongjia=(int)(yajing+zujing*month);
				        jsonobj.put("zongjia", zongjia);  
			    	 jsonobj.put("buystate", 0);   
		    	 }else {
			    	 jsonobj.put("zongjia", rs.getString("shoujia"));  
			    	 jsonobj.put("buystate", 1);   
		    	 }

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

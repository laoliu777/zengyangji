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
 * Servlet implementation class OrderDetailServlet
 */
@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//±àÂë±ØÐëºÍÒ³Ãæ±àÂëÒ»ÖÂ

		String oid=request.getParameter("oid"); 
		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
	        
	        sql="select * from iorder,iteminfo where oid=? and iorder.type=iteminfo.type ";
	        try
	        {
	        	prestmt = con.prepareStatement(sql);
				prestmt.setString(1, oid);
				rs = prestmt.executeQuery();
			    while(rs.next())
			    {
			    	 jsonobj.put("type", rs.getString("iorder.type"));  
			    	 jsonobj.put("brand", rs.getString("brand"));  
			    	 jsonobj.put("vid", rs.getString("vid"));  
			    	 jsonobj.put("uid", rs.getString("uid"));  
			    	 int month=rs.getInt("renttime");
			    	 if(month>0) {
					        int yajing=rs.getInt("yajing");
					        float zujing=rs.getFloat("zujing");
					        int zongjia=(int)(yajing+zujing*month);
					   	 jsonobj.put("yajing", rs.getString("yajing"));  
					     jsonobj.put("zujing", rs.getString("zujing"));  
					     jsonobj.put("zongjia", zongjia);  
				    	 jsonobj.put("renttime", rs.getString("renttime"));  
				    	 jsonobj.put("buystate", 0);   
			    	 }else {
				    	 jsonobj.put("zongjia", rs.getString("shoujia"));  
				    	 jsonobj.put("buystate", 1);   
			    	 }
			    	 jsonobj.put("ostate", rs.getString("ostate"));  
			    	 jsonobj.put("otime", rs.getString("otime"));  
			    	 jsonobj.put("kdcompany", rs.getString("kdcompany"));  
			    	 jsonobj.put("kdno", rs.getString("kdno"));  
				        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
						String ooid1=df.format(rs.getDate("otime"));
						String ooid=ooid1+String.format("%05d",rs.getInt("oid"));
				        jsonobj.put("ooid", ooid);  
			    	 jsonarray.put(jsonobj); 
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

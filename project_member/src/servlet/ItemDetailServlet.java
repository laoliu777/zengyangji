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
 * Servlet implementation class ItemDetailServlet
 */
@WebServlet("/ItemDetailServlet")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null,sql1 = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//编码必须和页面编码一致

		//String type=request.getParameter("type"); 
		String ino=request.getParameter("ino"); 
		int ssswitch=0;
		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
	        
	        sql="select * from item,iorder,iteminfo where iorder.vid=? and item.vid=iorder.vid and iorder.type=iteminfo.type ";
	        sql1="select TIMESTAMPDIFF(MINUTE,(select runtime from item where vid=? ),now())";
	        
	        try
	        {
	        	prestmt = con.prepareStatement(sql);
				prestmt.setString(1, ino);
				//prestmt.setString(2, ino);
				rs = prestmt.executeQuery();
			    while(rs.next())
			    {
			    	 jsonobj.put("oid", rs.getString("oid"));  
			    	 jsonobj.put("type", rs.getString("type"));  
			    	 jsonobj.put("ino", rs.getString("item.vid"));  
			    	 jsonobj.put("brand", rs.getString("brand"));  
			    	 jsonobj.put("spec", rs.getString("spec"));  
			    	 jsonobj.put("agent", rs.getString("uid"));  
			    	 jsonobj.put("sswitch", rs.getString("sswitch"));  
			    	 int renttime=rs.getInt("renttime");
			    	 if(renttime>0) {
			    		 jsonobj.put("starttime", rs.getString("starttime"));   
				    	 jsonobj.put("endtime", rs.getString("endtime"));   
				    	 jsonobj.put("yajing", rs.getString("yajing"));  
				    	 jsonobj.put("zujing", rs.getString("zujing"));  
				    	 jsonobj.put("buystate", 0);   
			    	 }else {
				    	 jsonobj.put("shoujia", rs.getString("shoujia"));  
				    	 jsonobj.put("buystate", 1);   
			    	 }
			    	 //里面界面用到的参数
			    	 ssswitch=rs.getInt("sswitch");
			    	 jsonobj.put("clock", rs.getString("clock"));  
			    	 String oontime=rs.getString("ontime");
			    	 String oofftime=rs.getString("offtime");
			    	 if(oontime!=null) {
			    		 String ontime1=oontime.substring(0, 5);
				    	 String offtime1=oofftime.substring(0, 5);
				    	 jsonobj.put("ontime", ontime1);  
				    	 jsonobj.put("offtime", offtime1);  
			    	 }			    	 
			    	 jsonarray.put(jsonobj); 
			    }
			    
			    if(ssswitch==1) {
			    	prestmt = con.prepareStatement(sql1);
					prestmt.setString(1, ino);
					rs = prestmt.executeQuery();
				    while(rs.next()) {
				    	jsonobj.put("runtime", rs.getInt(1));  
				    	jsonarray.put(jsonobj); 
				    }
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

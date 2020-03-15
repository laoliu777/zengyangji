package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt1 = null,prestmt2 = null,prestmt3 = null;
	private ResultSet rs = null;
	private String sql1 = null,sql2 = null,sql3 = null,sql33 = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//编码必须和页面编码一致

		String oid=request.getParameter("toid"); 
		
		/*
		String imei1=request.getParameter("imei1"); 
		String imei2=request.getParameter("imei2"); 
		String imei3=request.getParameter("imei3"); 
		String imei4=request.getParameter("imei4"); 
		String imei=imei1+"-"+imei2+"-"+imei3+"-"+imei4; 
		*/
		
		String imei=request.getParameter("imei"); 
		int month_rent=1;

		//System.out.println("mid:"+mid+" uid:"+uid);

		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();

	        //找出租赁天数
	       sql1="select renttime from iorder where oid=?  ";	        	        		
	        
	        //增item
	      sql3="insert into item (vid,starttime,endtime) values (?,?,?);";
	      sql33="insert into item (vid) values (?);";
	      
	  		//改iorder
	      sql2="update iorder set vid=?,ostate=2 where oid=? ";		
	      
	      try
	        {
		        //找出租赁天数
	    	  	prestmt1 = con.prepareStatement(sql1);
				prestmt1.setString(1, oid);
				rs = prestmt1.executeQuery();
			    while(rs.next())
			    {
			    	month_rent=rs.getInt("renttime");  
			    }
			    
			    if(month_rent>0) {
					Calendar cal=Calendar.getInstance();
					Date time=cal.getTime();
					String starttime= new SimpleDateFormat("yyyy-MM-dd").format(time);
					Calendar cal2=Calendar.getInstance();
					cal2.add(Calendar.DATE,month_rent);
					Date time2=cal2.getTime();
					String endtime= new SimpleDateFormat("yyyy-MM-dd").format(time2);
		
			        //增item
		        	prestmt3 = con.prepareStatement(sql3);
		        	prestmt3.setString(1, imei);
		        	prestmt3.setString(2, starttime);
		        	prestmt3.setString(3, endtime);
		        	prestmt3.execute();
		        	
					//改iorder
					prestmt2 = con.prepareStatement(sql2);
					prestmt2.setString(1, imei);
					prestmt2.setString(2, oid);
					prestmt2.execute();
			    }else {
							
			        //增item
		        	prestmt3 = con.prepareStatement(sql33);
		        	prestmt3.setString(1, imei);
		        	prestmt3.execute();
		        	
					//改iorder
					prestmt2 = con.prepareStatement(sql2);
					prestmt2.setString(1, imei);
					prestmt2.setString(2, oid);
					prestmt2.execute();
			    }

				
				out.print("ok");
	        }
	        catch(Exception ex)
	        {
	        	out.print("no");
	        	ex.printStackTrace();
	        }
	        finally
	        {   
	        	DBUtil.close(rs);
	        	DBUtil.close(prestmt1);
	        	DBUtil.close(prestmt2);
	        	DBUtil.close(prestmt3);
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

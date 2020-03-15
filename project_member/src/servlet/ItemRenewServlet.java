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
 * Servlet implementation class ItemRenewServlet
 */
@WebServlet("/ItemRenewServlet")
public class ItemRenewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt1 = null,prestmt2 = null,prestmt3 = null,prestmt4 = null;
	private ResultSet rs = null;
	private String sql1 = null,sql2 = null,sql3 = null,sql4 = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemRenewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//±‡¬Î±ÿ–Î∫Õ“≥√Ê±‡¬Î“ª÷¬

		String ino=request.getParameter("ino"); 		
		String mid=request.getParameter("mid"); 		
		int month_rent=Integer.parseInt(request.getParameter("monthrent")); 
		float zujing=Float.parseFloat(request.getParameter("zujing")); 
		int zongjia=0; 	
		zongjia=(int)(zujing*30*month_rent);
		int purse=0;

		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();

	        sql1="select purse from member where mid=? ; ";
	        sql2="update iorder set renttime=renttime+? where vid=?;";
	        sql3="update item set endtime=DATE_ADD( endtime, INTERVAL ? DAY) where vid=?;";
	        sql4="update member set purse=? where mid=? ;";
	      
	    	  try
		        {    		  		    	  
		        	prestmt1 = con.prepareStatement(sql1);
		        	prestmt1.setString(1, mid);
		 			rs = prestmt1.executeQuery();
		 		    while(rs.next())
		 		    {
		 		    	purse=rs.getInt("purse");  
		 		    }
		 		    
		 		   if(zongjia<purse) {
		 			   	prestmt2 = con.prepareStatement(sql2);
			        	prestmt2.setInt(1, month_rent*30);
			        	prestmt2.setString(2, ino);
			        	prestmt2.execute();
						 
			        	prestmt3 = con.prepareStatement(sql3);
			        	prestmt3.setInt(1, month_rent*30);
			        	prestmt3.setString(2, ino);
			        	prestmt3.execute();
			        	
				       	prestmt4 = con.prepareStatement(sql4);
				       	prestmt4.setInt(1, purse-zongjia);
				       	prestmt4.setString(2, mid);
						prestmt4.execute();
						 
						out.print("ok");
		 		   }else {
		 	    	  out.print("no");
		 	      }
	    		  
		        	
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
		        	if(zongjia<purse) {
		        		DBUtil.close(prestmt2);
			        	DBUtil.close(prestmt3);
			        	DBUtil.close(prestmt4);
		        	}
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

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
 * Servlet implementation class Order1ItemServlet
 */
@WebServlet("/Order1ItemServlet")
public class Order1ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt0 = null,prestmt1 = null,prestmt2 = null,prestmt3 = null;
	private ResultSet rs0 = null,rs = null;
	private String sql0 = null,sql1 = null,sql2 = null,sql3 = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order1ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//±‡¬Î±ÿ–Î∫Õ“≥√Ê±‡¬Î“ª÷¬

		String type=request.getParameter("type"); 
		String uid=request.getParameter("oagent"); 	
		String mid=request.getParameter("mid"); 		
		int zongjia=0; 	
		int purse=0;

		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();

	        sql0="select * from iteminfo where type=?; ";
	        sql1="select purse from member where mid=? ; ";
	        sql2="insert into iorder (type,mid,uid,renttime,otime) values (?,?,?,?,now());";
	        sql3="update member set purse=? where mid=? ;";
	      
	    	  try
		        {
	    		  	prestmt0 = con.prepareStatement(sql0);
		        	prestmt0.setString(1, type);
		 			rs0 = prestmt0.executeQuery();
		 		    while(rs0.next())
		 		    {
		 		    	zongjia=rs0.getInt("shoujia");
				    }
		    	  
		        	prestmt1 = con.prepareStatement(sql1);
		        	prestmt1.setString(1, mid);
		 			rs = prestmt1.executeQuery();
		 		    while(rs.next())
		 		    {
		 		    	purse=rs.getInt("purse");  
		 		    }
		 		    
		 		   if(zongjia<purse) {
		 			   	prestmt2 = con.prepareStatement(sql2);
		 			  	prestmt2.setString(1, type);
			        	prestmt2.setString(2, mid);
			        	prestmt2.setString(3, uid);
			        	prestmt2.setInt(4, 0);
			        	prestmt2.execute();
						 
				       	prestmt3 = con.prepareStatement(sql3);
				       	prestmt3.setInt(1, purse-zongjia);
				       	prestmt3.setString(2, mid);
						prestmt3.execute();
						 
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
		        	DBUtil.close(rs0);
		        	DBUtil.close(prestmt0);
	        	 	DBUtil.close(rs);
		        	DBUtil.close(prestmt1);
		        	if(zongjia<purse) {
		        		DBUtil.close(prestmt2);
			        	DBUtil.close(prestmt3);
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

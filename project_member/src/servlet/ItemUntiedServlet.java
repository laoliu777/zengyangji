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
 * Servlet implementation class ItemUntiedServlet
 */
@WebServlet("/ItemUntiedServlet")
public class ItemUntiedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt1 = null,prestmt2 = null;
	private ResultSet rs = null;
	private String sql1 = null,sql2 = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemUntiedServlet() {
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
		//String mid=request.getParameter("mid"); 		
		String oid=request.getParameter("oid"); 		

		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();

	        sql1="update iorder set ostate=3 where oid=?;";
	        sql2="delete from item where vid=?;";
	        //sql3="update item set endtime=DATE_ADD( endtime, INTERVAL ? DAY) where vid=?;";
	        //sql4="update member set purse=? where mid=? ;";
	      
	    	  try
		        {    		  		    	  
		        	prestmt1 = con.prepareStatement(sql1);
		        	prestmt1.setString(1, oid);
		        	prestmt1.execute();
		 		    
		 			prestmt2 = con.prepareStatement(sql2);
			        prestmt2.setString(2, ino);
			        prestmt2.execute();
						 			       					 
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

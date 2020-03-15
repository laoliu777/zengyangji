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
 * Servlet implementation class ItemClockAddServlet
 */
@WebServlet("/ItemClockAddServlet")
public class ItemClockAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemClockAddServlet() {
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
		String starttime1=request.getParameter("starttime1"); 
		String starttime2=request.getParameter("starttime2"); 
		String endtime1=request.getParameter("endtime1"); 
		String endtime2=request.getParameter("endtime2"); 
		String starttime=starttime1+":"+starttime2; 
		String endtime=endtime1+":"+endtime2; 
		
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
	        
	        
	      sql="update  item set clock=1,ontime=?,offtime=? where vid=? ";
	      
	      try
	        {
	        	prestmt = con.prepareStatement(sql);
				prestmt.setString(1, starttime);
				prestmt.setString(2, endtime);
				prestmt.setString(3, ino);
				 prestmt.execute();
				 out.print("ok");
	        }
	        catch(Exception ex)
	        {out.print("no");
	        	ex.printStackTrace();
	        }
	        finally
	        {   
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

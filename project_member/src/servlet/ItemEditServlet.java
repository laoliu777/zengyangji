package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 import net.sf.json.JSONArray;
 import net.sf.json.JSONObject;

/**
 * Servlet implementation class ItemEditServlet
 */
@WebServlet("/ItemEditServlet")
public class ItemEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null,sql1 = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//±‡¬Î±ÿ–Î∫Õ“≥√Ê±‡¬Î“ª÷¬

		//String type=request.getParameter("type"); 
		String ino=request.getParameter("ino"); 
		int sswitch_state=Integer.parseInt(request.getParameter("sswitch")); 
		 response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
	        int ss=0;
	        if(sswitch_state==0) {
	        	ss=1;
	        }
	        
	      sql="update item set sswitch=? where vid=? ";
	      sql1="update item set runtime=now() where vid=? ";
	      
	      try
	        {
	        	prestmt = con.prepareStatement(sql);
				prestmt.setInt(1, ss);
				prestmt.setString(2, ino);
				prestmt.execute();
				 
				 if(sswitch_state==0) {
					 prestmt = con.prepareStatement(sql1);
					 prestmt.setString(1, ino);
					 prestmt.execute();
				 }
				 
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

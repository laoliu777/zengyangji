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
 * Servlet implementation class RegisterCheck
 */
@WebServlet("/RegisterCheck")
public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql1 = null,sql2 = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//±‡¬Î±ÿ–Î∫Õ“≥√Ê±‡¬Î“ª÷¬
		String mtel=request.getParameter("mtel"); 
		String mid=request.getParameter("mid"); 

		int check=0;
		
		 	response.setContentType("text/json");
		    response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        JSONArray jsonarray = new JSONArray();  
	        JSONObject jsonobj = new JSONObject(); 
	        con=DBUtil.CreateConn();
	        
	    	sql1="select * from member where mtel=?  ";
	    	sql2="select * from member where mid=?  ";

	      try
	        {
	        	prestmt = con.prepareStatement(sql1);
	        	prestmt.setString(1, mtel);
	 			rs = prestmt.executeQuery();
	 		    while(rs.next())
	 		    {
	 		    	check=1;
	 		    }
	 		    
	 		   prestmt = con.prepareStatement(sql2);
	        	prestmt.setString(1, mid);
	 			rs = prestmt.executeQuery();
	 		    while(rs.next())
	 		    {
	 		    	check=2;
	 		    }
	        }
	      	catch(Exception ex)
	         {
	         	ex.printStackTrace();
	         }
	         finally
	         {
	        	 if(check==1) {
	        		 out.print("mtel");
	        	 }else if(check==2) {
	        		 out.print("mid");
	        	 }
	         	
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

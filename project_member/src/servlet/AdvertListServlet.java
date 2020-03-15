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
 * Servlet implementation class AdvertListServlet
 */
@WebServlet("/AdvertListServlet")
public class AdvertListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement prestmt = null;
	private ResultSet rs = null;
	private String sql = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json");
	    response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONArray jsonarray = new JSONArray();  
        JSONObject jsonobj = new JSONObject(); 
        con=DBUtil.CreateConn();

 	sql="select * from advert order by advid desc ; ";
 	
 	 try
      {
      	prestmt = con.prepareStatement(sql);
			rs = prestmt.executeQuery();
		    while(rs.next())
		    {
		    jsonobj.put("advid", rs.getString("advid"));  
	        jsonobj.put("adtitle", rs.getString("adtitle"));  
	        jsonobj.put("adpic", rs.getString("adpic"));  
	        jsonobj.put("adtime", rs.getString("adtime"));  
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

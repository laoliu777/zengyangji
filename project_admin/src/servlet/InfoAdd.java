package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ConnectDao;
import bean.InfoDao;

/**
 * Servlet implementation class InfoAdd
 */
@WebServlet("/InfoAdd")
public class InfoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String type = request.getParameter("type");
        String brand = request.getParameter("brand");
        int yajing = Integer.parseInt(request.getParameter("yajing"));
        float zujing = Float.parseFloat(request.getParameter("zujing"));
        int shoujia = Integer.parseInt(request.getParameter("shoujia"));
        String spec1 = request.getParameter("spec1");
        String spec2 = request.getParameter("spec2");
        String spec = spec1+"KM "+spec2+"V";
        /*
        String name = null;
        if(request.getParameter("name")!=null&&(!request.getParameter("name").equals("null"))) {
        	name=request.getParameter("name");
        }             
        */
        
        Connection conn = null;
        PreparedStatement stat = null;
    	PreparedStatement ps= null;
    	
			try {
            	conn=new ConnectDao().getConnection();
            	                     	
                stat = conn.prepareStatement("INSERT INTO iteminfo(type,zujing,yajing,shoujia,brand,spec) "
                		+ "VALUES(?,?,?,?,?,?)");
                stat.setString(1, type);
                stat.setFloat(2, zujing);
                stat.setInt(3, yajing);
                stat.setInt(4, shoujia);
                stat.setString(5, brand);
                stat.setString(6, spec);
                stat.executeUpdate();            
                response.sendRedirect("iteminfo_success.jsp");           
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("iteminfo_fail.jsp");           
            }finally{
                if(stat!=null){
                    try{
                        stat.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                if(conn!=null){
                    try{
                        conn.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
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

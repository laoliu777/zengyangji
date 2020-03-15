package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.InfoDao;

/**
 * Servlet implementation class InfoEdit
 */
@WebServlet("/InfoEdit")
public class InfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoEdit() {
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
        
		InfoDao dao = new InfoDao();
		
		if(dao.info_edit(type,zujing,yajing,shoujia,brand,spec)){
            response.sendRedirect("iteminfo_success.jsp");           
		}else {
            response.sendRedirect("iteminfo_fail.jsp");           
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

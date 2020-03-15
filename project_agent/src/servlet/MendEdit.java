package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import bean.MendDao;

/**
 * Servlet implementation class MendEdit
 */
@WebServlet("/MendEdit")
public class MendEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MendEdit() {
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

		int mendid = Integer.parseInt(request.getParameter("mendid"));
        int mstate = Integer.parseInt(request.getParameter("mstate"));

		MendDao dao = new MendDao();
		// 查询所有商品信息
		if(dao.mend_edit(mendid,mstate)){
            response.sendRedirect("mend_success.jsp");           
		}else {
            response.sendRedirect("mend_fail.jsp");           
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

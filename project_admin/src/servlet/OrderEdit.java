package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import bean.OrderDao;

/**
 * Servlet implementation class OrderEdit
 */
@WebServlet("/OrderEdit")
public class OrderEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderEdit() {
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

        //读取请求参数值
		int oid = Integer.parseInt(request.getParameter("oid"));
		int ostate = Integer.parseInt(request.getParameter("ostate"));		
        String kdcompany = request.getParameter("kdcompany");
        String kdno = request.getParameter("kdno");
        
		OrderDao aaaa = new OrderDao();
		if(aaaa.order_edit( oid,ostate, kdcompany, kdno)){
			response.sendRedirect("order_success.jsp");//?app_id=+app_id
		}else {
			response.sendRedirect("order_fail.jsp");//?app_id=+app_id
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

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import bean.OrderDao;

/**
 * Servlet implementation class ItemAddDetailOrd
 */
@WebServlet("/ItemAddDetailOrd")
public class ItemAddDetailOrd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemAddDetailOrd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		int oid = Integer.parseInt(request.getParameter("oid"));
		
		OrderDao dao = new OrderDao();
		// ��ѯ������Ʒ��Ϣ
		Order orderdetail = dao.findOrderDetail(oid);
		
		request.setAttribute("orderdetail", orderdetail);
		
		// ת����product_list.jspҳ��
		request.getRequestDispatcher("item_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Item;
import bean.ItemDao;
/**
 * Servlet implementation class ItemEditDetail
 */
@WebServlet("/ItemEditDetail")
public class ItemEditDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemEditDetail() {
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

		ItemDao dao = new ItemDao();
		// 查询所有商品信息
		Item itemdetail = dao.findItemDetail(oid);
		// 将list放置到request之中
		request.setAttribute("itemdetail", itemdetail);
		
		// 转发到product_list.jsp页面
		request.getRequestDispatcher("item_kd_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

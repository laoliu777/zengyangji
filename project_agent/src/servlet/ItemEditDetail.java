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
		// ��ѯ������Ʒ��Ϣ
		Item itemdetail = dao.findItemDetail(oid);
		// ��list���õ�request֮��
		request.setAttribute("itemdetail", itemdetail);
		
		// ת����product_list.jspҳ��
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

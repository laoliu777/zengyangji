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
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ino = request.getParameter("ino");
		// ʵ����ProductDao
		ItemDao dao = new ItemDao();
		// ��ѯ������Ʒ��Ϣ
		Item itemdetail = dao.findItemDtail(ino);
		// ��list���õ�request֮��
		request.setAttribute("itemdetail", itemdetail);
		
		// ת����product_list.jspҳ��
		request.getRequestDispatcher("item_detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

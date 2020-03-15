package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Advert;
import bean.AdvertDao;

/**
 * Servlet implementation class AdvertEditDetail
 */
@WebServlet("/AdvertEditDetail")
public class AdvertEditDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertEditDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		int advid = Integer.parseInt(request.getParameter("advid"));
		AdvertDao dao = new AdvertDao();
		// ��ѯ������Ʒ��Ϣ
		Advert advdetail = dao.findAdvertDtail(advid);
		// ��list���õ�request֮��
		request.setAttribute("advertdetail", advdetail);
		// ת����product_list.jspҳ��
		request.getRequestDispatcher("advert_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

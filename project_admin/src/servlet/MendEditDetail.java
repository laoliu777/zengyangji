package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Mend;
import bean.MendDao;

/**
 * Servlet implementation class MendEditDetail
 */
@WebServlet("/MendEditDetail")
public class MendEditDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MendEditDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		int mendid = Integer.parseInt(request.getParameter("mendid"));
		MendDao dao = new MendDao();
		// ��ѯ������Ʒ��Ϣ
		Mend menddetail = dao.findMendDtail(mendid);
		// ��list���õ�request֮��
		request.setAttribute("menddetail", menddetail);
		// ת����product_list.jspҳ��
		request.getRequestDispatcher("mend_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

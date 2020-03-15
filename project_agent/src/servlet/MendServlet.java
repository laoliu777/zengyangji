package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import bean.Mend;
import bean.MendDao;
/**
 * Servlet implementation class MendServlet
 */
@WebServlet("/MendServlet")
public class MendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String agent=(String) request.getSession().getAttribute("my_uid");
		MendDao dao = new MendDao();
		// ��ѯ������Ʒ��Ϣ
		List<Mend> list = dao.findAllMend(agent);
		// ��list���õ�request֮��
		request.setAttribute("list", list);
		// ת����product_list.jspҳ��
		request.getRequestDispatcher("mend.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.User;
import bean.UserDao;
/**
 * Servlet implementation class UserEditDetail
 */
@WebServlet("/UserEditDetail")
public class UserEditDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		/*
		int role = Integer.parseInt(request.getParameter("role"));
		String account = request.getParameter("account");
		*/
		
		int role=0;
		String account="管理员1";
		
		UserDao dao = new UserDao();
		User userdetail = dao.findUserDtail(account,role);

		request.setAttribute("userdetail", userdetail);
		// 转发到product_list.jsp页面
		request.getRequestDispatcher("user_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

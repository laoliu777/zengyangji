package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Login;
import bean.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int denglu=0;
		LoginDao loginDao= new LoginDao();
		HttpSession session=request.getSession();         // �Ȼ��user��������ǵ�һ�η��ʸ�Servlet���û�����϶�Ϊ�գ�������ǵ�
		Login l = new Login();
		String my_name = request.getParameter("username");
		if(request.getParameter("username") != null)
			l = loginDao.checkLogin(my_name,request.getParameter("password"));
		if(l!=null){//�����½�ɹ�
			//ArrayList<MessBoar> al=loginDao.findMbInfo();           //��ȡ���԰�����ݣ�����һ������
			//session.setAttribute("al", al);               //�����鱣������
			//session.setAttribute("login",l);              //����ȡ�Ķ��󱣴���session��
			session.setAttribute("my_uid",l.getAccount());
			denglu=0;
			session.setAttribute("denglu",denglu);   
			response.sendRedirect("IndexServlet");            //��֤�ɹ���ת�� main.jsp
		}
		else{                                             
			denglu=1;
			session.setAttribute("denglu",denglu);
			response.sendRedirect("login.jsp");		
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

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
		HttpSession session=request.getSession();         // 先获得user对象，如果是第一次访问该Servlet，用户对象肯定为空，但如果是第
		Login l = new Login();
		String my_name = request.getParameter("username");
		if(request.getParameter("username") != null)
			l = loginDao.checkLogin(my_name,request.getParameter("password"));
		if(l!=null){//如果登陆成功
			//ArrayList<MessBoar> al=loginDao.findMbInfo();           //获取留言板的内容，返回一个数组
			//session.setAttribute("al", al);               //把数组保存起来
			//session.setAttribute("login",l);              //将获取的对象保存在session中
			session.setAttribute("my_uid",l.getAccount());
			denglu=0;
			session.setAttribute("denglu",denglu);   
			response.sendRedirect("IndexServlet");            //验证成功跳转到 main.jsp
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

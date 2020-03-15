package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.UserDao;
import java.io.PrintWriter;

/**
 * Servlet implementation class UserEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //读取请求参数值
        int role = Integer.parseInt(request.getParameter("role"));
		String account = request.getParameter("account");
        
		/*
        String name = null;
        if(request.getParameter("name")!=null&&(!request.getParameter("name").equals("null"))) {
        	name=request.getParameter("name");
        }
        */
        String tel=request.getParameter("tel");
        
        UserDao aaaa = new UserDao();     	           
        
        if(aaaa.user_edit( account, role, tel)){
            response.sendRedirect("user_success.jsp");           
		}else {
            response.sendRedirect("user_fail.jsp");           
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

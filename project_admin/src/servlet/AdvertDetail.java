package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Advert;
import bean.AdvertDao;

/**
 * Servlet implementation class AdvertDetail
 */
@WebServlet("/AdvertDetail")
public class AdvertDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertDetail() {
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
		// 查询所有商品信息
		Advert advdetail = dao.findAdvertDtail(advid);
		// 将list放置到request之中
		request.setAttribute("advertdetail", advdetail);
		// 转发到product_list.jsp页面
		request.getRequestDispatcher("advert_detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

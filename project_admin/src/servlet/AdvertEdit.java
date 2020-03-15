package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AdvertDao;

/**
 * Servlet implementation class AdvertEdit
 */
@WebServlet("/AdvertEdit")
public class AdvertEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertEdit() {
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

		int advid = Integer.parseInt(request.getParameter("advid"));
        String adtitle = request.getParameter("adtitle");
        String adcontent = request.getParameter("adcontent");

		AdvertDao dao = new AdvertDao();
		if(dao.advert_edit(advid,adtitle,adcontent)){
            response.sendRedirect("advert_success.jsp");           
		}else {
            response.sendRedirect("advert_fail.jsp");           
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

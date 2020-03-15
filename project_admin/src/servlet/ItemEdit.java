package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemDao;

/**
 * Servlet implementation class ItemEdit
 */
@WebServlet("/ItemEdit")
public class ItemEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemEdit() {
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
        String ino = request.getParameter("ino");
        int sswitch = Integer.parseInt(request.getParameter("sswitch"));
        
        String starttime1=null;
        String endtime1=null;
        String starttime = null;
        String endtime = null;
        if((request.getParameter("starttime")!=null)&&(!request.getParameter("starttime").equals("null"))
        		&&(!request.getParameter("starttime").equals(""))) {
        	starttime=request.getParameter("starttime");
        	System.out.println("starttime:"+starttime);
        	if(starttime.charAt(2)=='/') {
                starttime1=starttime.substring(6, 10)+"/"+starttime.substring(0, 5);
            }else if(starttime.charAt(4)=='-') {
            	starttime1=starttime;
            }
        }
        if((request.getParameter("endtime")!=null)&&(!request.getParameter("endtime").equals("null"))
        		&&(!request.getParameter("endtime").equals(""))) {
        	endtime=request.getParameter("endtime");
        	 if(endtime.charAt(2)=='/') {
             	endtime1=endtime.substring(6, 10)+"/"+endtime.substring(0, 5);
             }else if(endtime.charAt(4)=='-') {
             	endtime1=endtime;
             }
        }
        
        ItemDao aaaa = new ItemDao();     	           
        
        if(aaaa.item_edit( ino, starttime1, endtime1,sswitch)){
			response.sendRedirect("ItemServlet");//?app_id=+app_id
		}else {
			out.println("<style>body{background-color:#C4E1FF}</style><br><br><br><br><br><br><h1 align='center'>信息有误，修改失败！<h1>");
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

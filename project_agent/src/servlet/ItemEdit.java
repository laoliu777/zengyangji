package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
		int oid = Integer.parseInt(request.getParameter("oid"));
		//int ostate = Integer.parseInt(request.getParameter("ostate"));		
        String kdcompany = request.getParameter("kdcompany");
        String kdno = request.getParameter("kdno");
        //String vid = request.getParameter("vid");
        //int renttime = Integer.parseInt(request.getParameter("renttime"));
        //int sswitch = Integer.parseInt(request.getParameter("sswitch"));
              

        /*
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
        	SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd");
        	Date date1;
			try {
				date1 = time.parse(starttime1);
				Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(date1);
	        	calendar.add(Calendar.DATE,renttime);
	        	Date date2=calendar.getTime();
	    		endtime1= new SimpleDateFormat("yyyy-MM-dd").format(date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        */
       
        
        ItemDao aaaa = new ItemDao();     	           
        
        if(aaaa.item_kd_add( oid, kdcompany, kdno)){
			response.sendRedirect("item_success.jsp");//?app_id=+app_id
		}else {
			response.sendRedirect("item_fail.jsp");//?app_id=+app_id
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

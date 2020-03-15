package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ConnectDao;

/**
 * Servlet implementation class ItemAdd
 */
@WebServlet("/ItemAdd")
public class ItemAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemAdd() {
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

        //��ȡ�������ֵ
        int oid = Integer.parseInt(request.getParameter("oid"));
        String imei = request.getParameter("imei1")+"-"
        		+request.getParameter("imei2")+"-"
                +request.getParameter("imei3")+"-"
                +request.getParameter("imei4");
        int renttime = Integer.parseInt(request.getParameter("renttime"));

        Calendar cal=Calendar.getInstance();
		Date time=cal.getTime();
		String starttime= new SimpleDateFormat("yyyy-MM-dd").format(time);
		Calendar cal2=Calendar.getInstance();
		cal2.add(Calendar.DATE,renttime);
		Date time2=cal2.getTime();
		String endtime= new SimpleDateFormat("yyyy-MM-dd").format(time2);
        
        //JDBC
        Connection conn = null;
        PreparedStatement stat = null,stat1 = null,ps= null;
        try {
        	conn=new ConnectDao().getConnection();
        	
        	//�ҳ���ǰ���豸�ţ����ı��+1��
        	/*
    		ps = conn.prepareStatement("select max(ino) "
    				+ "from item where type =?");
    			// ��SQL����еĵ�1��������ֵ
    		ps.setString(1, type);
    			// ִ�в�ѯ����
    		ResultSet rs3 = ps.executeQuery();
    			// �������ƶ������ж��Ƿ���Ч
    		while (rs3.next()) {
    			ino=rs3.getInt(1)+1;
    		}
    		*/
        	
        	stat = conn.prepareStatement("update iorder set vid=? and ostate=2 "
            		+ "where oid=?");
            stat.setString(1, imei);
            stat.setInt(2, oid);
            stat.executeUpdate();            
	
        	
            stat1 = conn.prepareStatement("INSERT INTO item(vid,starttime,endtime) "
            		+ "VALUES(?,?,?);");
            stat1.setString(1, imei);   
            stat1.setString(2, starttime);
            stat1.setString(3, endtime);
           
            stat1.executeUpdate();            

            //�ض���Ա���б�
            response.sendRedirect("ItemServlet");           
        } catch (Exception e) {
            e.printStackTrace();
            /*
             * �ж��쳣�Ƿ��ܹ��ָ���������ܹ��ָ�
             * (����,���ݿ������ͣ,�����жϵȵ�,һ���֮Ϊϵͳ�쳣),��ʾ�û��Ժ����Լ��ɡ�
             * ����ܹ��ָ�(Ӧ���쳣)��Ҫ�����ָ���
             */
            out.println("<style>body{background-color:#C4E1FF}</style><br><h1 align='center'>��Ϣ�������ʧ�ܣ�<h1>");
        }finally{
            if(stat!=null){
                try{
                    stat.close();
                    stat1.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
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

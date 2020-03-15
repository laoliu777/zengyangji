package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AdvertDao;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AdvertAdd1
 */
@WebServlet("/AdvertAdd1")
public class AdvertAdd1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertAdd1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		//String adtitle = request.getParameter("adtitle");
        //String adcontent = request.getParameter("adcontent");
        
        AdvertDao taskdao=new AdvertDao();
        
        //if(taskdao.advert_add(adtitle,adcontent)){  
        
    	int result=0;
		try {
			result = taskdao.get_result()+1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	if (ServletFileUpload.isMultipartContent(request)) {
     
    	    try {
    		// 1. ����DiskFileItemFactory�������û�������С����ʱ�ļ�Ŀ¼
    		DiskFileItemFactory factory = new DiskFileItemFactory();
    		// System.out.println(System.getProperty("java.io.tmpdir"));//Ĭ����ʱ�ļ���
     
    		// 2. ����ServletFileUpload���󣬲������ϴ��ļ��Ĵ�С���ơ�
    		ServletFileUpload sfu = new ServletFileUpload(factory);
    		sfu.setSizeMax(10 * 1024 * 1024);// ��byteΪ��λ ���ܳ���10M 1024byte =
    						 // 1kb 1024kb=1M 1024M = 1G
    		sfu.setHeaderEncoding("utf-8");
     
    		// 3.
    		// ����ServletFileUpload.parseRequest��������request���󣬵õ�һ�������������ϴ����ݵ�List����
    		@SuppressWarnings("unchecked")
    		List<FileItem> fileItemList = sfu.parseRequest(request);
    		Iterator<FileItem> fileItems = fileItemList.iterator();
     
    		// 4. ����list��ÿ����һ��FileItem���󣬵�����isFormField�����ж��Ƿ����ϴ��ļ�
    		int i=0;
    		String aaa[]=new String[2];
    		while (fileItems.hasNext()) {
    		    FileItem fileItem = fileItems.next();
    		    
    		    
    		    // ��ͨ��Ԫ��
    		    if (fileItem.isFormField()) {
	    			String name = fileItem.getFieldName();// name����ֵ
	    			String value = fileItem.getString("utf-8");// name��Ӧ��valueֵ
	    			aaa[i]=value;
	    			i++;
	    			System.out.println(name + " = " + value);
    		    }
    		    // <input type="file">���ϴ��ļ���Ԫ��
    		    else {
	    			String fileName = fileItem.getName();// �ļ�����
	    			System.out.println("ԭ�ļ�����" + fileName);// Koala.jpg
	     
	    			String suffix = fileName.substring(fileName.lastIndexOf('.'));
	    			System.out.println("��չ����" + suffix);// .jpg
	     
	    			// ���ļ�����Ψһ��
	    			String newFileName = new Date().getTime() + suffix;
	    			System.out.println("���ļ�����" + newFileName);// image\1478509873038.jpg
	     
	    			// 5. ����FileItem��write()������д���ļ�
	    			File file = new File("C:/data/pic/" + newFileName);
	    			//File file = new File("http://129.204.152.137:8080/upload/" + newFileName);
	    			System.out.println(file.getAbsolutePath());
	    			fileItem.write(file);
	    			String mynewfilepath = "http://129.204.152.137:8080/upload/" + newFileName;
	     
	    			// 6. ����FileItem��delete()������ɾ����ʱ�ļ�
	    			fileItem.delete();
	     
	    			/*
	    			 * �洢�����ݿ�ʱע�� 1.����Դ�ļ����� Koala.jpg 2.�������·��
	    			 * image/1478509873038.jpg
	    			 * 
	    			 */
	    			//if(taskdao.advert_add2(aaa[0],aaa[1],file.getAbsolutePath())){        	
	    			if(taskdao.advert_add2(aaa[0],aaa[1],mynewfilepath)){        	
	        			response.sendRedirect("advert_success.jsp");       		     			
	    			}else {
	    				response.sendRedirect("advert_fail.jsp");
	    			}
     
    		    }
    		}
     
    	    } catch (FileUploadException e) {
    		e.printStackTrace();
    	    } catch (Exception e) {
    		e.printStackTrace();
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

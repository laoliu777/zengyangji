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
    		// 1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
    		DiskFileItemFactory factory = new DiskFileItemFactory();
    		// System.out.println(System.getProperty("java.io.tmpdir"));//默认临时文件夹
     
    		// 2. 创建ServletFileUpload对象，并设置上传文件的大小限制。
    		ServletFileUpload sfu = new ServletFileUpload(factory);
    		sfu.setSizeMax(10 * 1024 * 1024);// 以byte为单位 不能超过10M 1024byte =
    						 // 1kb 1024kb=1M 1024M = 1G
    		sfu.setHeaderEncoding("utf-8");
     
    		// 3.
    		// 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
    		@SuppressWarnings("unchecked")
    		List<FileItem> fileItemList = sfu.parseRequest(request);
    		Iterator<FileItem> fileItems = fileItemList.iterator();
     
    		// 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
    		int i=0;
    		String aaa[]=new String[2];
    		while (fileItems.hasNext()) {
    		    FileItem fileItem = fileItems.next();
    		    
    		    
    		    // 普通表单元素
    		    if (fileItem.isFormField()) {
	    			String name = fileItem.getFieldName();// name属性值
	    			String value = fileItem.getString("utf-8");// name对应的value值
	    			aaa[i]=value;
	    			i++;
	    			System.out.println(name + " = " + value);
    		    }
    		    // <input type="file">的上传文件的元素
    		    else {
	    			String fileName = fileItem.getName();// 文件名称
	    			System.out.println("原文件名：" + fileName);// Koala.jpg
	     
	    			String suffix = fileName.substring(fileName.lastIndexOf('.'));
	    			System.out.println("扩展名：" + suffix);// .jpg
	     
	    			// 新文件名（唯一）
	    			String newFileName = new Date().getTime() + suffix;
	    			System.out.println("新文件名：" + newFileName);// image\1478509873038.jpg
	     
	    			// 5. 调用FileItem的write()方法，写入文件
	    			File file = new File("C:/data/pic/" + newFileName);
	    			//File file = new File("http://129.204.152.137:8080/upload/" + newFileName);
	    			System.out.println(file.getAbsolutePath());
	    			fileItem.write(file);
	    			String mynewfilepath = "http://129.204.152.137:8080/upload/" + newFileName;
	     
	    			// 6. 调用FileItem的delete()方法，删除临时文件
	    			fileItem.delete();
	     
	    			/*
	    			 * 存储到数据库时注意 1.保存源文件名称 Koala.jpg 2.保存相对路径
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

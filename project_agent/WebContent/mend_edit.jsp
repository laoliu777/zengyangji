<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@page import="java.util.List"%>
<%@page import="bean.Mend"%> 
<%@page import="bean.MendDao"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="Bootstrap Metro Dashboard">
<meta name="author" content="">
<meta name="keyword" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>报修报换</title>
<!-- start: CSS -->
	<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<link id="base-style" href="css/style.css" rel="stylesheet">
	<link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
	<!-- end: CSS -->
	<!-- start: Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico">
	<!-- end: Favicon -->
		<style type="text/css">
			body { background: url(img/bg-login.jpg) !important; }
		</style>
	<!-- start: JavaScript-->
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="js/jquery-migrate-1.0.0.min.js"></script>	
		<script src="js/jquery-ui-1.10.0.custom.min.js"></script>	
		<script src="js/jquery.ui.touch-punch.js"></script>	
		<script src="js/modernizr.js"></script>	
		<script src="js/bootstrap.min.js"></script>	
		<script src="js/jquery.cookie.js"></script>
		<script src='js/fullcalendar.min.js'></script>	
		<script src='js/jquery.dataTables.min.js'></script>
		<script src="js/excanvas.js"></script>
		<script src="js/jquery.flot.js"></script>
		<script src="js/jquery.flot.pie.js"></script>
		<script src="js/jquery.flot.stack.js"></script>
		<script src="js/jquery.flot.resize.min.js"></script>	
		<script src="js/jquery.chosen.min.js"></script>	
		<script src="js/jquery.uniform.min.js"></script>		
		<script src="js/jquery.cleditor.min.js"></script>	
		<script src="js/jquery.noty.js"></script>	
		<script src="js/jquery.elfinder.min.js"></script>	
		<script src="js/jquery.raty.min.js"></script>	
		<script src="js/jquery.iphone.toggle.js"></script>	
		<script src="js/jquery.uploadify-3.1.min.js"></script>	
		<script src="js/jquery.gritter.min.js"></script>
		<script src="js/jquery.imagesloaded.js"></script>	
		<script src="js/jquery.masonry.min.js"></script>	
		<script src="js/jquery.knob.modified.js"></script>	
		<script src="js/jquery.sparkline.min.js"></script>	
		<script src="js/counter.js"></script>	
		<script src="js/retina.js"></script>
		<script src="js/custom.js"></script>
	<!-- end: JavaScript-->
</head>
<body>
<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="#"><span>代理商后台管理系统</span></a>
								
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">					
						<!-- start: User Dropdown -->
						<li class="dropdown">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white user"></i> 
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title">
 									<span>账户设置</span>
								</li>
								<li><a href="#"><i class="halflings-icon user"></i><%=session.getAttribute("my_uid") %></a></li>
								<li><a href="login.jsp"><i class="halflings-icon off"></i> 注销</a></li>
							</ul>
						</li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
				
			</div>
		</div>
	</div>
	<!-- start: Header -->
	
		<div class="container-fluid-full">
		<div class="row-fluid">
				
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a href="IndexServlet"><i class="icon-bar-chart"></i><span class="hidden-tablet"> 首页</span></a></li>	
<!-- 
						<li>
							<a class="dropmenu" href="#"><i class="icon-envelope"></i><span class="hidden-tablet"> 订单管理</span><span class="label label-important"> 2 </span></a>
							<ul>
								<li><a class="submenu" href="ItemServlet"><i class="icon-file-alt"></i><span class="hidden-tablet"> 设备查询</span></a></li>
								<li><a class="submenu" href="item_add.jsp"><i class="icon-file-alt"></i><span class="hidden-tablet"> 设备添加</span></a></li>
							</ul>	
						</li>
						 -->
						<li><a href="ItemServlet"><i class="icon-tasks"></i><span class="hidden-tablet"> 订单管理</span></a></li>						 
						<li><a href="MendServlet"><i class="icon-tasks"></i><span class="hidden-tablet"> 报修报换</span></a></li>
					</ul>
				</div>
			</div>
			<!-- end: Main Menu -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="#">首页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">报修报换处理</a></li>
			</ul>

	
				
	<a class="btn btn-info" href="#" onclick="javascript:history.back(-1);">返回用户列表</a><br><br>			
	<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>报修报换处理</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;*为可修改项
					<div class="box-content">
					<%						
						Mend p = (Mend) request.getAttribute("menddetail");
						int rmendid=p.getMendid();
						SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String mmtime=df.format(p.getMtime());
						SimpleDateFormat df1=new SimpleDateFormat("yyyyMMdd");
						String mmid1=df1.format(p.getMtime());
						String mmid=mmid1+String.format("%03d",rmendid);
					%>	
					<form  action="MendEdit" method="post" onsubmit="return check(this);">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">						
							<tbody>
							<tr>
								<td class="center">报修号</td>
								<td class="center">
								<input type = "hidden" style="BACKGROUND-COLOR:transparent;" name = "mendid" value="<%=p.getMendid()%>" >
		            			<%=mmid %>
								</td>							
							</tr>
							<tr>
								<td>设备编号</td>
								<td class="center">
								<%=p.getVid()%>
								</td>										
							</tr>			
							<tr>
								<td>报修时间</td>
								<td class="center">
								<%=mmtime%>
								</td>										
							</tr>			
							<tr>
								<td>问题</td>
								<td class="center">
 								<%=p.getProblem()%>
 								</td>										
							</tr>		
							<tr>
								<td>*处理情况</td>
								<td class="center">
								<select id="selectError3" name="mstate">
								 	<% if(p.getMstate()==0){ %>
									<option value="0"  selected>未处理</option>
									<option value="1" >已处理</option>
			 						<%}else{%> 
									<option value="0" >未处理</option>
									<option value="1" selected>已处理</option>					            
									<% }  %>								 
					            </select>								
					            </td>										
							</tr>					
						  </tbody>
					  </table>            
					  <div class="form-actions">
								<button type="submit" class="btn btn-primary" onclick="return confirm ('确认修改?')">提交</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn" onclick = "return confirm ('确认取消?'),location = 'MendServlet'">取消</button>
					  </div>
					  </form>						
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			
       

	</div><!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>Settings</h3>
		</div>
		<div class="modal-body">
			<p>Here settings can be configured...</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a>
			<a href="#" class="btn btn-primary">Save changes</a>
		</div>
	</div>
	

	
	<div class="clearfix"></div>
	
	<footer>

		<p>
			<span style="text-align:left;float:left">代理商后台管理系统</span>
			
		</p>

	</footer>
</body>
</html>
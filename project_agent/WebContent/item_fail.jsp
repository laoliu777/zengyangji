<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="bean.Item"%>
<%@page import="bean.ItemDao"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="Bootstrap Metro Dashboard">
<meta name="author" content="">
<meta name="keyword" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>操作失败</title>
<!-- start: CSS -->
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
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
				<li><a href="#">订单管理</a></li>
			</ul>

			<div align="center" style="margin-top:20%;">
			<font size="5rem">信息有误，提交失败！</font></span><br><br>
		    <div class="progress progress-striped active" style="width:30rem;height:3rem;"">
			<div class="progress-bar progress-bar-success" role="progressbar"
				 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
				 style="width: 50%;">
				<span class="sr-only">未完成</span>
			</div>
		    </div>
		    <button class="btn btn-primary" style="height:4rem;"onclick = "javascrtpt:window.location.href='ItemServlet'">
		    <span class="glyphicon glyphicon-hand-left"><font size="4rem">&nbsp;返回订单界面&nbsp;&nbsp;</font></span></button>
			<br><br><br><br><br><br><br><br><br><br><br>			
			
			</div>	
				

			
       

	</div><!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->	
	

	
	<div class="clearfix"></div>
	
	<footer>

		<p>
			<span style="text-align:left;float:left">代理商后台管理系统</span>
			
		</p>

	</footer>
</body>
</html>
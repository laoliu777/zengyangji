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
<title>订单查看</title>
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
				<li><a href="#">设备详情</a></li>
			</ul>

	
			<a class="btn btn-info" href="#" onclick="javascript:history.back(-1);">返回设备列表</a><br><br>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>设备详情</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">						
							<tbody>
							 <%						
								Item p = (Item) request.getAttribute("itemdetail");
								SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String ordtime=df.format(p.getOrdertime());
							%>		
							<tr>
								<td class="center">下单时间</td>
								<td class="center"><%=ordtime%></td>							
							</tr>
							<tr>
								<td class="center">会员</td>
								<td class="center"><%=p.getMember()%></td>							
							</tr>
							<tr>
								<td class="center">设备类型</td>
								<td class="center"><%=p.getType()%></td>							
							</tr>
							<tr>
								<td class="center">品牌</td>
								<td class="center"><%=p.getBrand()%></td>							
							</tr>
							<tr>
								<td class="center">订单状态</td>
								<td class="center">
								<% if (p.getOrderstate()==0){	 %>
									<span class="label label-important">待发货</span>
								<%	} else if (p.getOrderstate()==1) {%>
					           		<span class="label label-warning">待绑定</span>
					           	<%	}  else if (p.getOrderstate()==2) {%>
					           		<span class="label label-success">使用中</span>
					           	<%	}  else {%>
					           		<span class="label">已结束</span>
					           	<%	}  %>
								</td>
							</tr>
							<% if (p.getOrderstate()>0){	 %>
							<tr>
								<td class="center">快递公司</td>
								<td class="center"><%=p.getKdcompany()%></td>							
							</tr>
							<tr>
								<td class="center">快递号</td>
								<td class="center"><%=p.getKdno()%></td>							
							</tr>
							<%	} %>		
							<% if (p.getOrderstate()>1){	 %>
							<tr>
								<td class="center">设备编号</td>
								<td class="center"><%=p.getIno()%></td>							
							</tr>
							<%	} %>		
							<% if (p.getBuystate()==0){	 %>		
							<tr>
								<td class="center">租售情况</td>
								<td class="center">租赁</td>							
							</tr>				
							<tr>
								<td class="center">租赁时长</td>
								<td class="center"><%=p.getRenttime()%></td>							
							</tr>		
							<tr>
								<td class="center">总价</td>
								<td class="center"><%=p.getZongjia()%>元</td>							
							</tr>
							<tr>
								<td class="center">租金</td>
								<td class="center"><%=p.getZujing()%>元/天</td>							
							</tr>	
							<tr>
								<td class="center">押金</td>
								<td class="center"><%=p.getYajing()%>元</td>							
							</tr>																									
							<% if (p.getOrderstate()>1&&p.getOrderstate()<3){	 %>							
							<tr>								
								<td class="center">起租时间</td>
								<td class="center"><%=p.getStarttime()%></td>		
							</tr>				
							<tr>								
								<td class="center">结束时间</td>
								<td class="center"><%=p.getEndtime()%></td>		
							</tr>		
							<tr>
								<td class="center">开关情况</td>
								<td class="center">
								<% if (p.getSswitch()==0){	 %>
									<span class="label">关闭</span>
								<%	} else {%>
					           		<span class="label label-success">开启</span>
					           	<%	}  %>	
					           	</td>						
					        </tr>		
					        <tr>
								<td class="center">当前温度</td>
								<td class="center">26</td>							
							</tr>						
							<tr>
								<td class="center">当前定位</td>
								<td class="center">湖南省长沙市湖南大学</td>							
							</tr>																												
							<% }  %>						
							<%	} else{%>
							<tr>
								<td class="center">租售情况</td>
								<td class="center">购买</td>							
							</tr>				
							<tr>
								<td class="center">售价</td>
								<td class="center"><%=p.getZongjia()%></td>							
							</tr>
							<% if (p.getOrderstate()>1&&p.getOrderstate()<3){	 %>							
							<tr>
								<td class="center">开关情况</td>
								<td class="center">
								<% if (p.getSswitch()==0){	 %>
									<span class="label">关闭</span>
								<%	} else {%>
					           		<span class="label label-success">开启</span>
					           	<%	}  %>	
					           	</td>						
					        </tr>		
					        <tr>
								<td class="center">当前温度</td>
								<td class="center">26</td>							
							</tr>						
							<tr>
								<td class="center">当前定位</td>
								<td class="center">湖南省长沙市湖南大学</td>							
							</tr>																												
							<% }  %>						
							<%	} %>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
				
	
	
		</div>	<!-- end: Content -->
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
	
	<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<ul class="list-inline item-details">
				<li><a href="http://sc.chinaz.com">Admin templates</a></li>
				<li><a href="http://sc.chinaz.com">Bootstrap themes</a></li>
			</ul>
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
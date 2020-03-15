<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="bean.User"%>
<%@page import="bean.UserDao"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="Bootstrap Metro Dashboard">
<meta name="author" content="">
<meta name="keyword" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<title>首页</title>
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
				<a class="brand" href="#"><span>管理员后台管理系统</span></a>
								
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
						<li>
							<a class="dropmenu" href="#"><i class="icon-envelope"></i><span class="hidden-tablet"> 用户管理</span><span class="label label-important"> 2 </span></a>
							<ul>
								<li><a class="submenu" href="UserServlet"><i class="icon-file-alt"></i><span class="hidden-tablet"> 用户查询</span></a></li>
								<li><a class="submenu" href="user_add.jsp"><i class="icon-file-alt"></i><span class="hidden-tablet"> 用户添加</span></a></li>
							</ul>	
						</li>
						<li><a href="OrderServlet"><i class="icon-align-justify"></i><span class="hidden-tablet"> 订单管理</span></a></li>	
						<li>
							<a class="dropmenu" href="#"><i class="icon-tasks"></i><span class="hidden-tablet"> 设备管理</span><span class="label label-important"> 2 </span></a>
							<ul>
								<li><a class="submenu" href="ItemServlet"><i class="icon-file-alt"></i><span class="hidden-tablet"> 设备查询</span></a></li>
								<li><a class="submenu" href="item_add.jsp"><i class="icon-file-alt"></i><span class="hidden-tablet"> 设备添加</span></a></li>
							</ul>	
						</li>
						<li>
							<a class="dropmenu" href="#"><i class="icon-folder-open"></i><span class="hidden-tablet"> 型号管理</span><span class="label label-important"> 2 </span></a>
							<ul>
								<li><a class="submenu" href="InfoServlet"><i class="icon-file-alt"></i><span class="hidden-tablet"> 型号查询</span></a></li>
								<li><a class="submenu" href="iteminfo_add.jsp"><i class="icon-file-alt"></i><span class="hidden-tablet"> 型号添加</span></a></li>
							</ul>	
						</li>
						<li>
							<a class="dropmenu" href="#"><i class="icon-eye-open"></i><span class="hidden-tablet"> 广告管理</span><span class="label label-important"> 2 </span></a>
							<ul>
								<li><a class="submenu" href="AdvertServlet"><i class="icon-file-alt"></i><span class="hidden-tablet"> 广告查询</span></a></li>
								<li><a class="submenu" href="advert_add.jsp"><i class="icon-file-alt"></i><span class="hidden-tablet"> 广告添加</span></a></li>
							</ul>	
						</li>
						<li><a href="MendServlet"><i class="icon-edit"></i><span class="hidden-tablet"> 报修管理</span></a></li>
					</ul>
				</div>
			</div>
			<!-- end: Main Menu -->
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="#">首页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">我的信息</a></li>
			</ul>

	
				
				
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>我的基本信息</h2>
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
							User p = (User) request.getAttribute("uu");							
						%>		
						  <tr>
								<td class="center">账号</td>
								<td class="center"><%=p.getAccount()%></td>							
							</tr>
							<tr>
								<td>角色</td>
								<td class="center">管理员</td>			
							</tr>
							<tr>
								<td>电话</td>
								<td class="center"><%=p.getTel()%></td>			
							</tr>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			
			<div class="row-fluid">
				
				<div class="box span12">
					<div class="box-header">
						<h2><i class="halflings-icon white th"></i><span class="break"></span>系统信息</h2>
					</div>
					<div class="box-content">
						<ul class="nav tab-menu nav-tabs" id="myTab">
							<li class="active"><a href="#info">使用说明</a></li>
							<li><a href="#custom">系统简介</a></li>
							<li><a href="#messages">功能一览</a></li>
						</ul>
						 
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane active" id="info">
								<p>
									平台管理员平台管理，可以进行参数设置、用户管理和数据字典管理，主要是设置整个系统中涉及的总体变量的设置管理，方便数据的维护。
									平台管理员可通过“登录日志”来查看登录后台管理系统的用户信息（平台管理员，代理商），包括用户名、账号、登录IP和登录时间等信息，可查看后台用户对在系统上所作的操作，一旦出现问题，方便问题跟踪定位。
									平台管理员可查询、管理移动端注册的会员，对后台系统中的代理商进行管理维护。也可以添加设备、管理维护设备、远程开启/关闭设备等。查询移动端会员租赁或购买设备的信息记录，会员租赁/购买的设备报修报换申请，账款查收记录，移动端会员的提现申请记录， 报修报换申请后增加收发设备的记录，到期设备提醒记录。
									平台管理员可根据广告的要求，设置广告的具体参数，也可以维护移动APP端系统的信息显示。
								</p>

							</div>
							<div class="tab-pane" id="custom">
								<p>
									本课题的任务及目的主要是为利用单片机技术、物联网技术和测控技术综合集成建立成本低廉、控制精度高的增氧机租赁与智能化远程控制系统,为大规模、高水平、高质量渔业养殖提供技术平台,促使由传统养殖技术到现代养殖技术的转变.系统的使用不但降低生产成本和劳动强度而且提高生产效率,不断加快水产养殖技术的实用化和商业化进程,同时对保护自然环境和节约能源也起到了积极的作用。
									</p>
									<p>基于这样便捷的系统，我需要完成一个自适应性强的稳定可靠的移动端，包括三部分：会员所使用的混合移动端，代理商所使用的H5服务端，平台管理员所使用的H5服务端，另外还有一个对应的微信小程序部分。总体来说，目的是完成一款搭配增氧机的智能客户端应用，通过软件对增氧机进行购买或者租赁，购买租赁实现在线支付，并且租赁到期可实现在线缴费；同时可以查看增氧机相关的新闻，对于产品信息可以更全面了解；也可通过APP对增氧机进行开关控制，同时可实现远程控制；还可以查看近期的消费信息，以及预约相关服务等等。	
								</p>
							</div>
							<div class="tab-pane" id="messages">
								<p>
									（1）会员管理页面分为天气预报和增氧机列表。天气预报展示当前定位区域的天气预报和未来5天的天气预报。增氧机列表展示当前登录会员所租赁/购买的增氧机设备列表，列表中记录了每台设备的设备编号和当前状态，状态有三种：开启、关闭和异常告警。会员可通过开/关按钮来远程控制设备的开关状态。
									设备租购，会员可根据自己的需要在最新型号页面查找自己需要的增氧机，确认租赁/购买的设备后，可进入设备详情页面点击购买弹出租购提示框进行租赁或购买。
									会员可查询登录会员的个人信息，报修报换信息，钱包、余额、账单明细，过期提醒消息，已易购设备管理、设备收发记录，收货地址管理维护，使用帮助查看和关于系统平台的介绍，在该平台上租赁/购买的设备信息等。
 
								</p>				
								<p>
									（2）代理商设备信息管理，查看自己代理的所有设备信息，并通过开启/关闭按钮远程控制设备，并可以修改设备租售状态等。
									可查看移动端会员租赁或购买代理商设备的信息，以及会员租赁/购买的设备的报修报换申请，在收发信息管理模块增加收发设备的记录，到期设备提醒记录。
								</p>
								<p>
									（3）平台管理员平台管理，可以进行参数设置、用户管理和数据字典管理，主要是设置整个系统中涉及的总体变量的设置管理，方便数据的维护。
									平台管理员可通过“登录日志”来查看登录后台管理系统的用户信息（平台管理员，代理商），包括用户名、账号、登录IP和登录时间等信息，可查看后台用户对在系统上所作的操作，一旦出现问题，方便问题跟踪定位。
									平台管理员可查询、管理移动端注册的会员，对后台系统中的代理商进行管理维护。也可以添加设备、管理维护设备、远程开启/关闭设备等。查询移动端会员租赁或购买设备的信息记录，会员租赁/购买的设备报修报换申请，账款查收记录，移动端会员的提现申请记录， 报修报换申请后增加收发设备的记录，到期设备提醒记录。
									平台管理员可根据广告的要求，设置广告的具体参数，也可以维护移动APP端系统的信息显示。

								</p>
							</div>
						</div>
					</div>
				</div><!--/span-->
			
			</div><!--/row-->			
			
			<!--
			<div class="row-fluid">
				
				<div class="box span4" onTablet="span12" onDesktop="span4">
					<div class="box-header">
						<h2><i class="halflings-icon white calendar"></i><span class="break"></span>Calendar</h2>
					</div>
					<div class="box-content">
						<div id="main_calendar_phone"></div>
						<div class="clearfix"></div>
					</div>	
				</div>
			</div>-->
			
			<!--
			<div class="row-fluid sortable">
				<div class="box span12">
				  <div class="box-header" data-original-title>
					  <h2><i class="halflings-icon white calendar"></i><span class="break"></span>Calendar</h2>
				  </div>
				  <div class="box-content">
					<div id="external-events" class="span3 hidden-phone hidden-tablet">
						<h4>Draggable Events</h4>
						<div class="external-event badge">Default</div>
						<div class="external-event badge badge-success">Completed</div>
						<div class="external-event badge badge-warning">Warning</div>
						<div class="external-event badge badge-important">Important</div>
						<div class="external-event badge badge-info">Info</div>
						<div class="external-event badge badge-inverse">Other</div>
						<p>
						<label for="drop-remove"><input type="checkbox" id="drop-remove" /> remove after drop</label>
						</p>
						</div>

						<div id="calendar" class="span9"></div>

						<div class="clearfix"></div>
					</div>
				</div>
			</div><!--/row-->
       
	   
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white picture"></i><span class="break"></span>图片展示</h2>
						<div class="box-icon">
							<a href="#" id="toggle-fullscreen" class="hidden-phone hidden-tablet"><i class="halflings-icon white fullscreen"></i></a>
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div class="masonry-gallery">
														<div id="image-1" class="masonry-thumb">
								<a style="background:url(img/gallery/photo1.jpg)" title="Sample Image 1" href="img/gallery/photo1.jpg"><img class="grayscale" src="img/gallery/photo1.jpg" alt="Sample Image 1" height="300" width="250"></a>
							</div>
							<br>
														<div id="image-2" class="masonry-thumb">
								<a style="background:url(img/gallery/photo2.jpg)" title="Sample Image 2" href="img/gallery/photo2.jpg"><img class="grayscale" src="img/gallery/photo2.jpg" alt="Sample Image 2" height="300" width="250"></a>
							</div>
							<br>
														<div id="image-3" class="masonry-thumb">
								<a style="background:url(img/gallery/photo3.jpg)" title="Sample Image 3" href="img/gallery/photo3.jpg"><img class="grayscale" src="img/gallery/photo3.jpg" alt="Sample Image 3" height="300" width="250"></a>
							</div>
							<br>
														<div id="image-4" class="masonry-thumb">
								<a style="background:url(img/gallery/photo4.jpg)" title="Sample Image 4" href="img/gallery/photo4.jpg"><img class="grayscale" src="img/gallery/photo4.jpg" alt="Sample Image 4" height="300" width="250"></a>
							</div>
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
			<span style="text-align:left;float:left">管理员后台管理系统</span>
			
		</p>

	</footer>
</body>
</html>
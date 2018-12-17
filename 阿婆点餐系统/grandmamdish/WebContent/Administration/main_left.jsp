<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<style>
	*{
		margin: 0;
		padding: 0;
	}
	body{
		
	}
	.leftnav{
			width: 250px;
			height: 420px;
			background-color: #d2cb8b;
			border-top: 1px solid #ccc;
			margin: 20px auto;
	}
		.leftnav{
			line-height: 30px;
			text-align: center;
		}
		a{
			display: block;
			font-size: 12px;
			text-decoration: none;
			color: #000;
		}
		.leftnav h3{
			font-size: 12px;
			color: #000;
		}
	</style>
</head>
<body>
	<div class="leftnav">
		<h3>菜单管理</h3>
		<a href="/grandmamdish/Administration/main_right_menusadd.jsp" target="content" title="添加新菜单">添加新菜单</a>
		<a href="/grandmamdish/Administration/main_right_menusinfo.jsp" target="content" >菜单信息列表</a>
		<h3>销售订单管理</h3>
		<a href="/grandmamdish/Administration/order.jsp" target="content" >销售订单信息列表</a>
		<a href="/grandmamdish/Administration/order_search.jsp" target="content" title="添加新菜单">销售订单查询</a>
		<a href="/grandmamdish/Administration/order_statistic.jsp" target="content" >本日销售额统计</a>
		<h3>公告管理</h3>
		<a href="/grandmamdish/Administration/notice.jsp" target="content" title="添加新菜单">公告信息列表</a>
		<a href="/grandmamdish/Administration/notice_add.jsp" target="content" title="添加新菜单">添加公告</a>
		<h3>菜品类型管理</h3>
		<a href="/grandmamdish/Administration/type.jsp" target="content" title="添加新菜单">菜品信息列表</a>
		<a href="/grandmamdish/Administration/type_add.jsp" target="content" title="添加新菜单">添加菜品类型</a>
		<h5><a href="/grandmamdish/Administration/main_right_adminupdate.jsp" target="content">系统用户管理</a></h5>
		
	</div>
</body>
</html>
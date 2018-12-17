<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	 background: url(images/houtai.jpg) no-repeat 10px center ; 
	}
	.all-top{
		width:100%
		height: 100%;
	}
	.nav{
		 float: right; 
		font-size: 16px;
	}
	.nav a{
		text-decoration: none;
	}
	.top{
			margin: 20px 500px 0;
			height: 40px;
			line-height: 40px;
			/* background-color: #d2cb8b; */
			/* border-bottom: 1px solid #ccc; */
			text-align: center;
			letter-spacing: 5px;
		}
</style>
</head>
<body>
	<div class="all-top">
		<!--导航开始  -->
	<div class="nav">
		<span>欢迎您管理员：${admin.adminName }&emsp;|</span> 
  		<a href="/grandmamdish/Administration/indext.jsp">退出&emsp;&emsp;&emsp;</a>
	</div>
	<!--导航结束  -->
	<!--顶部开始  -->
  <div class="top"> 	
  	<h3>阿婆私房菜后台管理系统 </h3>
  </div>
  <!--顶部结束  -->
	</div>
	
</body>
</html>
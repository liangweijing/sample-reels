<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function flush(){
	/*刷新了所有的frame（frames得到的是一个数组），但是解决了top.jsp中user属性的问题，可是还是解决不了无法显示middle.jsp  */
	window.parent.frames.location.reload();
}
</script>
<style>
	*{
		margin: 0;
		padding: 0;
	}
	body{
	 background: url(../img/houtai.jpg) no-repeat 10px center ; 
	}
	.all{
		width: 100%;
		height: 100%;
	}
	.all-t{
		 float: right; 
		font-size: 16px;
		
	}
	.all-t a{
		text-decoration: none;
		padding: 0 20px;
	}
	.all-m{
			height: 48px;
			
			text-align: center;
			letter-spacing: 5px;
			positon: relative;
		}
		.all-m h3{
			width: 150px;
			margin: 0 auto;
		}
		.all-m p{
			position: absolute;
			top: 22%;
			left: 60%;
		}
		.all-b ul{
			width: 560px;
			overflow: hidden;
			margin: 20px auto;
		}
		.all-b li{
			width: 80px;
			height: 50px;
			line-height: 60px;
			list-style: none;
			float: left;
			background: url(../img/buttn.png) no-repeat -30px center;
			text-align: center;
			margin: 0 5px;
		}
		.all-b li a{
		text-decoration: none;
		text-align: center;
		}
		
</style>
</head>
<body>
	<div class="all">
		<!--导航开始  -->
	<div class="all-t">
		<a href="/grandmamdish/User/UserLogin.jsp" target="middle">会员登录</a> |
		<a href="/grandmamdish/User/UserRegist.jsp" target="middle">会员注册</a> |
  		<a href="/grandmamdish/ExistServlet?type=exist" target="middle" onclick="flush()">退出&emsp;&emsp;&emsp;</a>
  		
	</div>
	<!--导航结束  -->
	<!--顶部开始  -->
  <div class="all-m"> 	
  	<h3>阿婆私房菜 </h3>
  	<p>亲爱的${user.name}您好！欢迎光临！</p>
  </div>
  <!--顶部结束  -->
  <!-- 底部开始 -->
  	<div class="all-b">
  		<ul>
  			<li><a href="/grandmamdish/User/index.jsp">首页</a></li>
  			<li><a href="/grandmamdish/User/order.jsp"  target="middle">我的订单</a></li>
  			<li><a href="/grandmamdish/User/center.jsp" target="middle">用户中心</a></li>
  			<li><a href="/grandmamdish/User/carry.jsp" target="middle">配送说明</a></li>
  			<li><a href="our.jsp" target="middle">关于我们</a></li> <!-- 在同一个文件夹 -->
  			
  		</ul>
  		
  		
  	</div>
  
  <!--底部结束  -->
	</div>
	
</body>
</html>
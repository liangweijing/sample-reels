<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%String path=request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <title>阿婆私房菜</title> 
 <link rel="shortcut icon" href="favicon.ico"  type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Administration/CSS/houtai.css" />
<style>
	.middle{
			width: 500px;
			margin: 150px 800px;

		}
		table{
			line-height: 40px;
		}
		input{
			height: 25px;
		}
</style>
<body>
 <script type="text/javascript">
if (window.top!=window.self){
	window.top.location.href=location.href;
}
</script> 
<script>
	function login(){
		document.form2.adminName.focus();
	}
	window.onload = login;
</script>
</head>
<body>
	<div class="top">
		<h4 style="letter-spacing: 5px;">阿婆私房菜后台管理系统</h4>
	</div>
	<div class="middle">
		<form name="form2" action="/grandmamdish/AdminLoginServlet?methods=login" method="post">
			<table align="center" width="400px" >
				<tr >
					<td colspan="2" style="color: blue;">登陆网上订餐后台管理</td>
					
				</tr>
				<tr>
					<td style="width: 100px;">管理员：</td>
					<td><input type="text" name="adminName"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="adminpwd"></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="submit" value="登录">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type="reset" value="取消"></td>
				</tr>
			</table>
			<c:if test="${status=='false' }">
					<p style="color: red;">	用户名或密码错误，请重新登录！</p>
			</c:if>
			<c:if test="${status==true }">
					<p style="color: red;">	修改成功，请重新登录！</p>
			</c:if>
		</form>
			
	</div>
	<div class="bottom">
		grandmamdish&emsp;&copy;&emsp;2018-09-05
	</div>
</body>
</html>
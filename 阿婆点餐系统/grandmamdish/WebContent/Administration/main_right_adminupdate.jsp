<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<style>
		table,tr,td{
			height: 25px;
			line-height: 25px;
			border: 1px solid #ccc;
			text-align: center;
			width: 100%;
		}
	</style>
 <script language="JavaScript">
		function check11()
			{
				
				if (document.form1.adminName.value == "" ) 
				{
					console.log("执行了");
					alert("用户名不能为空!");
					document.form1.name.focus();
					return false;
				}
				
			}
</script>
<script>
		function update(){
			document.form1.adminName.focus();//输入框获取焦点
		}
		window.onload=update;
	</script>

<script type="text/javascript">
 	$(function(){
 		$("#change").blur(function(){
 		var input=$("#change").val();
 		alert(input);
 		$.get("/grandmamdish/AdminLoginServlet?methods=logintest&changename="+input ,function(data){
 			console.log("zhiglixngl");
 			alert("123"+data);
 			if(data==1){
				$("#result").text("用户名已存在！");
			}
 			
			
		}); 
 	});
	
 	}); 
</script> 
</head>
<body>
	<div class="update">
	<form name="form1" action="/grandmamdish/AdminLoginServlet?methods=update" method="post" onSubmit="return check11()">
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td>更改管理员信息</td>
			</tr>
			<tr><td>管理员姓名<input id="change" type="text" name="adminName"><span id="result" style="color: red;"></span></td></tr>
			<tr><td>管理员密码<input type="text" name="adminpwd"></td></tr>
			<tr><td ><input type="submit" value="修改" "></td></tr>
			<!-- <tr><td class="output"></td></tr> -->
			
		</table>
		
	</form>
</div>
</body>
</html>
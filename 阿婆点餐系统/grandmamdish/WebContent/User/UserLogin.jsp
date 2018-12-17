<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script>
function testform(){
	if(document.form2.userName.value==""){
		alert("用户名不许为空！");
		document.form2.userName.focus();
		return false;
	}
}
</script>
</head>
<body>
	<form name="form2" action="/grandmamdish/UserServlet?methods=login" method="post" onSubmit="return testform()">
			<table align="center" width="400px" >
				<tr >
					<td colspan="2" style="color: blue;">阿婆私房菜,请先登录：</td>
					
				</tr>
				<tr>
					<td style="width: 100px;">登录账号：</td>
					<td><input type="text" name="userName"></td>
				</tr>
				<tr>
					<td>登录密码：</td>
					<td><input type="password" name="userpwd"></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">
					<input type="submit" value="登录">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<a href="/grandmamdish/User/UserRegist.jsp">注册</a>
					</td>
				</tr>
			</table>
			
		</form>
		<c:if test="${result=='false' }">
					<script>alert("用户名或密码错误，请重新登录！");document.form2.userName.focus();</script>
			</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form name="form1" action="/Angular/AngularServlet?jump=add" method="post">
		 <input type="text" name="username" >
		<input type="submit" value="提交">
		</form>
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="js/angular.min.js" ></script>
		<script type="text/javascript" src="js/httpdemo.js" ></script>
</body>
</html>
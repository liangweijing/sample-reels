<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
		.order{
			width: 600px;
			height: 350px;
			
			margin: 0 auto;
		}
	</style>
</head>
<body>
		<div class="order">
		<table  width="100%" border="1px" cellspacing="0">
			<tr>
				<th>订单编号</th>
				<th>菜品编号</th>
				<th>用户编号</th>
				<th>购买数量</th>
				<th>下单时间</th>
				<th>配送方式</th>
			</tr>
			<c:if test="${empty myOrder }">
				<%request.getRequestDispatcher("/UserServlet?methods=getOrder").forward(request, response); %>
			</c:if>
			<c:forEach items="${myOrder }" var="order">
				<tr>
					<td>${order.id }</td>
					<td>${order.menuid }</td>
					<td>${order.userid }</td>
					<td>${order.menunum }</td>
					<td>${order.times }</td>
					<td>${order.delivery }</td>
				</tr>
				
			</c:forEach>
			 
		</table>
	</div>
</body>
</html>
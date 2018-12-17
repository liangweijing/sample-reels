<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<style>
		.table-hd{
			border-bottom: 1px solid #ccc;
		}
		.bottom{
			width: 400px;
			margin: 0 auto;
		}
		
	</style>
</head>
<body>
	<table cellspacing="0" width="100%" border="1"  frame ="hsides"  rules="none" align="center">
		<tr align="center" valign="middle" height=""><td colspan="10" class="table-hd">菜品信息</td>
		</tr>
		<tr>
			<td >编号</td>
			<td>菜名</td>
			<td>类型</td>
			<td>原材料</td>
			<td>简介</td>
			<td>单价</td>
			<td>数量</td>
			<td>折后价</td>
			<td>优惠数量</td>
			<td>图片</td>
		</tr>
		<c:if test="${empty page }">
			 <jsp:forward page="/MenuServlet?action=queryMenusByPage&from=houtai"></jsp:forward>
		</c:if>
		
		<c:forEach items="${page.data }" var="menu">
			<tr>
				<td >${menu.id }</td>
				<td >${menu.name }</td>
				<td >${menu.typeName }</td>
				<td >${menu.burden }</td>
				<td >${menu.brief }</td>
				<td >${menu.price }</td>
				<td >${menu.sums }</td>
				<td >${menu.pricel }</td>
				<td >${menu.sumsl }</td>
				<td ><img src="/grandmamdish/Administration/${menu.imgPath }"  height="80px"  /></td>
			</tr>
		</c:forEach>
		
	</table>
	<div class="bottom">
		<span>第 ${page.curPage }页/共${page.totalPage }页</span>
		<c:choose>
			<c:when test="${page.curPage==1 }">
				<span>[首页]</span>
			</c:when>
			<c:when test="${page.curPage!=1 }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=1&from=houtai">[首页]</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.curPage==1 }">
				<span>[上一页]</span>
			</c:when>
			<c:when test="${page.curPage!=1 }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=${page.curPage-1 }&from=houtai">[上一页]</a>
			</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${page.curPage==page.totalPage }">
				<span>[下一页]</span>
			</c:when>
			<c:when test="${page.curPage!=totalPage }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=${page.curPage+1 }&from=houtai">[下一页]</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.curPage==page.totalPage }">
				<span>[末页]</span>
			</c:when>
			<c:when test="${page.curPage!=totalPage }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=${page.totalPage }&from=houtai">[末页]</a>
			</c:when>
		</c:choose>
	</div> 
		
</body>
</html>
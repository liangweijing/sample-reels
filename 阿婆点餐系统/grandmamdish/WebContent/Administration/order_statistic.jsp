<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty page }">
		<jsp:forward page="/AdminLoginServlet?methods=statistic"></jsp:forward>
	</c:if>
	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">
				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="8" height="20">
									<span class="left_bt2">本日销售额统计</span>
								</td>
							</tr>
							<tr>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">单价</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">合计</span></td>
							</tr>

							<c:set value="0" var="pagesum" />
							
							<c:forEach var="order" items="${page.data}">
							   <tr>
								<td class="line_table" align="center">
									<span class="left_txt">
										<c:out value="${order.menuname }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.menunum }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.price }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.menunum*order.price }"/>
										<c:set value="${pagesum+order.menunum*order.price }" var="pagesum" />
									</span></td>
							</tr>
							</c:forEach>
							
							
							<tr>
							<c:if test="${sales==null }">
							<jsp:forward page="/AdminLoginServlet?methods=sales"></jsp:forward>
							</c:if>
								<td class="line_table" align="center" colspan="8"><span
									class="left_bt2">
									本页总额：${pagesum}元 &nbsp;&nbsp;&nbsp;&nbsp;
									本日销售总额：${sales}元
								</span></td>
							</tr>
							
							<tr>
							<td class="line_table" align="center" colspan="12" height="20">
							<span class="left_bt2">
									第<c:out value="${page.curPage}" />页&nbsp;&nbsp;
									共<c:out value="${page.totalPage}" />页
							</span>&nbsp;&nbsp; 
								<c:choose>
								<c:when test="${page.curPage eq 1}">[首页]</c:when>
								<c:otherwise><a href="${pageUrl }&pageNum=1">[首页]</a></c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.curPage eq page.totalPage}">[尾页]</c:when>
								<c:otherwise><a href="${pageUrl }&pageNum=${page.totalPage }">[尾页]</a></c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.curPage eq 1}">[上一页]</c:when>
								<c:otherwise><a href="${pageUrl }&pageNum=${page.curPage-1 }">[上一页]</a></c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.curPage eq page.totalPage}">[下一页]</c:when>
								<c:otherwise><a href="${pageUrl }&pageNum=${page.curPage+1 }">[下一页]</a></c:otherwise>
								</c:choose>
								
							</td>
						</tr>	
						
					</table>
				</div>

			</td>

		</tr>
	</table>
</body>
</html>
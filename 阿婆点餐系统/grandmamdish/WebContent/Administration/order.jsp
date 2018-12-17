<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">

</style>
</head>

<body>
	 <c:if test="${empty page }">
		<jsp:forward page="/AdminLoginServlet?methods=queryOrderById"></jsp:forward> 
	</c:if>
	<%-- ${page.curPage }  --%>
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
								<td class="line_table" align="center" colspan="12"><span
									class="left_bt2">销售订单查询结果信息列表</span></td>
							</tr>
							<tr>
								<td class="line_table" align="center"><span
									class="left_bt2">用户ID</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">真实姓名</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">联系方式</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">家庭住址</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">单价(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">合计(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购时间</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">是否派送</span></td>
								<td class="line_table" align="center" colspan="2"><span
									class="left_bt2">确认订单</span></td>
							</tr> 
							
							<c:forEach var="order" items="${page.data}">
							   <tr>
								<td class="line_table" align="center">
									${order.userid }</td>
								<td class="line_table" align="center">
									<span class="left_txt">
										<c:out value="${order.menuname }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.realname }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.phone }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										 <c:out value="${order.address }"/> 
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
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.times }"/>
									</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">
										<c:out value="${order.delivery eq 0 ? '否' : '是'}" />
									</span></td>
								
								<c:if test="${order.delivery eq 0 }">
								<td class="line_table" align="center"><a
									href="../adminOrderServlet?action=delivery&orderid=${order.orderid }">确认</a></td>
								<td class="line_table" align="center"><a
									href="../adminOrderServlet?action=delete&orderid=${order.orderid }">取消</a></td>
								</c:if>
							</tr>
							</c:forEach>
							
						 <tr>
							<td class="line_table" align="center" colspan="12" height="20">
							<span class="left_bt2">
									第<c:out value="${page.curPage}" />页&nbsp;&nbsp;
									共<c:out value="${page.totalPage}" />页
							</span>&nbsp;&nbsp; 
								<c:choose>
								<c:when test="${page.curPage eq 1}">[首页]</c:when>
								<c:otherwise><a href="/grandmamdish/AdminLoginServlet?methods=queryOrderById&curPage=1">[首页]</a></c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.curPage eq page.totalPage}">[尾页]</c:when>
								<c:otherwise><a href="/grandmamdish/AdminLoginServlet?methods=queryOrderById&curPage=${page.totalPage }">[尾页]</a></c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.curPage eq 1}">[上一页]</c:when>
								<c:otherwise><a href="/grandmamdish/AdminLoginServlet?methods=queryOrderById&curPage=${page.curPage-1 }">[上一页]</a></c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.curPage eq page.totalPage}">[下一页]</c:when>
								<c:otherwise><a href="/grandmamdish/AdminLoginServlet?methods=queryOrderById&curPage=${page.curPage+1 }">[下一页]</a></c:otherwise>
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
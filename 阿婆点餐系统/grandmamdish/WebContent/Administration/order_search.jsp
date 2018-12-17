<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="js/date.js" type="text/javascript"></script>
</head>
<body>
	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">

				<div align="center" width="120">
					<form action="/grandmamdish/AdminLoginServlet?methods=search" name="form3"
						method="post">
						<table id="table1" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tbody style="margin: 0; padding: 0">

								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按用户ID查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="userid" size="20" value="${orderCriteria.userid eq '0' ? '' : orderCriteria.userid }"> </td>
								</tr>
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按菜品名称查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="menuname" size="20"  value="${orderCriteria.menuname }"> </td>
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按销售日期查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="date" size="20" readOnly
										onClick="setDay(this);" value="${orderCriteria.date }"> <input type="submit"
										value="查询"></td>
						</table>
					</form>
				</div>



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
								
							</tr>

							<c:forEach var="order" items="${list}">
							   <tr>
								<td class="line_table" align="center">
									<span class="left_txt">
										<c:out value="${order.userid }"/>
									</span></td>
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
							</tr>
							</c:forEach>
							
					</table>
				</div>

			</td>

		</tr>
	</table>
</body>
</html>
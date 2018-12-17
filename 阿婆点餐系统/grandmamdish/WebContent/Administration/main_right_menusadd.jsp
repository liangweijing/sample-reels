<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script language="JavaScript">
	function check11() {
		
		if (document.form1.name.value == "") {
			alert("菜品名称不能为空!");
			document.form1.name.focus();
			return false;
		}
		if (document.form1.author.value == "") {
			alert("原料不能为空!");
			document.form1.author.focus();
			return false;
		}
		if (document.form1.price.value == "") {
			alert("市场价格不能为空!");
			document.form1.price1.focus();
			return false;
		}
		if (document.form1.price1.value == "") {
			alert("会员价格不能为空!");
			document.form1.price1.focus();
			return false;
		}
		if (document.form1.brief.value == "") {
			alert("说明不能为空!");
			document.form1.brief.focus();
			return false;
		}

		if (document.form1.img.value == "") {
			alert("上传图片不能为空!");
			document.form1.img.focus();
			return false;
		}
	}
</script>

</head>
<body>
	<div class="add">
		<form name="formadd" action="/grandmamdish/MenuServlet?action=addMenu"  method="post" onsubmit="check11()" enctype="multipart/form-data">
			<table width="100%" border="1" >
				<tr>
					<td colspan="2" align="center">添加菜单</td>
					
				</tr>
				<tr>
					<td>菜品名称：</td>
					<td><input type="text" name="menuname"></td>
				</tr>
				<tr>
					<td>原    料：</td>
					<td><input type="text" name="burden"></td>
				</tr>
				<tr>
					<td>市场价格：</td>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<td>会员价格：</td>
					<td><input type="text" name="pricel"></td>
				</tr>
				<tr>
					<td>说    明：</td>
					<td><textarea name="brief" id="" cols="30" rows="10"></textarea></td>
				</tr>
				<tr>
					<td>菜品类别：</td>
					<td>
						<c:if test="${empty types }">
							<jsp:forward page="/MenuTypeServlet?method=gettype"></jsp:forward>
						</c:if>
						<select name="menutype" >
							
							<c:forEach items="${types }" var="type">
								<option value="${type.id }">${type.name}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr>
					<td>上传图片：</td>
					<td><input type="file" id="fileid" name="file">					
					
					</td> 
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="添加">${result }</td>
					
				</tr>
			</table>

		</form>

	</div>
</body>
</html>
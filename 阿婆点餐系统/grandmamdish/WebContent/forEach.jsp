<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.*" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%List<String> li1=new ArrayList(); 
    		li1.add("1");
    		li1.add("2");
    		li1.add("3");
    	  List<String> li2=new ArrayList();
    	  	li2.add("2");
    	  	request.setAttribute("li1", li1);
    	  	request.setAttribute("li2", li2);
    	%>
    	<c:forEach items="${li1 }" var="l1">
    		
    		<c:forEach items="${li2 }" var="l2">
    			<c:if test="${l2==l1 }">
    				${l1 }
    			</c:if>
    		</c:forEach>
    	</c:forEach>
    	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
<!-- <script type="text/javascript">
var json = [

	　　{dd:'SB',AA:'东东',re1:123},

	　　{cccc:'dd',lk:'1qw'}

	];

	for(var i=0,l=json.length;i<l;i++){数组中一个对象是一个元素，下标为0的是整个{dd:'SB',AA:'东东',re1:123}在里面分key

	　　for(var key in json[i]){

	　　　　alert(key+':'+json[i][key]);

	　　}

	}

</script> -->
<script type="text/javascript">
//当文档准备完毕，就会调用该方法
$(function() {
	//执行监听，当这个元素的值发生改变，就会执行内部的方法
	
	$("#button").click(function() {
		//那么要做什么呢？ 其实是要把这个元素的值上传到服务器上。
		/* var id = $(this).val();
		$("#city").html("<option value=''>-请选择-"); */
		$.get("/grandmamdish/MenuTypeServlet?method=ranktwo"  ,function(data){
			$("#rank").empty();
			var newdiv=$("<div id='rank2' style='width: 100px;height: 50px;border: 1px solid red;''></div>");
			for(var i=0,l=data.length;i<l;i++){

				　　for(var key in data[i]){

				　　　/* 　alert(key+':'+data[i][key]); */
					var txt=$("<p style='font-size:12px;'></p>").text(key+':'+data[i][key]);
					
					$("#rank").append(txt);
					/* $("#rank").append(txt); */
				　　}

				}
			/* $("#rankli").append(newdiv); */
		},"json");
	});
});

</script>
</head>
<body>
	<input type="button" value="按钮" id="button" />
	<ul>
	<li id="rankli">
	<div id="rank" style="width: 100px;height: 50px;border: 1px solid red;">原文原文原文原文原文原文
	原文原文原文原文原文原文
	</div>
	</li>
	</ul>
	<%request.setAttribute("name", "测试"); %> <!-- 这样和servlet里面一样servletContext，session，request之间是取不到的 -->
	<%-- <%=session.getAttribute("name") %>返回null --%>
	<%-- ${name } --%> <!-- 用el表达式会从最大的域挨个查找这个属性 -->
	${sessionScope.name } <!-- el的域属性是sessionScope，requestScope，pageScope,pageContext这样也取不到el的属性为null返回空串 -->
	
</body>
</html>
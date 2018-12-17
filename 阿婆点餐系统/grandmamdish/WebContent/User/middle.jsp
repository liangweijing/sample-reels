<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#submit").click(function(){
		window.location.href="/grandmamdish/MenuServlet?action="+"submitOrder";
	}); 
	/* $("#submit").on("click",function(){一个按钮绑定两个事件写法，但是由于submit跳转到两个servlet才回到index无法固定内容
		$("#salesrank").empty();因为重新加载index没有点击submit按钮
		$.get("/grandmamdish/MenuTypeServlet?method=ranktwo"  ,function(data){
			for(var i=0,l=data.length;i<l;i++){

				　　for(var key in data[i]){

					var txt=$("<p style='font-size:12px;'></p>").text(key+':'+data[i][key]);
					$("#salesrank").append(txt);
					
				　　}

				}
			
		},"json");
	}); */
	
	$("#flush").click(function(){//不是点击之后刷新，不是失去焦点，也不能是鼠标移上去之后刷新，找不到合适的动作之后刷新所以加了按钮
		 $("#salesrank").empty();
		$.get("/grandmamdish/MenuTypeServlet?method=ranktwo"  ,function(data){
			for(var i=0,l=data.length;i<l;i++){
					for(var key in data[i]){

					var txt=$("<p style='font-size:12px;'></p>").text(key+':'+data[i][key]);
					$("#salesrank").append(txt);
					}

				}
			
		},"json"); 
		/* window.location.href="/grandmamdish/MenuServlet?action=rank " 这样也做得到可是刷新会闪一下用户体验不好*/
	});
	
	});

</script>
<script type="text/javascript">
	function test(){
		var sums="${menu.sums}";
		var name="${menu.name}";
		var user="${user}";
		if(user==""){
			alert("请先登录");
			return false;
		}
		if(sums==0){
			alert(mane+"已售空");
			return false;
		}
		
	}
	function emptycar(){
		if(confirm("确定清空？")){
			document.getElementById("menuorder").innerHTML="";
			document.getElementById("totalp").innerText="";
			document.getElementById("totals").innerText="";
			
			//干掉session（即清空购物车mycar和menunum属性）
			 window.location.href="/grandmamdish/ExistServlet?type=emptycar";
		}else{
			return false;
		}
		 
	}	 
	
</script>

<style>
	*{
		margin: 0px;
		padding: 0px;
		font-size: 12px;
	}
	
	.left{
		width: 900px;
		height: 600px;
		float: left;
		overflow: hidden;
	}
	 .right{
		width: 450px;
		height: 900px;
		float: right;
		overflow: hidden;
	
	}
	
	.right li{
		height: 80px;
		list-style: none;
		background-color: #d2cb8b;
		margin: 15px 0;
		position: relative;
	}
	.right h3{
		height: 25px;
		line-height: 25px;
		text-align: center;
		border-bottom: 2px dashed #a89966;
	}
	#submit{
		margin: 0 10px;
		width: 80px;
		display: block;
	}
	#reset{
		margin: 0 10px;
		width: 80px;
		display: block;
	}
	.notice,.mycar,.salesrank{
		border: 1px solid #ccc;
	} 
</style>
</head>
<body>

	<!--首页左侧  -->
	<div class="first">
		<div class="left">
		
		<c:if test="${empty page }">
			 <jsp:forward page="/MenuServlet?action=queryMenusByPage&from=qiantai"></jsp:forward>
		</c:if>
		
	<c:forEach items="${page.data }" var="menu">
	<div class="left-t">
					<table style="float: left;">
						<tr>
							<td rowspan="6" class="bookPic"><img src="${pageContext.request.contextPath }/Administration/${menu.imgPath}" width="130px" height="90px"/></td>
							<td><span>菜名:</span></td>
							<td><span><strong>${menu.name}</strong></span></td>
						</tr>
						<tr>
							<td><span>市场价格:</span></td>
							<td><span>${menu.price}</span></td>
						</tr>
						<tr>
						    <td><span>会员价格:</span></td>
							<td><span><strong style="color: red;">${menu.pricel}</strong></span></td>
						</tr>
						<tr>
						    <td><span>数量:</span></td>
							<td><span><strong style="color: red;">${menu.sums}</strong></span></td>
						</tr>
						<tr>
							<td><span>配料:</span></td>
							<td><span>${menu.burden}</span></td>
						</tr>
						<tr>
							<td><span>菜品类型:</span></td>
							<td><span>${menu.typeName}</span></td>
						</tr>
						<tr>
							<td colspan="2" style="height: 40px;">
							<a href="${pageContext.request.contextPath}/UserServlet?methods=mycar&id=${menu.id}" onclick="return test();">加入餐车</a>
							</td>
							<td></td>
						</tr>
					</table>

		</div>														
</c:forEach>
	<div style="clear: both;text-align: center;">

		<span>第 ${page.curPage }页/共${page.totalPage }页</span>
		<c:choose>
			<c:when test="${page.curPage==1 }">
				<span>[首页]</span>
			</c:when>
			<c:when test="${page.curPage!=1 }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=1&from=qiantai">[首页]</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.curPage==1 }">
				<span>[上一页]</span>
			</c:when>
			<c:when test="${page.curPage!=1 }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=${page.curPage-1 }&from=qiantai">[上一页]</a>
			</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${page.curPage==page.totalPage }">
				<span>[下一页]</span>
			</c:when>
			<c:when test="${page.curPage!=totalPage }"> 
			<!-- 问题下一页内容不显示不是框架转跳问题，没加参数from=qiantai -->
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=${page.curPage+1 }&from=qiantai" >[下一页]</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.curPage==page.totalPage }">
				<span>[末页]</span>
			</c:when>
			<c:when test="${page.curPage!=totalPage }">
				<a href="/grandmamdish/MenuServlet?action=queryMenusByPage&curPage=${page.totalPage }&from=qiantai">[末页]</a>
			</c:when>
		</c:choose>
	</div>			
</div>

	<!--首页左侧结束  -->
	
 	<!--首页右侧  -->
	<div class="right">
		<ul >
			<li>
				<!--公告  -->
			<div class="notice">
				<h3>餐厅公告</h3>
			</div>
			<!--公告结束  -->
			</li>
			<li style="height: 180px;">
				<!--我的餐车  -->
			<div class="mycar" >
			<h3>我的餐车</h3>
				<table id="shopcar" width="100%" align="center">
					<tr >
						<th>菜单名称</th>
						<th >单价</th>
						<th >数量</th>
					</tr>
					<c:choose>
						<c:when test="${empty user }">
							<td>0</td>
							<td>0</td>
							<td>0</td>
						</c:when>
						<c:when test="${!empty user }">
							<c:forEach items="${mycar }" var="car">
						<tr id="menuorder">
							<td>${car.name }</td>
							<td>${car.price }</td>
							<td>
								<c:forEach items="${menunum }" var="map">
									<c:if test="${map.key==car.id }">
										<c:forEach items="${page.data }" var="menu">
											<c:if test="${menu.id==map.key }">
												<c:if test="${menu.sums<map.value }">
													<script>
														alert("${menu.name}"+"库存不够！");
													</script>
												</c:if>
											</c:if>
										</c:forEach>
									${map.value }
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
						</c:when>
					</c:choose>
					
					
					<tr>
						<td>总计</td>
						<c:choose>
							<c:when test="${empty user }">
								
								<td>0</td>
								<td>0</td>
							</c:when>
							
							<c:when test="${!empty user }">
							<c:set var="totalPrice" value="0"></c:set>
								<td id="totalp">
						
							<c:choose>
								<c:when test="${mycar==null }">0</c:when>
								<c:when test="${mycar!=null }">
									<c:forEach items="${mycar }" var="car">
									
										<c:forEach items="${menunum }" var="map">
											<c:if test="${map.key==car.id }">
											<span style="display: none;">${totalPrice=totalPrice+map.value*car.price }</span>
											</c:if>
										</c:forEach>
									</c:forEach>
								</c:when>
							</c:choose>
							${totalPrice }
						</td>
						<td id="totals">
						<c:set var="totalsum" value="0"></c:set>
						<c:choose>
							<c:when test="${menunum==null }">0</c:when>
							<c:when test="${menunum!=null }">
								<c:forEach items="${menunum }" var="map">
								<span style="display: none;">${totalsum=totalsum+map.value }</span>
								</c:forEach>
								${totalsum }
							
							</c:when>
						</c:choose>
							
						</td>
					
							</c:when>
						</c:choose>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<input type="button" id="submit" value="提交"   />
				<input type="button" id="reset" value="全部取消" onclick="return emptycar()"/>
			</div>
			
			
			</li>
			<!--我的餐车结束  -->
			
		<li id="rankli" style="height: 140px;">
			<!--销售排行榜  -->
		<div id="salesrank">
			 <h3>销售排行榜</h3>
			<!-- 不加判断则会不停的刷新，因为跳转到MenuServlet后又跳回来，加载middle.jsp就会不停的转发到servlet又转发回来 -->
			<!-- 加了判断只转发一次,而且servlet不用再转跳回来 -->
			<c:if test="${empty rankOrder }">
				<%request.getRequestDispatcher("/MenuServlet?action=rank").forward(request, response); %>
			</c:if>
			
			<table width="100%" align="center">
			<c:set var="i" value="0"></c:set>
				<c:forEach items="${rankOrder }" var="rank" varStatus="status">
					<tr>
						<td>${i=i+1}${rank.key}</td>  <!-- 输出的是i不是i=i+1,没有自增符号 -->
						<td>${status.count}${rank.key}</td>
						<td id="salenum">已销售${rank.value }份</td>
					</tr>
				</c:forEach>
			</table>
			
			 
		</div>
		<input type="button" id="flush" value="刷新"/>
			</li> 
			<!--销售排行榜结束  -->
		</ul>
	</div> 
		<!--首页右侧结束  -->
	</div>	
			
		
</body>
</html>
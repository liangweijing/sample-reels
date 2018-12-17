<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <title>阿婆私房菜</title> 
 <link rel="shortcut icon" href="favicon.ico"  type="image/x-icon"/>

<style>
	*{
		margin: 0;
		padding: 0;
	}
</style>
</head>
	<frameset rows="120px,*,55px" frameborder="0"  marginheight="10px" marginwidth="10px" framespacing="0" noresize="noresize" scrolling="no">
			<frame name="top" src="/grandmamdish/Administration/main_top.jsp" frameborder="1">
				
			</frame>
		<frameset cols="20%,60%" scrolling="no">
		<frame name="main_left" src="/grandmamdish/Administration/main_left.jsp" frameborder="1"></frame>
		<frame name="content" src="/grandmamdish/Administration/main_right_menusinfo.jsp" target="_top"></fram>
		</frameset>
		<frame name="bottom" src="/grandmamdish/Administration/main_bottom.html" ></frame>
	</frameset>
	
	
</html>
function check(){
	if(document.getElementById('username').value==''){
		document.getElementById('usernameinfo').innerText='用户名不能为空！'
		document.getElementById('username').focus();
		return false;
	}else if(document.getElementById('content').value==''){
		document.getElementById('textareainfo').innerText='内容不能为空！'
		return false;
	}else{
		document.getElementById('successinfo').style.display='block';
		
	}
	return false;
}

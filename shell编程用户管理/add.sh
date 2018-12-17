clear;	

	isContinue="y";
	userName="";
	while [ $isContinue = "y" -o $isContinue = "Y" ]
	do
		#用户名处理，输入一致到字符串长度不为0为止，字母打头，数字加字母，判断是否已存在
		userName="";
		while [ -z $userName ] #长度为空true
		do
			echo -n "用户名:";
			read userName;
			if [ -z $userName ] #长度为空true
			then
				echo "错误！用户名是不能为空的，请重新输入！";
				continue;
			fi	
			if [ `expr match $userName "[a-zA-Z][0-9a-zA-Z]*"` -ne `expr length $userName` ] #expr match返回匹配到的字符串的长度，exprlength求字符串长度。
			then
				echo "错误！用户名的只能由非数字打头的字符和数字组成，请重新输入！";
				userName="";
				continue;
				
			fi
			grep -q $userName  "./passwd"; 
			if [ $? -eq 0 ] 
			then
				echo "错误！用户名已存在，请重新输入！";
				userName="";
				continue;
			fi			
		done
		#密码处理：1.验空，3.密码长度要6位，由大小写字母、数字和控制字符组成，2.验两次输入的一致性，
		passWord="";
		passWordAgain="";
		while [ -z $passWord ]
		do
			echo -n "密码:"; #不换行输出
			read passWord;		
			if [ -z $passWord ] #长度为0true
			then
				echo "错误！密码是不能为空的，请重新输入！";
				continue;
			fi		
			if [ `expr length $passWord` -ne 6 ]
			then
				echo "密码长度为6位，请重新输入!";
				passWord="";
				continue;
			fi		
			if [ `expr match $passWord "[0-9a-zA-Z]*"` -ne `expr length $passWord` ]
			then
				echo "密码由大小写字母、数字和控制字符组成，请重新输入!";
				passWord="";
				continue;
			fi	
			echo -n "请在输入一次密码：";
			read passWordAgain;	
			if [ $passWordAgain != $passWord ]
			then
				echo "两次输入的密码不一样，请重新输入！";
				passWord="";
				continue;
			fi
			
		done
		#用户UID输入处理,UID为数字、一般非超级用户的ID是100-500
		uID="";
		while [ -z $uID ]
		do
			echo -n "用户UID:";
			read uID;		
			if [ -z $uID ]
			then
				echo "错误！用户UID是不能为空的，请重新输入！";
				continue;
			fi	
			if [ `expr match $uID "[0-9]*"` -ne `expr length $uID` ] #输入的密码在[0-9]正则表达式中比配的长度是否和输入的密码长度一致，校验是否都是0-9中数字
			then
				echo "错误！用户的UID必须为数字，请重新输入！";
				uID="";
				continue;
			fi		
			if [ $uID -lt 100 -o $uID -gt 500 ] #-o或，小于100或大于500错
			then
				echo "错误！一般非超级用户的ID范围为100～500，请重新输入!";
				uID="";
				continue;
			fi
			grep -q $uID  "./passwd";  #-q抑制输出
			if [ $? -eq 0 ] 
			then
				echo "错误！用户UID已存在，请重新输入！";
				uID="";
				continue;
			fi
		done	
		#用户组GID处理，是500-60000
		gID="";
		while [ -z $gID ]
		do
			echo -n "用户组GID:";
			read gID;		
			if [ -z $gID ]
			then
				echo "错误！用户GID是不能为空的，请重新输入！";
				continue;
			fi		
			if [ `expr match $gID "[0-9]*"` -ne `expr length $gID` ]
			then
				echo "错误！用户的GID必须为数字，请重新输入！";
				gID="";
				continue;
			fi	
			if [ $gID -lt 500 -o $gID -gt 60000 ]
			then
				echo "错误！用户组的ID范围为500～60000，请重新输入!";
				gID="";
				continue;
			fi
		done
		echo -n "注释:";read note;
		#登录shell:bash,sh,csh,ksh
		shellVersion="";
		while [ -z $shellVersion ]
		do
			echo -n "请输入以下登录SHELL（bash,sh,csh,ksh）中一个:";
			read shellVersion;
			if [ $shellVersion != "bash" -a $shellVersion != "sh" -a $shellVersion != "csh" -a $shellVersion != "ksh" ] #-a与
			then
				echo "输入的Shell类型【$shellVersion】不在本系统支持范围内，请重新输
入！";
				shellVersion="";
				continue;
			fi
		done	
		echo -n "用户工作目录:";
		mkdir /home/$userName;
		if [ $? -eq 0 ]
		then
			echo  "/home/$userName成功创建！";
			mdirec="/home/$userName";
		fi
		userInfo="$userName:x:$uID:$gID:$note:$mdirec:$shellVersion";
		echo $userInfo >> $dataSource | sudo sh; #>>追加方式重定向
		sudo sh -c "echo $userInfo >> ./passwd";
		#因为sudo得到的权限并不随重定向符号输出，所以我们可以先将当前shell弄得有root的权限，然后再进行相关操作

		echo "$userName:$passWord" >> "./realpwd"; #建立一个查看密码的文件 	
		if [ $? -eq 0 ]
		then
			echo "$userName用户信息添加成功！";
		else
			echo "$userName用户信息添加失败！";
		fi		
	echo -n "是否继续添加其他用户？？（y/n）";
		read isContinue;		
	
done

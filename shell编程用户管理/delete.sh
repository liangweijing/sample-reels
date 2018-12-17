clear;
dataSource='./passwd'; #为什么这个文件不认全局变量？
	isContinue="y";
	
		while [ $isContinue = "y" -o $isContinue = "Y" ] #-o逻辑或
		do
			echo -n "输入用户名:";
			read userName;
			if [ -z "$userName" ]
			then
				echo "用户名不能为空，请重新输入！";
				isContinue="Y";
				continue;
			fi	
			grep -q $userName $dataSource;		
			if [ $? -eq 0 ] #找到返回0，没找到返回1
			then
					echo "用户信息为：";
				        grep  $userName $dataSource;
					echo -n "是否删除？（y/n）";
					read isContinue;
			else
					echo "你输入的 【$userName】 用户不存在！";
					echo -n "是否继续删除其他用户？？（y/n）";
					read isContinue;
					continue;
			fi	
			if [ $isContinue = "y" -o $isContinue = "Y"  ]
			then
					echo -n "请输入密码：";
					read password;
			else
				break;
			fi
			realpwd=`(grep $userName "./realpwd" | awk -F ":" '{print $2}')`;

			#echo $realpwd;
			if [ "$realpwd" != "$password" ]
			then					
				echo "密码输入错误";
				break;	
			else
				rowID1=$(grep -n $userName $dataSource | awk -F ":" '{print $1}'); #取得要删除的用户的记录行号
 				sed -e "$rowID1 d" $dataSource > tempFile; #删除某行:sed数字+d命令,将删除后的全部内容输出重定向到tempfile文件
				cat tempFile > $dataSource; #再将tempfile文件写回（不包括删除了的用户）passwd文件
				rmdir "/home/$userName";

				rowID2=$(grep -n $userName "./realpwd" | awk -F ":" '{print $1}'); 
 				sed -e "$rowID2 d" "./realpwd" > real; #删除的密码文件中对应行
				cat real > "./realpwd";
					
					#rm tempFile;

			fi			
					
				
			if [ $? -eq 0 ]
			then
					echo "记录删除！";
			else
					echo "系统错误，删除失败！";
			fi			
	
			echo -n "是否继续删除其他用户？？（y/n）";
			read isContinue;
		
			if [ -z $isContinue ]
			then
				isContinue="N";
			fi

	done

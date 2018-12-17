#! /bin/bash
dataSource='./passwd';
homePath='.';
addUsers="./add.sh";
deleteUsers="./delete.sh";
CheckDataSourceFileExist()
{
	if [ -e $dataSource ]
	then
		#文件存在
		return 0;
	else
		#文件不存在
		clear; #清空标准输入
		echo -n "警告 【$dataSource】 不存在！请确认！"; #-n代表不换行输出
		read ; #读取选项存入特殊环境变量REPLY中而不是choice，继续while  
		return 1;
	fi
}
	choice="F";
	while [ $choice != "Q" -a $choice != "q" ]
	do
		CheckDataSourceFileExist;
		if [ $? -ne 1 ] #调用check函数返回是否为1，－ne 1即不等于1，即文件存在
		then
			clear;
			echo "			用户信息管理主菜单";
			echo "===========================================================";
			echo "		1.显示当前所有记录";
			echo "		2.格式化显示当前所有记录";
			echo "		3.显示用户名和用户ID";
			echo "		4.格式化显示用户名和ID";
			echo "		5.查询特定用户信息";
			echo "		6.添加新用户";
			echo "		7.删除用户";
			echo "		8.查看密码";
			echo "		Q.退出";
			echo -n "你的选择:";
			read choice;
			#作用是清除由于read到string.Empty的时候引发的bash参数过多的错误。
			if [ -z $choice ] #字符串长度为0是true
			then
				choice="empty";
			fi
			clear;
			if [ $choice = "empty" ];
			then
				echo "选项尚未选择！";
			else
				case $choice in
					1) CheckDataSourceFileExist;
						if [ $? -ne 1 ] #不等于1，即文件存在
						then
							echo "当前的所有的用户信息如下所示:";
							echo "     序号 用户名 密码 ID GID 说明 工作目录 登录Shell";
							cat -n $dataSource | tr ":" " " | more; 
#调用变量$,tr将：分隔符替换为空格，more命令是一个基于vi编辑器文本过滤器，它以全屏幕的方式按页显示文本文件的内容Enter（向下翻滚一行），空格（向下滚动一屏），Q（退出命令）该命令一次显示一屏文本，满屏后停下来，并且在屏幕的底部出现一个提示信息，给出至今己显示的该文件的百分比：--More（更多）--（XX%）

						fi
						
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
					2) 	CheckDataSourceFileExist;
							if [ $? -ne 1 ]
							then
								echo "当前的所有的用户信息如下所示:";
								echo -e "    序号 用户名\t密码\tID\tGID\t说明\t工作目录\t登录Shell"
								cat  -n $dataSource  | awk -F ":" '{print $1 "\t" $2 "\t" $3 "\t" $4 "\t" $5 "\t" $6 "\t" $7 }'  | more; 
								
							fi
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
	 #sort k 按照第一列将passwd文件排序，awk文本分析工具，把文件逐行（以\n）的读入,-F ":"将冒号作文本分割域输出第一个域,第二个(即用户名\t密码\tUID\tGID\t注释\t主目录\t登录shell)

					
					3) 						
						CheckDataSourceFileExist;
						if [ $? -ne 1 ]
						then
							echo "当前的所有的用户的用户名和用户ID如下所示:";
							echo -e "　　　　序号 用户名,ID";
							awk -F ":" '{print $1 ", "  $3}' $dataSource  | cat -n | more;
						fi
						
						echo -n "请按任意键返回菜单："; 
						read ;
#read ;存入特殊环境变量REPLY中而不是choice，继续while，所以输入什么都可以enter,q,都会继续。只有在case菜单中选择q是赋值给choice,case之后的选择都是这句，给了默认的
						;;
					4) 	CheckDataSourceFileExist;
							if [ $? -ne 1 ]
							then
							echo "当前的所有的用户的用户名和用户ID如下所示:";
								echo -e "    序号 用户名 \t ID";
								awk -F ":" '{print $1 "\t "  $3}' $dataSource  | cat -n | more;
							fi
				
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
					5) CheckDataSourceFileExist;
						if [ $? -ne 1 ]
						then
							keyWords="";
							while [ -z $keyWords ] #字符串长度为0是true
							do
								echo -n "输入搜索关键词:";
								read keyWords;
								if [ -z $keyWords ]
								then
									echo "搜索关键词不能为空，请重新输入！";
								fi
							done
						CheckDataSourceFileExist;
						if [ $? -ne 1 ]
						then
							grep -i -n --color=auto $keyWords $dataSource; #-i不区分大小写,grep -n显示行号和cat不一样,关键字加颜色	
							if [ $? -eq 1 ] #找到grep命令返回0，否返回1，文件不存在返回2
							then
								echo "很遗憾，【$dataSource】文件中，并不存在与 $keyWords 一致的信息。";
							fi
						fi
						fi
						
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
					6) $addUsers
						
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
					7) $deleteUsers
						
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
					8)echo "    序号 用户名：密码";
						cat -n "./realpwd" ;
						echo -n "请按任意键返回菜单："; 
						read ;
						;;
					Q) printf "程序已经退出。\n" choice="Q";;
					q) printf "程序已经退出。\n" choice="q";;
					*) echo $choice "：此选项不是默认提供的功能。请确认。"　
						echo -n "请按任意键返回菜单："; #-n不换行
						read ; 
						;; #default
				esac
			fi

		fi
	done

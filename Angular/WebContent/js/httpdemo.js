/**
 * $http.post({
				ulr:'/Angular/AngularServlet?',
				data:{jump:"add"}
	})
			.success(function(resp){
				console.log(resp);
			})
			.error(function(){console.log("http失败");})
  *
  *
  *
  *
					    
		 $http({
			 method: 'POST',
             url: '/Angular/AngularServlet/addUser',
             data: {
            	 username:$scope.username	 
             },
         }).success(function (response) {
             //响应成功
        	 alert(111);
             console.log(response);
            
         }).error(function () {
        	 alert(000);
             console.log('获取失败')
         });
 */
angular.module('myApp',[])
.controller('httpCtrl',function($scope,$http){
	$scope.username="";
	$scope.submitForm=function(){
		//要通过post传递的参数
		var data = {
		    jump:"add",
		    username:$scope.username,
		},
		//post请求的地址
		url = "/Angular/AngularServlet",
		//将参数传递的方式改成form
		postCfg = {
		    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		    transformRequest: function (data) {
		        return $.param(data);
		    }
		};
		//发送post请求，获取数据
		$http.post(url, data, postCfg)
		    .success(function (response) {
		        alert("success");
		        console.log(response);
		    }).error(function () {
	        	 alert(000);
	             console.log('获取失败')
	         });
		
	}
	
});
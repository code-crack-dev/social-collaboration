'use strict';
app.controller('UserController', function($scope, $cookies, UserService, $location, $rootScope, $http)  {
	
	console.log('----------- starting controller')
	$scope.user={
		mail_id:'',
		username:'',
		first_name:'',
		last_name:'',
		dateReg:'',
		gender:'',
		password:'',
		status:'',
		role:'',
		details:'',
		mobile:'',
		isOnline:'',
		last_seen:'',
		dob:'',
		reason:'',
		birthDate:'',
		errorMessage:'',
		errorCode:'',
	};
	
	$scope.users;
	$scope.myJobs;
	$scope.message;
	$scope.friend;
	
	$scope.registerUser=function()
	{
		console.log("Enter Register User")
		UserService.registerUser($scope.user)
		.then(
				function(d){
					$location.path("/")
					$scope.user = d;
				},
				function(errorMessage)
				{
					console.error('Error while register user');
				});
	};
	
	
	$scope.authenticate = function(user)
	{
		console.log("Authenticate user function")
		console.log("User " + $scope.user.username)
		console.log("password" + $scope.user.password)
		UserService.authenticate(user)
		.then
		(
				function(d)
				{
					$scope.user = d;
					console.log("User ErrorCode : " + $scope.user.errorCode)
					console.log("status " + $scope.user.status)
					console.log("user " + $scope.user.username)
					console.log("password " + $scope.user.password)
					if ($scope.user.status === 'R') 
					{
						alert("Your registraton is rejected. Plaease contact Admin");
						user.etErrorCode("404");
						user.setErrorMessage("Your Registration is rejected");
					} 
					
					else if($scope.user.status == 'N')
					{
						alert("Your Registration is not yet approved. Wait for Admin approval");
						user.etErrorCode("404");
						user.setErrorMessage("Your Registration is not yet approved");
					}
					
					else if($scope.user.errorCode == 407)
					{
						alert($scope.user.errorCode);
						console.log("Invalid User Name or Password")
						username : ''
						password : ''
						$location.path("/login");
					}
					
					//else if($scope.user.errorCode == 408) check
					else if($scope.user.errorMessage == 408)
					{
						alert($scope.user.errorMessage);
						$location.path("/login");
					}
					else
					{
						console.log("Valid Credentials, Navigating to home page "+$scope.user.status)
						//$scope.message = "You are successfully logged in as" check;
						$scope.message1 = "You are successfully logged in as :";
						$rootScope.currentUser = 
							{
								username: $scope.user.username,
								first_name: $scope.user.first_name,
								last_name: $scope.user.last_name,
								dob: $scope.user.dob,
								gender: $scope.user.gender,
								mail_id: $scope.user.mail_id,
								status: $scope.user.status,
								role: $scope.user.role,
								birthDate: $scope.user.birthDate,
								isOnline: $scope.user.isOnline,
								last_seen: $scope.user.last_seen
							};
						
						$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
						$cookies.putObject('currentUser',$rootScope.currentUser)
						var cookie = $cookies.getObject('currentUser')
			            console.log(cookie)
			            console.log($cookies.getObject('currentUser'))
			            
						$location.path("/");
					}
				},
				
				function(errResponse) {
					
					$scope.message = "Invalid username or password.";
					$location.path("/login");
				});
		};
		

		$scope.login= function()
		{
			console.log("Validating Login " + $scope.user);
			$scope.authenticate($scope.user);
			
		};
		
		
		$scope.logout= function()
		{
			console.log("Entering of Logout Function");
			$rootScope.currentUser = {};
			$cookieStore.remove('currentUser');
			
			console.log("Calling Session Logout");
			UserService.logout()
            alert("Successfully Logout...");

			$location.path('/login');
		};
		
		$scope.applyJob= function(job)
		{
			console.log("Entering Job Apply")
			UserService.applyJob(job)
			.then
			(
				function(response)
				{
					console.log("Job Applied")
					alert("You applied for the Job")
					$location.path("/viewJobs")
				}
			)
		};
		
			
//to send request to friend

		$scope.friendRequest= function(username)
		{
			console.log("Entering Send Friend Request");
			UserService.friendRequest(username)
			.then 
			(
				function(response)
				{
					console.log(response.status);
					alert('FriendRequest is Sent');
					listUser();
					$location.path("/viewUsers");
				}, function(errResponse)
				{
					console.error("Error sending Friend Request");
					$location.path("/viewUsers");
				}
			)
		};	
		
		$scope.getProfile = function(username)
		{
			console.log("Entering View Friend")
			UserService.getProfile(username)
			.then
			(
				function(response)
				{
					console.log("Friend Retrieved")
					$location.path("/viewProfile")	
				}
			)
		};
	
})
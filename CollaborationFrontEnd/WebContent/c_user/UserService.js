'use strict';
app
		.service(
				'UserService',
				[
						'$http',
						'$q',
						'$rootScope',
						function($http, $q, $rootScope) {
							console.log("UserService...")
							var BASE_URL = 'http://localhost:8080/CollaborationController/'
							return {
								registerUser : function(user) {
									console.log("Create user in service")
									return $http
											.post(BASE_URL + 'addUser/', user)
											.then(
													function(response) {
														alert("Registration Success. Wait for admin approval")
														console
																.log(response.status)
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while creating user');
														return $q
																.reject(errResponse())

													});
								},

								authenticate : function(user) {
									console
											.log("Entering Function Validate User")
									return $http
											.post(BASE_URL + "login/", user)
											.then(
													function(response) {
														if (response.data.errorMessage == "Success") {
															$rootScope.currentUser = {
																username : response.data.username,
																first_name : response.data.first_name,
																last_name : response.data.last_name,
																dob : response.data.user.dob,
																gender : response.data.gender,
																mail_id : response.data.mail_id,
																status : response.data.status,
																role : response.data.role,
																// birthdate:
																// response.data.birthdate,
																isOnline : response.data.isOnline,
																last_seen : response.data.last_seen
															};

														}
														return response.data;
													},
													function(errResponse) {
														$rootScope.userLoggedIn = false;
														console
																.error(errResponse.status);
														console
																.error("Error while validating");
														return $q
																.reject(errResponse);
													});
								},

								logout : function() {
									console.log("Entering Logout")
									return $http
											.get(BASE_URL + "logout/")
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.log("Error Logging out");
														return $q
																.reject(errResponse);
													});
								},

								applyJob : function(job) {
									console.log("Applying Job")
									return $http.post(BASE_URL + "applyJob",
											job).then(function(response) {
										console.log("Job has been applied")
										return response;
									})
								},

								listUser : function() {
									console.log("Entering Function List User")
									return $http
											.get(BASE_URL + "getUserList")
											.then(
													function(response) {
														// console.log(response.data)
														console
																.log("Recieved List Users "
																		+ response.status)
														return response.data

													})
								},
								// to send F request
								friendRequest : function(username) {
									console
											.log("Entering Service - Friend Request")
									return $http
											.get(
													BASE_URL + "sendRequest-"
															+ username)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.log("Error Adding Friend");
														return $q
																.reject(errResponse);
													});
								},
								getProfile : function(username) {
									console.log("Entering Get Friend")
									return $http
											.get(
													BASE_URL + "getUser-"
															+ username)
											.then(
													function(response) {
														console
																.log("Friend Retrived")
														$rootScope.friend = response.data;
														return response;
													})
								}
							}
						} ]);
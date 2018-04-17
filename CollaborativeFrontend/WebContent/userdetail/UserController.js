/**
 * 
 */'use strict';

app.controller('UserController', [
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, UserService, $location, $rootScope,$route,$window) {
			console.log("UserController...")
			var self = this;
			self.userdetail = {
					
				errorCode : '',
				errorMessage : '',
				userId : '',
				userPassword : '',
				userName : '',
				userRole : '',
				userGender : '',
				userEmail : '',
				userContactNo: '',
				userAddress: '',
				userDOB:'',
				userStatus:'',
				userIsOnline:''
				
			}
			
			self.userdetails = [];

			self.getSelectedUser = function(id) {
				console.log("-->UserController : calling getSelectedUser method : getting userdetail with id : " + id);
				UserService.getSelectedUser(id).then(
						function(d) {
							self.userdetail = d;
							$location.path('/view_user');
						}, 
						function(errResponse) {
							console.error('Error while fetching User...');
						});
			};
			self.fetchAllUsers = function() {
				console.log("--> UserController : calling fetchAllUsers method.");
				UserService.fetchAllUsers().then(
						function(d) {
							self.userdetails = d;
							console.log("yes ");
						}, function(errResponse) {
							console.error('Error while fetching Users...');
						});
			};
			self.createUser = function(userdetail) {
				console.log("--> UserController : calling createUser method.");
				UserService.createUser(userdetail).then(
						function(d) {
							self.userdetails = d;
							alert('User Created Successfully...')
						},
						function(errResponse) {
							console.error('Error while creating userdetail...');
						});
			};
			self.updateUser = function(userdetail, id) {
				console.log("--> UserController : calling updateUser method.User id is :"+id);
				console.log("-->UserController", self.userdetail);
				UserService.updateUser(userdetail, id).then(function(d) {
					self.userdetails = d;
					alert('User Updated Successfully...')
					$location.path('/login');
					}, function(errResponse) {
						console.error('--> UserController : Error while updating User...');
					});
			};

			self.logout = function() {
				console.log("--> UserController : calling logout method.");
				
				$rootScope.currentUser = {};
				
			//	$cookieStore.remove('currentUser');
				UserService.logout();
				
				console.log("-->UserController : User Logged out.");
				$route.reload();
				$window.location.reload(true);
				$location.path('/');
			}

			
			

			
			self.deleteUser = function(id) {
				console.log("-->UserController : calling deleteUser method.");
				UserService.deleteUser(id).then(
						self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting userdetail...')
						});
			};
			
				self.statusUser = function(userdetail, id) {
					console.log("-->UserController : calling statusUser() method : User id is : "+id);
					console.log("-->UserController", self.userdetail);
					UserService.statusUser(userdetail, id).then(
							self.fetchAllUsers,
							function(errResponse) {
								console.error("Error while showing status of the userdetail...")
							});
					
				};
				self.onlineUser = function(userdetail, id) {
					console.log("-->UserController : calling onlineUser() method : User id is : "+id);
					console.log("-->UserController", self.userdetail);
					UserService.onlineUser(userdetail, id).then(
							self.fetchAllUsers,
							function(errResponse) {
								console.error("Error while showing online userdetail...")
							});
					
				};
				self.authenticateUser = function(userdetail) {
					console.log("-->UserController : calling authenticateUser method.");
					UserService.authenticateUser(userdetail).then(
							function(d){
							self.userdetail=d;
							$rootScope.currentUser=self.userdetail;
							console.log(self.userdetail.errorMessage);
							$location.path('/view_blog');
							},
							function(errResponse) {
								console.error('Error while fetching User...')
							});
				};
				
				self.fetchAllUsers();
				self.register = function() {
					{
						console.log("--> UserController : calling register() method.", self.userdetail);
						self.createUser(self.userdetail);
						console.log('Saving new user...');
					}
					$location.path('/login');
					self.reset();
				};
				
				self.editUser = function() {
					{
					console.log("-->UserController : calling editUser method : getting user with id : " ,self.userdetail);
					self.updateUser(self.userdetail);
					console.log('Updating user...');
				}
								$location.path('/edit-profile');
							
							
				};
				
			/*****************************************************************************/
				self.submit = function() {
					{
						console.log("--> UserController : calling submit() method.", self.userdetail);
						self.createUser(self.userdetail);
						console.log('Saving new User', self.userdetail);
					}
					self.reset();
				};			
				
				self.edit = function(id) {
					console.log("id to be edited : "+id);
					for (var i = 0; i < self.userdetails.length; i++) {
						if (self.userdetails[i].id === id) {
							self.userdetail= angular.copy(self.userdetails[i]);
							break;
						}
					}
				};

				self.remove = function(id) {
					console.log('id to be deleted', id);
					if (self.userdetail.id === id) {
						self.reset();
					}
					self.deleteUser(id);
				};

				self.reset = function() {
					console.log('submit a new User', self.userdetail);
					self.userdetail = {
							errorCode : '',
							errorMessage : '',
							userId : '',
							userPassword : '',
							userName : '',
							userRole : '',
							userGender : '',
							userEmail : '',
							userContactNo: '',
							userAddress: '',
							userDOB:'',
							userStatus:'',
							userIsOnline:''
					};
					$scope.myForm.$setPristine(); // reset form...
				};
				
				
				
		} ]);

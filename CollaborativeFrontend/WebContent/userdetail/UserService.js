/**
 * 
 */'use strict';

app.factory('UserService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("UserService...")

			var BASE_URL = 'http://localhost:9000/CollaborativeBackend'
				return {
				getSelectedUser : function(id) {
					console.log("-->UserService : calling getSelectedUser() method with id : " + id);
					return $http
								.get(BASE_URL+'/userdetail/'+ id)
								.then(function(response) {
									$rootScope.selectedUser = response.data;
									return response.data;
								},
								function(errResponse) {
									console.error('Error while Fetching User.');
									return $q.reject(errResponse);
								});
				},
				fetchAllUsers : function() {
					console.log("--> UserService : calling 'fetchAllUsers' method.");
					return $http
								.get(BASE_URL + '/userdetails')
								.then(function(response) {
									console.log("got it ");
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Users');
									return $q.reject(errResponse);
								});
				},
				createUser : function(userdetail) {
					console.log("--> UserService : calling 'createUser' method.");
					return $http
								.post(BASE_URL + '/userdetail/', userdetail)
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while creating userdetail');
									return $q.reject(errResponse);
								});
				},
				updateUser : function(userdetail, id) {
					console.log("--> UserService : calling 'updateUser' method with id : "+id);
					return $http
								.put(BASE_URL+'/userdetail/'+id,userdetail)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating User');
									return $q.reject(errResponse);
								});
				},
				
				deleteUser : function(id) {
					console.log("--> UserService : calling 'updateUser' method with id : "+id);
					return $http
								delete(BASE_URL+'/user/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while deleting User');
									return $q.reject(errResponse);
								});
				},

				statusUser : function(userdetail, id) {
					console.log("-->UserService : calling statusUser() method : getting user with id : " + id);
					return $http
								.put(BASE_URL+'/statusUser/'+id, userdetail)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while showing staus of User.");
									return $q.reject(errResponse);
								});
				},
				
				onlineUser : function(userdetail, id) {
					console.log("-->UserService : calling onlineUser() method : getting user with id : " + id);
					return $http
								.put(BASE_URL+'/onlineUser/'+id, userdetail)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while user is online");
									return $q.reject(errResponse);
								});
				},
				authenticateUser : function(userdetail ) {
					console.log("--> UserService : calling 'authenticateUser' ");
					return $http
								.post(BASE_URL+'/user/authenticate/',userdetail)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while authenicating User');
									return $q.reject(errResponse);
								});
				},	
				logout: function() {
					console.log("--> UserService : calling 'logout' method.");
					return $http
								.get(BASE_URL+'/user/logout')
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error('Error while logging out.');
									return $q.reject(errResponse);
								}
							);
					
					
				},

			};
		}]);
				
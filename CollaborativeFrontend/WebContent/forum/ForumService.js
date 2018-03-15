/**
 * 
 */'use strict';

app.factory('ForumService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("ForumService...")

			var BASE_URL = 'http://localhost:9000/CollaborativeBackend'
return {
				
				getSelectedForum : function(id) {
					console.log("-->ForumService : calling getSelectedForum() method with id : " + id);
					return $http
								.get(BASE_URL+'/forum/'+ id)
								.then(function(response) {
									$rootScope.selectedForum = response.data;
									return response.data;
								},
								function(errResponse) {
									console.error('Error while Fetching Forum.');
									return $q.reject(errResponse);
								});
				},
				
				fetchAllForums : function() {
					console.log("--> ForumService : calling 'fetchAllForums' method.");
					return $http
								.get(BASE_URL + '/forums')
								.then(function(response) {
									console.log("got it ");
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Forums');
									return $q.reject(errResponse);
								});
				},

				fetchAllApprovedForums : function() {
					console.log("--> ForumService : calling 'fetchAllApprovedForums' method.");
					return $http
								.get(BASE_URL + '/forums')
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Forums');
									return $q.reject(errResponse);
								});
					
				},
				createForum : function(forum) {
					console.log("--> ForumService : calling 'createForum' method.");
					return $http
								.post(BASE_URL + '/forum/', forum)
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while creating forum');
									return $q.reject(errResponse);
								});
				},
				
				updateForum : function(forum, id) {
					console.log("--> ForumService : calling 'updateForum' method with id : "+id);
					return $http
								.put(BASE_URL+'/forum/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating Forum');
									return $q.reject(errResponse);
								});
				},
				
				deleteForum : function(id) {
					console.log("--> ForumService : calling 'updateForum' method with id : "+id);
					return $http
								delete(BASE_URL+'/forum/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while deleting Forum');
									return $q.reject(errResponse);
								});
				},
				
				approveForum : function(forum, id) {
					console.log("-->ForumService : calling approveForum() method : getting forum with id : " + id);
					return $http
								.put(BASE_URL+'/approveForum/'+ id, forum)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while approving Forum");
									return $q.reject(errResponse);
								});
				},
				
				rejectForum : function(forum, id) {
					console.log("-->ForumService : calling rejectForum() method : getting forum with id : " + id);
					return $http
								.put(BASE_URL+'/rejectForum/'+ id, forum)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while approving Forum");
									return $q.reject(errResponse);
								});
				},
				
				usercountForum : function(forum, id) {
					console.log("-->ForumService : calling usercountForum() method : getting forum with id : " + id);
					return $http
								.put(BASE_URL+'/usercountForum/'+id, forum)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while liking Forum.");
									return $q.reject(errResponse);
								});
				}
			};
}]);
				
				
				
			

			
'use strict';

app.factory('JobService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("JobService...")

			var BASE_URL = 'http://localhost:9000/CollaborativeBackend'
				return {
				getSelectedJob : function(id) {
					console.log("-->JobService : calling getSelectedJob() method with id : " + id);
					return $http
								.get(BASE_URL+'/job/'+ id)
								.then(function(response) {
									$rootScope.selectedJob = response.data;
									return response.data;
								},
								function(errResponse) {
									console.error('Error while Fetching Job.');
									return $q.reject(errResponse);
								});
				},
				fetchAllJobs : function() {
					console.log("--> JobService : calling 'fetchAllJobs' method.");
					return $http
								.get(BASE_URL + '/jobs')
								.then(function(response) {
									console.log("got it ");
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Jobs');
									return $q.reject(errResponse);
								});
				},
				
				

				createJob : function(job) {
					console.log("--> JobService : calling 'createJob' method.");
					return $http
								.post(BASE_URL + '/job/', blog)
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while creating job');
									return $q.reject(errResponse);
								});
				},
				
				updateJob : function(job, id) {
					console.log("--> JobService : calling 'updateJob' method with id : "+id);
					return $http
								.put(BASE_URL+'/job/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating Job');
									return $q.reject(errResponse);
								});
				},
				
				deleteJob : function(id) {
					console.log("--> JobService : calling 'deleteJob' method with id : "+id);
					return $http
								delete(BASE_URL+'/job/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while deleting Job');
									return $q.reject(errResponse);
								});
				},
				
				applyJob : function(job, id) {
					console.log("-->JobService : calling applyJob() method : getting job with id : " + id);
					return $http
								.put(BASE_URL+'/applyJob/'+ id, job)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while applying Job");
									return $q.reject(errResponse);
								});
				},
				
				rejectJob : function(job, id) {
					console.log("-->JobService : calling rejectJob() method : getting job with id : " + id);
					return $http
								.put(BASE_URL+'/rejectJob/'+ id, job)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while approving Job");
									return $q.reject(errResponse);
								});
				},
				
				statusJob : function(job, id) {
					console.log("-->JobService : calling statusJob() method : getting job with id : " + id);
					return $http
								.put(BASE_URL+'/statusjob/'+id, job)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while staus of job.");
									return $q.reject(errResponse);
								});
				}
				
			};
		}]);
			
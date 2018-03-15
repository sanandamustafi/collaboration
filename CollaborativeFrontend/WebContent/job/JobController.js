'use strict';

app.controller('JobController', [
		'$scope',
		'JobService',
		'$location',
		'$rootScope',
		function($scope, JobService, $location, $rootScope) {
			console.log("JobController...")

			var self = this;
			self.job = {
					errorCode : '',
					errorMessage : '',
					jobId : '',
					jobProfile : '',
					jobQualification : '',
					jobstatus : '',
					jobpostdate : '',
					jobDescription : ''
					
			}
			self.jobs = [];

			self.getSelectedJob = function(id) {
				console.log("-->JobController : calling getSelectedJob method : getting job with id : " + id);
				JobService.getSelectedJob(id).then(
						function(d) {
							self.job = d;
							$location.path('/view_job');
						}, 
						function(errResponse) {
							console.error('Error while fetching Job...');
						});
			};
			self.fetchAllJobs = function() {
				console.log("--> JobController : calling fetchAllJobs method.");
				JobService.fetchAllJobs().then(
						function(d) {
							self.jobs = d;
							console.log("yes ");
						}, function(errResponse) {
							console.error('Error while fetching Jobs...');
						});
			};
			self.createJob = function(job) {
				console.log("--> JobController : calling createJob method.");
				JobService.createJob(job).then(
						function(d) {
							self.jobs = d;
							alert('Job Created Successfully...')
						},
						function(errResponse) {
							console.error('Error while creating job...');
						});
			};
			self.updateJob = function(job, id) {
				console.log("-->JobController : calling updateJob method.");
				JobService.updateJob(job, id).then(
						self.fetchAllJobs,
						function(errResponse) {
							console.error('Error while updating job...')
						});
			};

			self.deleteJob = function(id) {
				console.log("-->JobController : calling deleteJob method.");
				JobService.deleteJob(id).then(
						self.fetchAllJobs,
						function(errResponse) {
							console.error('Error while deleting job...')
						});
			};
			self.applyJob = function(job, id) {
				console.log("-->JobController : calling applyJob() method : getting job with id : " + id);
				console.log("-->JobController",self.job);
				JobService.applyJob(job, id).then(
						self.fetchAllJobs,
						function(errResponse) {
							console.error("Error while applying job...")
						});
			};

			self.rejectJob = function(job, id) {
				console.log("-->JobController : calling rejectJob() method : getting job with id : " + id);
				console.log("-->JobController",self.job);
				JobService.rejectJob(job, id).then(
						self.fetchAllJobs,
						function(errResponse) {
							console.error("Error while rejecting job...")
						});
			};
			self.statusJob= function(job, id) {
				console.log("-->JobController : calling statusJob() method : Job id is : "+id);
				console.log("-->JobController", self.job);
				JobService.statusJob(job, id).then(
						self.fetchAllJobs,
						function(errResponse) {
							console.error("Error while status of job...")
						});
				
			};
			self.fetchAllJobs();
			/*****************************************************************************/
			self.submit = function() {
				{
					console.log("--> JobController : calling submit() method.", self.job);
					self.createBlog(self.job);
					console.log('Saving new Job', self.job);
				}
				self.reset();
			};			
			
			self.edit = function(id) {
				console.log("id to be edited : "+id);
				for (var i = 0; i < self.jobs.length; i++) {
					if (self.jobs[i].id === id) {
						self.job = angular.copy(self.jobs[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.job.id === id) {
					self.reset();
				}
				self.deleteJob(id);
			};

			self.reset = function() {
				console.log('submit a new Job', self.job);
				self.blog = {
						errorCode : '',
						errorMessage : '',
						jobId : '',
						jobProfile : '',
						jobQualification : '',
						jobstatus : '',
						jobpostdate : '',
						jobDescription : ''
				};
				$scope.myForm.$setPristine(); // reset form...
			};
		} ]);
			
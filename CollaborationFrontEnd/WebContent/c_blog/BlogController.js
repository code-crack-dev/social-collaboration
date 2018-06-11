app.controller('BlogController', function($scope, $location, BlogService) {
	console.log("Enter in Blog Controller")
	$scope.blogs = [];
	$scope.blogComments = [];
	$scope.blogComment = {
		id : '',
		blog_id : '',
		comment : '',
		postedAt : '',
		rating : '',
		username : ''
	};
	$scope.blog = {
		blog_id : '',
		blog_title : '',
		blog_description : '',
		username : '',
		status : '',
		date_time : '',
		rejected : '',
		like : '',
		unlike : '',
		errorMsg : '',
		errorCode : ''
	};

	$scope.addBlog = function() {
		console.log("Entering Add Blog")
		BlogService.addBlog($scope.blog).then(function(response) {
			alert("Blog Added. Waiting for admin approval")
			console.log("Add Blog Success " + response.status)
			$location.path("/viewBlogs")
		});
	}

})
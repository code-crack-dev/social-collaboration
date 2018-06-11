app.factory('BlogService', function($http, $q, $rootScope) {

	console.log("Entering BlogService")
	var BASE_URL = 'http://localhost:8080/CollaborationController/'
	return{
		addBlog: function(blog)
		{
			console.log("Entering Function Add Blog")
			return $http.post(BASE_URL + "addBlog", blog)
			.then(function(response)
				{
					console.log(response.status)
					return response.status
				},function(response)
				{
					console.log(response.status)
					return response.status
				});
		},
		
		listBlog: function()
		{
			console.log("Entering Blog List--service listBlog")
			return $http.get(BASE_URL + "admin_getAllBlogs")
			.then(function(response) 
			{
				console.log("list of blog in console SHAHRUKH")
				console.log(response.data)
				console.log(response.status)
				return response.data
			})
		},
		
	}

})
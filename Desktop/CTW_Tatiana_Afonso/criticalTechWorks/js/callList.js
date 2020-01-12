function callAjax(data){
var api_url = 'http://www.mocky.io/v2/5e1b608c3100007a004f333f'
jQuery.ajax({ 
	 url: api_url, 
	 type: 'GET', 
	 contentType: 'application/json',
	 data: data,
	 dataType: 'jsonp',
	 timeout: 5000,
	 success: function(data) {
	  localStorage.setItem('data',JSON.stringify(data));
	  for(obj in data){	
		$('#htmlCode').append(
		'<div class="col-lg-3 col-md-6 mb-4 ">'+
			'<div class="card h-100">'+
			  '<img class="card-img-top " src="'+ data[obj].picture + '" alt="'+data[obj].city+'" style="height: calc(60%);">'+
			  '<div class="card-body">'+
				'<h4 class="card-title">'+data[obj].city+'</h4>'+
			  '</div>'+
			  '<div class="card-footer">'+
				'<a href="detail.html?id='+ data[obj].city + '" class="btn btn-primary">More details</a>'+
			  '</div>'+
			'</div>'+
		  '</div>');		  
		}	  
	}
})
}

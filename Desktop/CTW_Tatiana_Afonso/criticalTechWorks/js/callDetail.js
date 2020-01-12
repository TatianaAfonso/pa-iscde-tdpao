
function callAjax(data){
	
var city,api_url = '';

//code for get URL parameter
 var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++){
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == "id"){
            city = decodeURIComponent(sParameterName[1]);
        }
    }
	
var dataList = JSON.parse(localStorage.getItem("data"));
for(obj in dataList){
	if(dataList[obj].city===city){
		api_url = dataList[obj].url;
	}
}
if(api_url!==''){
	
jQuery.ajax({ 
	 url: api_url, 
	 type: 'GET', 
	 contentType: 'application/json',
	 data: data,
	 dataType: 'jsonp',
	 timeout: 5000,
	 success: function(data) {
	 console.log(data);
	 $("li[id^='breadcrumb']:last").append(data.city);
		$('#detail').append(
		'<div class="col-lg-6">'+
        '<img class="img-fluid rounded mb-4" src="'+data.picture +'"alt=""></div>'
		+ '<div class="col-lg-6">'
        + '<h2> About '+data.city+'</h2>'
        +'<p>'+data.description+'</p>'
		+ '</div>');
		} 
})
}
else{
	$('#alert').append('<div class="alert alert-warning" role="alert">'+
  'This article not exist in the database!'+'</div>');
}

}

$(document).ready(function(){
	isAoI();
});
function isAoI(){
	var u = navigator.userAgent;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		$("#header_").css({
			"position":"absolute",
			"top":"23%"	
		});
	}
}

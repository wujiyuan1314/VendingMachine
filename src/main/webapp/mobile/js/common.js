$(document).ready(function(){
	isAoI();
});

function isAoI(){
	var u = navigator.userAgent;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		$(".headertop").css("padding-top", "11px");
		$(".headeratop").css("margin", "26px 0 0 0");
	}
}

//跳转到主页面
function gotohome(){
    window.location.href="/mobile/mall/page/index.jsp";
}

function getYuanByFen(fen){
    if (isNaN(parseInt(fen))){return 0.00;}
	var yuan=parseInt(fen)/100;
	yuan=yuan.toFixed(2);
	return yuan;
}

function onTopClick(id){
	document.getElementById(id).scrollIntoView();
}
/**
 * 登陆
 * @param id
 * @returns
 */
function open(id){
	if(confirm("确定要开机吗?")){
		window.location.href=basePath+"manage/"+id+"/login";
	 }
}
/**
 * 关机
 * @param id
 * @returns
 */
function shutdown(id){
	if(confirm("确定要关机吗?")){
		 $.ajax({
		        type: "post",
		        dataType: "html",
		        url: basePath+"manage/"+id+"/shutdown",
		        success: function (data) {
		        	alert(data);
		        }
		    });
	 }
}
/**
 * 重启
 * @param id
 * @returns
 */
function reboot(id){
	if(confirm("确定要重启吗?")){
		window.location.href=basePath+"manage/"+id+"/reboot";
	 }
}
/**
 * 自清洗
 * @param id
 * @returns
 */
function autoClean(id){
	if(confirm("确定要自清洗吗?")){
		window.location.href=basePath+"manage/"+id+"/autoClean";
	 }
}
/**
 * 自检
 * @param id
 * @returns
 */
function selfCheck(id){
	if(confirm("确定要自检吗?")){
		window.location.href=basePath+"manage/"+id+"/selfCheck";
	 }
}
/**
 * 广告投放
 * @param id
 * @returns
 */
function adPuton(id){
	var adId=$("#adId").val();
	if(confirm("确定要投放吗?")){
		window.location.href=basePath+"manage/"+id+"/"+adId+"/adPuton";
	 }
}
/**
 * 二维码投放
 * @param id
 * @returns
 */
function adPuton(id){
	var shopQrcode=$("#shopQrcode").val();
	if(confirm("确定要投放吗?")){
		window.location.href=basePath+"manage/"+id+"/"+shopQrcode+"/adPuton";
	 }
}
/**
 * 详情
 * @param id
 * @returns
 */
function detail(id){
	window.location.href=basePath+"machine/"+id+"/detail";
}
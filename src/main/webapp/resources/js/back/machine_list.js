/**
 * 开机
 * @param id
 * @returns
 */
function open(id){
	if(confirm("确定要开机吗?")){
		window.location.href=basePath+"machine/"+id+"/open";
	 }
}
/**
 * 关机
 * @param id
 * @returns
 */
function shutdown(id){
	if(confirm("确定要关机吗?")){
		window.location.href=basePath+"machine/"+id+"/shutdown";
	 }
}
/**
 * 重启
 * @param id
 * @returns
 */
function reboot(id){
	if(confirm("确定要重启吗?")){
		window.location.href=basePath+"machine/"+id+"/reboot";
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
/**
 * 登陆
 * @param id
 * @returns
 */
function open(id){
	if(confirm("确定要登陆吗?")){
		var url=basePath+"manage/"+id+"/login";
		$.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		})
	 }
}
/**
 * 关机
 * @param id
 * @returns
 */
function shutdown(id){
	if(confirm("确定要关机吗?")){
		 var url=basePath+"manage/"+id+"/shutdown";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 重启
 * @param id
 * @returns
 */
function reboot(id){
	if(confirm("确定要重启吗?")){
		 var url=basePath+"manage/"+id+"/reboot";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 自清洗
 * @param id
 * @returns
 */
function autoClean(id){
	if(confirm("确定要自清洗吗?")){
		var url=basePath+"manage/"+id+"/autoClean";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 自检
 * @param id
 * @returns
 */
function selfCheck(id){
	if(confirm("确定要自检吗?")){
		var url=basePath+"manage/"+id+"/selfCheck";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 设置设备参数
 * @param id
 * @returns
 */
function setDevParam(id){
	if(confirm("确定要设置设备参数吗?")){
		var url=basePath+"manage/"+id+"/setDevParam";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				$(".errormsg").html(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 广告投放
 * @param id
 * @returns
 */
function adPuton(id){
	var adId=$("#id").val();//广告主键
	if(confirm("确定要投放吗?")){
		var url=basePath+"manage/"+id+"/"+adId+"/setAdItemList";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 二维码投放
 * @param id
 * @returns
 */
function qrcodePuton(id){
	var shopQrcode=$("#id").val();//二维码主键
	if(confirm("确定要投放吗?")){
		var url=basePath+"manage/"+id+"/"+shopQrcode+"/setQR";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
/**
 * 实时查询杯数
 * @param id
 * @returns
 */
function getDevList(id){
	var url=basePath+"manage/"+id+"/getDevList";
	 $.post(url,'',function(res){
		var data=eval("("+res+")");
		var success=data.success;
		var msg=data.msg;
		if(success==0){
			alert(msg);
		}else if(success==1){
			alert(msg);
			window.location.reload();
		}
	 })
}
/**
 * 查询设备参数
 * @param id
 * @returns
 */
function getDevParam(id){
	var url=basePath+"manage/"+id+"/getDevParam";
	 $.post(url,'',function(res){
		var data=eval("("+res+")");
		var success=data.success;
		var msg=data.msg;
		if(success==0){
			alert(msg);
		}else if(success==1){
			alert(msg);
			window.location.reload();
		}
	 })
}
/**
 * 设置机器识别码
 * @param id
 * @returns
 */
function setCsrCode(id){
	var machineId=new Array();
 	var length=0;
 	$("input[id='Id']:checked").each(function(){
 		length=machineId.push($(this).val());
 	})
 	if(length==0){
 		$(".csrCodeerror").html("请选择一条记录");
 		return;
 	}
 	if(length>1){
 		$(".csrCodeerror").html("只能选择一条记录");
 		return;
 	}
 	var id=machineId[0];
 	var csrCode=$("#csrCode").val();
 	if(transusercode==''){
 		$(".csrCodeerror").html("请输入机器码");
 		return;
 	}
	if(confirm("确定要设置吗?")){
		var url=basePath+"manage/"+id+"/"+csrCode+"/setCsrCode";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				$(".csrCodeerror").html(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
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
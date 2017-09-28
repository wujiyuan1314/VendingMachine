//跳转到我的页面
function gotoMyPage(){
    var backUrl=basePath6+"/mobile/goodss";
	if (backUrl ==""){
		history.back();
	} else{
		window.location.href= backUrl ;
	}//end of if
}  
/**
 * 免费购买
 * @param id
 * @returns
 */
function quickAddorder(id){
	var machinecode=$("#machinecode").val();
	var map={};
	map['id']=id;
	map['machinecode']=machinecode;
	map['wechatpubNo']=wechatpubNo;
	map['usercode']=usercode;
	var jsonMap=JSON.stringify(map);
	
	 var params={
		jsonMap:jsonMap
    }
	var url=basePath+"manage/freePay";
	$.ajax({url:url,
		type:'post',
		async: false,      //ajax同步
		dataType:"html",
		traditional: true,//这个设置为true，data:{"steps":["qwe","asd","zxc"]}会转换成steps=qwe&steps=asd&...
		data:params,//URL参数
		success:function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
			}else{
				alert(msg);
				window.location.reload();
			}
		},
	    error:function(){
           alert("错误");
        }
	});
}

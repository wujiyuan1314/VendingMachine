//跳转到我的页面
function gotoMyPage(usercode){
    var backUrl=basePath6+"mobile/"+usercode+"/goodss";
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
	var usercode=$("#usercode").val();
	var heat=0;
	if(machinecode==''){
		alert("请输入机器码");
		return;
	}
	var map={};
	map['id']=id;
	map['machinecode']=machinecode;
	map['usercode']=usercode;
	map['heat']=heat;
	var jsonMap=JSON.stringify(map);
	var params={
			jsonMap:jsonMap
    }
	var url=basePath6+"mobile/freePay";
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
				alert(msg);
			}else{
				alert(msg);
				window.opener=null;
				window.open('','_self');
				window.close();
			}
		},
	    error:function(){
           alert("错误");
        }
	});
}

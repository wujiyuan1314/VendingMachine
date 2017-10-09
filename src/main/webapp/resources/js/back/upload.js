//图片上传
function doUpload(obj) {
	var file=$("input[type='file']");
	$("input[type='file']").each(function(){
		if($(this).attr("id")!==('file_'+obj)){
			$(this).val("");
		}
	})
     $("#basic_validate").ajaxSubmit({
    		type: "POST",
    		url:basePath+'upload/pic1',
    		dataType: "text",
    	    success: function(responseText){
    	    	var data=eval("("+responseText+")");//转化为json串
    	    	var success=data.success;
    	    	var msg=data.msg;
    	    	if(success==0){
    	    		$(".info"+obj).html(msg);
    	    	}else if(success==1){
    	    		$(".info"+obj).html("上传成功");
    	    		$("#"+obj).val(msg);
    	    		$(".img"+obj).attr("src",msg);
    	    		$(".img"+obj).css("width","70px");
    	    		$(".img"+obj).css("height","50px");
    	    	}
    		}
    	})
}
//文件上传
function doUploadFile(obj) { 
	var file=$("input[type='file']");
	$("input[type='file']").each(function(){
		if($(this).attr("id")!==('file_'+obj)){
			$(this).val("");
		}
	})
    $("#basic_validate").ajaxSubmit({
   		type: "POST",
   		url:basePath+'upload/file',
   		dataType: "text",
   		success: function(responseText){ 
	    	var data=eval("("+responseText+")");//转化为json串
	    	var success=data.success;
	    	var msg=data.msg;
	    	if(success==0){
	    		$(".info"+obj).html(msg);
	    	}else if(success==1){
	    		$(".info"+obj).html("上传成功");
	    		$("#"+obj).val(msg);
	    	}
		}
   	})
} 
//视频上传
function doUploadVideo(obj) {  
	var file=$("input[type='file']");
	$("input[type='file']").each(function(){
		if($(this).attr("id")!==('file_'+obj)){
			$(this).val("");
		}
	})
    $("#basic_validate").ajaxSubmit({
   		type: "POST",
   		url:basePath+'upload/video',
   		dataType: "text",
   		success: function(responseText){
	    	var data=eval("("+responseText+")");//转化为json串
	    	var success=data.success;
	    	var msg=data.msg;
	    	if(success==0){
	    		$(".info"+obj).html(msg);
	    	}else if(success==1){
	    		$(".info"+obj).html("上传成功");
	    		$("#"+obj).val(msg);
	    	}
		}
   	})
} 

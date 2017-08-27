function doUpload(obj) {  
     $("#basic_validate").ajaxSubmit({
    		type: "POST",
    		url:basePath+'upload/pic1',
    		dataType: "text",
    	    success: function(data){
    	    		alert("上传成功");
    	    		$("#"+obj).val(data);
    		}
    	})
} 

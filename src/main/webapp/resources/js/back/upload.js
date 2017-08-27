function doUpload(obj) {  
    /**$.ajax({  
          url: basePath+'upload/pic1' ,  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {  
              alert(returndata);  
          },  
          error: function (returndata) {  
              alert(returndata);  
          }  
     }); */
     $("#basic_validate").ajaxSubmit({
    		type: "POST",
    		url:basePath+'upload/pic1' ,
    		dataType: "json",
    	    success: function(data){
    	    		alert(data);
    	    		$("#"+obj).val(data);
    		}
    	})
} 

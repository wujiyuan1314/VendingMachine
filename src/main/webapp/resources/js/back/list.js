/**
 * 实现全选功能
 */
function selectAll(obj) {
	var selected = document.getElementById("all");
	
	var IDs = document.getElementsByName(obj);
	if(selected.checked == true) {
		for(var i = 0; i < IDs.length; i++){  
			if(IDs[i].checked == false){  
				IDs[i].checked = true;  
		    }  
		}  
	}
	else{
		for(var i = 0; i < IDs.length; i++){  
			if(IDs[i].checked == true){  
				IDs[i].checked = false;
			}
		}
	}
}


/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage){
	
	document.getElementById("currentPage").value = currentPage;
	//alert(document.getElementById("currentPage").value);
	//alert(currentPage);
	$("#Paramform").submit();
}

/**
 * 页面跳转
 */
function changeCurrentPage2(){
	
	document.getElementById("currentPage").value = document.getElementById("currentPageText").value;
	//alert(document.getElementById("currentPage").value);
	//alert(document.getElementById("currentPageText").value);
	document.forms[0].submit();
	//document.getElementById("collegeForm").submit();
}

/**
 * js实现reset功能，添加指令页面
 */

function reset(){
	document.getElementById(collegeForm).reset();
}

/**
 * 提交表单
 * 
 */

function doSubmit(){
	document.getElementById("addForm").submit();
}
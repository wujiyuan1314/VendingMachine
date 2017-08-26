/**
 * 实现全选功能
 */
function selectAll(obj) {
	var selected = document.getElementById("all");
	var IDs = document.getElementsByName(obj);
	if(selected.checked == true) {
		$("input[name='Id']").each(function() {
			var spanclass=$(this).parent().attr('class');
	        if (typeof(spanclass) =='undefined'||spanclass=='') {
	        	$(this).parent().addClass("checked");
	        }
	    });
	}
	else{
		$("input[name='Id']").each(function() {
			var spanclass=$(this).parent().attr('class');
	        if (spanclass =='checked') {
	        	$(this).parent().removeClass("checked");
	        }
	    });
	}
}
/**
 * 批量删除
 */
function dels(pre){
	var ids="";
	$("input[name='Id']").each(function() {
        if ($(this).parent().attr('class') =='checked') {
              if(ids==''){
            	  ids+=$(this).val();  
              }else{
            	  ids+=","+$(this).val(); 
              }
        }
    });
	if(ids==""){
		alert("请至少选择一条数据");
		return;
	}
	if(confirm("确定要删除吗?")){
		var url=basePath+pre+"/dels";
		var params={ids:ids};
		$.post(url,params,function(res){
			window.location.reload();
		});
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
/**
 * placeholder兼容浏览器设置
 * 
 */
function placeholder(nodes,pcolor) {
    if(nodes.length && !("placeholder" in document_createElement_x("input"))){
        for(i=0;i<nodes.length;i++){
            var self = nodes[i],
                placeholder = self.getAttribute('placeholder') || '';     
            self.onfocus = function(){
                if(self.value == placeholder){
                   self.value = '';
                   self.style.color = "";
                }               
            }
            self.onblur = function(){
                if(self.value == ''){
                    self.value = placeholder;
                    self.style.color = pcolor;
                }              
            }                                       
            self.value = placeholder;  
            self.style.color = pcolor;              
        }
    }
  } 
/**
 * 左侧菜单点击变化
 */
$(".sidebar ul li ul li").click(function(){
	//var current=$(this).attr("class");
	//if(current!='active'){
	//	$(".sidebar").find("li").addClss("submenu");
		//$(this).addClass("active");
	//}
})
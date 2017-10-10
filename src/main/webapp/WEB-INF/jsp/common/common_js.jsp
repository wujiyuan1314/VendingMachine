<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path2 + "/";
	//System.out.println(basePath);
%>
<script type="text/javascript">
var basePath="<%=basePath2%>";
</script>
<script src="<%=basePath2 %>resources/js/excanvas.min.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.min.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.ui.custom.js"></script> 
<script src="<%=basePath2 %>resources/js/bootstrap.min.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.peity.min.js"></script> 
<script src="<%=basePath2 %>resources/js/fullcalendar.min.js"></script> 
<script src="<%=basePath2 %>resources/js/matrix.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.gritter.min.js"></script>
<script src="<%=basePath2 %>resources/js/matrix.chat.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.validate.js"></script> 
<script src="<%=basePath2 %>resources/js/matrix.form_validation.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.wizard.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.uniform.js"></script> 
<script src="<%=basePath2 %>resources/js/select2.min.js"></script> 
<script src="<%=basePath2 %>resources/js/matrix.popover.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.dataTables.min.js"></script> 
<script src="<%=basePath2 %>resources/js/jquery.form.js"></script> 
<script src="<%=basePath2 %>resources/js/matrix.tables.js"></script> 
<script src="<%=basePath2 %>resources/js/bootstrap-treeview.js"></script>   
<script src="<%=basePath2 %>resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script> 
<script src="<%=basePath2 %>resources/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="<%=basePath2 %>resources/js/back/list.js"></script> 
<script src="<%=basePath2 %>resources/js/back/upload.js"></script>
<script src="<%=basePath2 %>resources/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="<%=basePath2 %>resources/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=basePath2 %>resources/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }
// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>
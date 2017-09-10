<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path4 = request.getContextPath();
	String basePath4 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path4 + "/";
%>
<head>
<title>自动售货机管理系统</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath4 %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath4 %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath4 %>resources/css/matrix-login.css" />
<link href="<%=basePath4 %>resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="login" method="post">
				 <div class="control-group normal_text"> <h3><img src="<%=basePath4 %>resources/img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input name="userName" type="text" placeholder="用户名" />
                            <span style="color:#d66262;width:20px;font-size:15px;"> ${erroruserName}</span>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="password" type="password" placeholder="密码" />
                            <span style="color:#d66262;width:20px;font-size:15px;"> ${errorpassword}</span>
                        </div>
                    </div>
                </div>
                 <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span>
                            <input name="verificode" type="verificode" placeholder="验证码" style="width:240px;"/>
                            <img id="captcha" alt="验证码" src="<%=basePath4%>/code" data-src="${basePath4 }/code?t=" style="vertical-align:middle;border-radius:4px;width:80px;height:32px;margin-bottom:4px;cursor:pointer;">
                            <span style="color:#d66262;width:20px;font-size:15px;"> ${errorverificode}</span>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right"><input type="submit"  value="登录" class="btn btn-success" /></span>
                </div>
            </form>
        </div>
        
        <script src="<%=basePath4 %>resources/js/jquery.min.js"></script>  
        <script src="<%=basePath4 %>resources/js/matrix.login.js"></script> 
        <script type="text/javascript">
        var basePath4="<%=basePath4%>";
        // 验证码
        $("#captcha").click(function() {
            var $this = $(this);
            var url = basePath4+$this.data("src") + new Date().getTime();
            $(this).attr("src", url);
        });
        </script>
    </body>
</html>
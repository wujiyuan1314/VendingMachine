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
            <form id="loginform" class="form-vertical" action="index.html">
				 <div class="control-group normal_text"> <h3><img src="<%=basePath4 %>resources/img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right"><a type="submit" href="index.html" class="btn btn-success" /> 登录</a></span>
                </div>
            </form>
            <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">请输入正确的用户名密码.</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a></span>
                    <span class="pull-right"><a class="btn btn-info"/></a></span>
                </div>
            </form>
        </div>
        
        <script src="<%=basePath4 %>resources/js/jquery.min.js"></script>  
        <script src="<%=basePath4 %>resources/js/matrix.login.js"></script> 
    </body>
</html>
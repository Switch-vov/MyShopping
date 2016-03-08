<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/css/common.css" />

  </head>
  <%-- 登录界面 --%>
  <body>
    <h1>登录界面</h1>
    <form action="/MyShoping/GoHallUI" method="post">
	    <table border="1px" style="border-collapse: collapse;">
	    	<tr>
	    		<td>用户ID：</td>
	    		<td><input type="text" name="userid"/></td>
	    	</tr>
	    	<tr>
	    		<td>密&nbsp;&nbsp;&nbsp;码：</td>
	    		<td><input type="password" name="password" /></td>
	    	</tr>
	    	<tr>
	    		<td><input type="submit" value="登录"/></td>
	    		<td><input type="reset" value="清空" /></td>
	    	</tr>
	    </table>
    </form>
    <%
    	String err = (String)request.getAttribute("err");
    	if(err != null){
     %>
     <span style="color: red;">
     	<%=err %>
     </span>
     <% } %>
  </body>
</html>

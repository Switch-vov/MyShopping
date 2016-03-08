<%@ page import="com.pc.domain.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'hall.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/css/common.css" />
	<script type="text/javascript" language="javascript">
		function gotoMyCart(){
			window.location.href = "/MyShoping/ShoppingClServlet?type=view";
		}
	</script>
  </head>
  <%-- 购物大厅界面 --%>
  <body>
    <h1>欢迎光临购物大厅</h1>
    <table border="1px" style="border-collapse: collapse;">
    	<tr>
    		<td>书名</td>
    		<td>价格</td>
    		<td>出版社</td>
    		<td>点击购买</td>
    	</tr>
    	<%
    		// 取出request中的ArrayList
    		ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
    		for(Book book : books){
    	 %>
    	<tr>
    		<td><%= book.getName() %></td>
    		<td><%= book.getPrice() %></td>
    		<td><%= book.getPublishHouse() %></td>
    		<td><a href="/MyShoping/ShoppingClServlet?type=add&id=<%= book.getId() %>">加入购物车</a></td>
    	</tr>
    	<% } %>
    	<tr>
    		<td colspan="4"><input type="button" value="查看购物车" onclick="gotoMyCart()"/></td>
    	</tr>
    </table>
    <a href="/MyShoping">返回重新登录</a>
  </body>
</html>

<%@ page language="java" import="java.util.*,com.pc.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMyCart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/css/common.css" />

  </head>
  <%-- 购物车界面 --%>
  <body>
    <h1>我的购物车</h1>
    <a href="/MyShoping/GoHallUI">返回购物大厅</a><br />
    <form action="/MyShoping/ShoppingClServlet?type=update" method="post">
	    <table border="1px" style="border-collapse: collapse;">
	    	<tr>
	    		<td>BookID</td>
	    		<td>书名</td>
	    		<td>价格</td>
	    		<td>出版社</td>
	    		<td>数量</td>
	    		<td>删除</td>
	    	</tr>
	    	<%
	    		ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("bookList");
	    		// 测试
	    		// System.out.println(books.size());
	    		for(Book book : books){
	    	 %>
	    	<tr>
	    		<td><%= book.getId() %><input type="hidden" name="id" value="<%=book.getId() %>"/></td>
	    		<td><%= book.getName() %></td>
	    		<td><%= book.getPrice() %></td>
	    		<td><%= book.getPublishHouse() %></td>
	    		<td><input type="text" name="booknum" size="3px" value="<%=book.getShoppingNum() %>"/>本</td>
	    		<td><a href="/MyShoping/ShoppingClServlet?type=delete&id=<%= book.getId() %>">删除</a></td>
	    	</tr>
	    	<% } %>
	    	<tr>
	    		<td colspan="3"><input type="submit" value="更新购物车"/></td>
	    		<td colspan="3"><a href="/MyShoping/GoMyOrderServlet">提交订单</a></td>
	    	</tr>
	    	<tr>
	    		<td colspan="6">购物车的总价格： <%=request.getAttribute("totalPrice") %> 元 </td>
	    	</tr>
    	</table>
    </form>
  </body>
</html>

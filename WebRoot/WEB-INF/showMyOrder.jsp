<%@ page language="java" import="java.util.*,com.pc.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" language="javascript">
		function goSubmitOrder(){
			window.location.href = "/MyShoping/SubmitOrderServlet";
		}
	</script>
  </head>
  <%-- 订单页面 --%>
  <body>
    <h1>我的订单</h1>
    <table border="1px" style="border-collapse: collapse;">
    	<tr>
    		<td colspan="2">用户个人信息</td>
    	</tr>
    	<tr>
    		<td>用户名</td>
    		<td><%= ((Users)session.getAttribute("loginUser")).getName() %></td>
    	</tr>
    	<tr>
    		<td>电子邮件</td>
    		<td><%= ((Users)session.getAttribute("loginUser")).getEmail() %></td>
    	</tr>
    	<tr>
    		<td>用户级别</td>
    		<td><%= ((Users)session.getAttribute("loginUser")).getGrade() %></td>
    	</tr>
    </table>
    <br/>
    <table border="1px" style="border-collapse: collapse;">
    	<tr>
    		<td>BookID</td>
    		<td>书名</td>
    		<td>价格</td>
    		<td>出版社</td>
    		<td>数量</td>
    	</tr>
    	<%
    		// 循环的显示购物车的商品信息
    		ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("orderinfo");
    		float totalPrice = (Float)request.getAttribute("totalPrice");
    		for(Book book : books){
    	 %>
    	<tr>
    		<td><%= book.getId() %></td>
    		<td><%= book.getName() %></td>
    		<td><%= book.getPrice() %></td>
    		<td><%= book.getPublishHouse() %></td>
    		<td><%= book.getShoppingNum() %></td>
    	</tr>
    	<% } %>
    	<tr>
    		<td colspan="6">总价格：<%=totalPrice %>元</td>
    	</tr>
    </table>
    <input type="button" value="确认订单" onclick="goSubmitOrder()"/>
  </body>
</html>

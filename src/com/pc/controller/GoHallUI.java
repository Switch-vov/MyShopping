package com.pc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.domain.Book;
import com.pc.domain.Users;
import com.pc.service.BookService;
import com.pc.service.MyCart;
import com.pc.service.UsersService;
/**
 * 
 * @author Switch
 * 功能：在跳转购物大厅时进行处理
 *
 */
public class GoHallUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		// 先判断该用户是否已经登录
		if(request.getSession().getAttribute("loginUser") != null){
			// 创建BookService对象
			BookService bookService = new BookService();
			ArrayList<Book> books = bookService.getAllBook();
								
			// 将books放入request
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			return ;
		}
		
		
		
		
		
		// 得到从登录页面传递来的用户名和密码
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// 测试
		// System.out.println(userId + " " + password);
		
		// 创建Users对象
		Users user = new Users();
		try{
			// 如果userId不为数字
			user.setId(Integer.parseInt(userId));
		} catch (NumberFormatException e) {
			request.setAttribute("err", "用户ID只能为数字");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		} 
		user.setPwd(password);
		
		// 创建UsersService对象
		UsersService usersService = new UsersService();
		
		// 在UsersService验证用户是否合法
		if(usersService.checkUser(user)){
			// 成功，跳转到购物大厅
			
			// 因为其他页面可能使用到用户信息，所以将用户信息存入session
			request.getSession().setAttribute("loginUser", user);
			
			// 创建BookService对象
			BookService bookService = new BookService();
			ArrayList<Book> books = bookService.getAllBook();
			
			// 创建一个购物车
			MyCart myCart = new MyCart();
			request.getSession().setAttribute("myCart", myCart);
			
			// 将books放入request
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			
		} else {
			// 失败，返回原页面
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

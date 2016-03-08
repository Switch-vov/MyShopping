package com.pc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.service.BookService;
import com.pc.service.MyCart;
/**
 * 
 * @author Switch
 * 功能：在跳转购物车时进行处理
 *
 */
public class ShoppingClServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 接收type值，区分用户动作
		String type = request.getParameter("type");
		
		if(type.equals("delete")){
			// 用户要删除商品
			
			// 接收用户希望删除的商品
			String id = request.getParameter("id");
			
			// 得到购物车
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			// 删除书籍
			myCart.deleteBook(id);
			// 把要显示的数据放入request，准备显示
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice() + "");
			// 跳转到我的购物车
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		} else if(type.equals("add")){
			// 用户要添加商品
			// 接收用户想购买的商品ID
			String id = request.getParameter("id");
			// 测试
			// System.out.println(id);

			// 创建BookService对象
			// BookService bookService = new BookService();

			// 取出购物车，并添加书到购物车中
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			myCart.addBook(id);
			
			// 为了防止某个页面刷新，可以使用sendRedirect();
			response.sendRedirect("/MyShoping/GoShowMyCart");
			
		} else if(type.equals("update")) {
			// 更新
			// 得到用户希望更新的书号和数量
			String[] bookIds = request.getParameterValues("id");
			// 得到每本书的数量
			String[] nums = request.getParameterValues("booknum");
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			if(bookIds != null){
				for (int i = 0; i < bookIds.length; i++) {
					myCart.updateBook(bookIds[i], nums[i]);
				}
			}
			// 把要显示的数据放入request，准备显示
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice() + "");
			// 跳转到我的购物车
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		} else if(type.equals("view")) {
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			// 把要显示的数据放入request，准备显示
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice() + "");
			// 跳转到我的购物车
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

package com.pc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.domain.Book;
import com.pc.service.MyCart;
/**
 * 
 * @author Switch
 * 功能：在跳转查看订单页面时进行处理
 *
 */
public class GoMyOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// 得到购物车
		MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
		ArrayList<Book> books = myCart.showMyCart();
		float totalPrice = myCart.getTotalPrice();
		request.setAttribute("orderinfo", books);
		request.setAttribute("totalPrice", totalPrice);
		// 跳转到显示我的订单的页面
		request.getRequestDispatcher("/WEB-INF/showMyOrder.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

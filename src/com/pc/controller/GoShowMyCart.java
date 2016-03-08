package com.pc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.service.MyCart;
/**
 * 
 * @author Switch
 * 功能：为了防止添加操作，刷新之后又加入购物车一次，采用控制器隔离
 *
 */
public class GoShowMyCart extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
		// 把要显示的数据放入request，准备显示
		request.setAttribute("bookList", myCart.showMyCart());
		request.setAttribute("totalPrice", myCart.getTotalPrice() + "");

		// 跳转到我的购物车
		request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

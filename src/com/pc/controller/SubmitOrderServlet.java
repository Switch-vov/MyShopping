package com.pc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.domain.Users;
import com.pc.service.MyCart;
import com.pc.service.OrderService;
/**
 * 
 * @author Switch
 * 功能：在跳转成功订购页面时进行处理
 *
 */
public class SubmitOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// 处理下订单的请求
		try {
			OrderService orderService = new OrderService();
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			Users user = (Users) request.getSession().getAttribute("loginUser");
			orderService.submitOrder(myCart, user);
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/orderOk.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

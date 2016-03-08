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
 * ���ܣ�����ת�ɹ�����ҳ��ʱ���д���
 *
 */
public class SubmitOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ַ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// �����¶���������
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

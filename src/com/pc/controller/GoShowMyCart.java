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
 * ���ܣ�Ϊ�˷�ֹ��Ӳ�����ˢ��֮���ּ��빺�ﳵһ�Σ����ÿ���������
 *
 */
public class GoShowMyCart extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ַ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
		// ��Ҫ��ʾ�����ݷ���request��׼����ʾ
		request.setAttribute("bookList", myCart.showMyCart());
		request.setAttribute("totalPrice", myCart.getTotalPrice() + "");

		// ��ת���ҵĹ��ﳵ
		request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

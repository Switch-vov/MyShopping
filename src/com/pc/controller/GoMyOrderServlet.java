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
 * ���ܣ�����ת�鿴����ҳ��ʱ���д���
 *
 */
public class GoMyOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ַ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// �õ����ﳵ
		MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
		ArrayList<Book> books = myCart.showMyCart();
		float totalPrice = myCart.getTotalPrice();
		request.setAttribute("orderinfo", books);
		request.setAttribute("totalPrice", totalPrice);
		// ��ת����ʾ�ҵĶ�����ҳ��
		request.getRequestDispatcher("/WEB-INF/showMyOrder.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

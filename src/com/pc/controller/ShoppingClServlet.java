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
 * ���ܣ�����ת���ﳵʱ���д���
 *
 */
public class ShoppingClServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ַ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		// ����typeֵ�������û�����
		String type = request.getParameter("type");
		
		if(type.equals("delete")){
			// �û�Ҫɾ����Ʒ
			
			// �����û�ϣ��ɾ������Ʒ
			String id = request.getParameter("id");
			
			// �õ����ﳵ
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			// ɾ���鼮
			myCart.deleteBook(id);
			// ��Ҫ��ʾ�����ݷ���request��׼����ʾ
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice() + "");
			// ��ת���ҵĹ��ﳵ
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		} else if(type.equals("add")){
			// �û�Ҫ�����Ʒ
			// �����û��빺�����ƷID
			String id = request.getParameter("id");
			// ����
			// System.out.println(id);

			// ����BookService����
			// BookService bookService = new BookService();

			// ȡ�����ﳵ��������鵽���ﳵ��
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			myCart.addBook(id);
			
			// Ϊ�˷�ֹĳ��ҳ��ˢ�£�����ʹ��sendRedirect();
			response.sendRedirect("/MyShoping/GoShowMyCart");
			
		} else if(type.equals("update")) {
			// ����
			// �õ��û�ϣ�����µ���ź�����
			String[] bookIds = request.getParameterValues("id");
			// �õ�ÿ���������
			String[] nums = request.getParameterValues("booknum");
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			if(bookIds != null){
				for (int i = 0; i < bookIds.length; i++) {
					myCart.updateBook(bookIds[i], nums[i]);
				}
			}
			// ��Ҫ��ʾ�����ݷ���request��׼����ʾ
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice() + "");
			// ��ת���ҵĹ��ﳵ
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		} else if(type.equals("view")) {
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			// ��Ҫ��ʾ�����ݷ���request��׼����ʾ
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice() + "");
			// ��ת���ҵĹ��ﳵ
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

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
 * ���ܣ�����ת�������ʱ���д���
 *
 */
public class GoHallUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ַ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		// ���жϸ��û��Ƿ��Ѿ���¼
		if(request.getSession().getAttribute("loginUser") != null){
			// ����BookService����
			BookService bookService = new BookService();
			ArrayList<Book> books = bookService.getAllBook();
								
			// ��books����request
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			return ;
		}
		
		
		
		
		
		// �õ��ӵ�¼ҳ�洫�������û���������
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// ����
		// System.out.println(userId + " " + password);
		
		// ����Users����
		Users user = new Users();
		try{
			// ���userId��Ϊ����
			user.setId(Integer.parseInt(userId));
		} catch (NumberFormatException e) {
			request.setAttribute("err", "�û�IDֻ��Ϊ����");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		} 
		user.setPwd(password);
		
		// ����UsersService����
		UsersService usersService = new UsersService();
		
		// ��UsersService��֤�û��Ƿ�Ϸ�
		if(usersService.checkUser(user)){
			// �ɹ�����ת���������
			
			// ��Ϊ����ҳ�����ʹ�õ��û���Ϣ�����Խ��û���Ϣ����session
			request.getSession().setAttribute("loginUser", user);
			
			// ����BookService����
			BookService bookService = new BookService();
			ArrayList<Book> books = bookService.getAllBook();
			
			// ����һ�����ﳵ
			MyCart myCart = new MyCart();
			request.getSession().setAttribute("myCart", myCart);
			
			// ��books����request
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			
		} else {
			// ʧ�ܣ�����ԭҳ��
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

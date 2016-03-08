package com.pc.service;

import java.util.ArrayList;

import com.pc.domain.Book;
import com.pc.utils.SqlHelper;

/**
 * 
 * @author Switch
 * 功能：处理和book表相关的业务逻辑
 *
 */
public class BookService {

	
	// 得到所有的图书信息，分页显示
	public ArrayList getAllBook(){
		String sql = "select * from book";
		ArrayList bookArrayList = SqlHelper.executeQuery(sql, null);
		// 图书集合
		ArrayList<Book> books = new ArrayList<Book>();
		// 二次封装
		for(int i = 0; i < bookArrayList.size(); i++) {
			// 获取Object数组
			Object[] o = (Object[])bookArrayList.get(i);
			// 封装到Book对象
			Book book = new Book();
			book.setId(Integer.parseInt(o[0].toString()));
			book.setName((String)o[1]);
			book.setAuthor((String)o[2]);
			book.setPublishHouse((String)o[3]);
			book.setPrice(Float.parseFloat(o[4].toString()));
			book.setNums(Integer.parseInt(o[5].toString()));
			
			// 添加到返回集合
			books.add(book);
		}
		return books;
	}
	
	// 根据ID得到图书信息
	public Book getBookById(String id){
		String sql = "select * from book where id=?";
		String[] parameters = {id};
		ArrayList bookArrayList = SqlHelper.executeQuery(sql, parameters);
		Book book = null;
		if(bookArrayList.size() == 1){
			// 获取Object数组
			Object[] o = (Object[])bookArrayList.get(0);
			// 封装到Book对象
			book = new Book();
			book.setId(Integer.parseInt(o[0].toString()));
			book.setName((String)o[1]);
			book.setAuthor((String)o[2]);
			book.setPublishHouse((String)o[3]);
			book.setPrice(Float.parseFloat(o[4].toString()));
			book.setNums(Integer.parseInt(o[5].toString()));
		}
		return book;
	}
}

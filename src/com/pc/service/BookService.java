package com.pc.service;

import java.util.ArrayList;

import com.pc.domain.Book;
import com.pc.utils.SqlHelper;

/**
 * 
 * @author Switch
 * ���ܣ������book����ص�ҵ���߼�
 *
 */
public class BookService {

	
	// �õ����е�ͼ����Ϣ����ҳ��ʾ
	public ArrayList getAllBook(){
		String sql = "select * from book";
		ArrayList bookArrayList = SqlHelper.executeQuery(sql, null);
		// ͼ�鼯��
		ArrayList<Book> books = new ArrayList<Book>();
		// ���η�װ
		for(int i = 0; i < bookArrayList.size(); i++) {
			// ��ȡObject����
			Object[] o = (Object[])bookArrayList.get(i);
			// ��װ��Book����
			Book book = new Book();
			book.setId(Integer.parseInt(o[0].toString()));
			book.setName((String)o[1]);
			book.setAuthor((String)o[2]);
			book.setPublishHouse((String)o[3]);
			book.setPrice(Float.parseFloat(o[4].toString()));
			book.setNums(Integer.parseInt(o[5].toString()));
			
			// ��ӵ����ؼ���
			books.add(book);
		}
		return books;
	}
	
	// ����ID�õ�ͼ����Ϣ
	public Book getBookById(String id){
		String sql = "select * from book where id=?";
		String[] parameters = {id};
		ArrayList bookArrayList = SqlHelper.executeQuery(sql, parameters);
		Book book = null;
		if(bookArrayList.size() == 1){
			// ��ȡObject����
			Object[] o = (Object[])bookArrayList.get(0);
			// ��װ��Book����
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

package com.pc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import oracle.net.aso.b;

import com.pc.domain.Book;

/**
 * 
 * @author Switch
 * ���ܣ���ʾ���ﳵ
 *
 */
public class MyCart {
	HashMap<String, Book> bookHashMap = new HashMap<String, Book>();
	
	// �����
	public void addBook(String id, Book book){
		if(bookHashMap.containsKey(id)){
			book = bookHashMap.get(id);
			// ���������ˣ�����+1
			book.setShoppingNum(book.getShoppingNum() + 1);
		} else {
			// δ������뼯��
			bookHashMap.put(id, book);
		}
	}
	
	// �����2
	public void addBook(String id){
		if(bookHashMap.containsKey(id)){
			Book book = bookHashMap.get(id);
			// ����Ѿ������ˣ�����+1
			book.setShoppingNum(book.getShoppingNum() + 1);
		} else {
			bookHashMap.put(id, new BookService().getBookById(id));
		}
	}
	
	
	// ɾ����
	public void deleteBook(String id){
		bookHashMap.remove(id);
	}

	// ������,�Թ��ﳵ��˵����ָ�����������
	public void updateBook(String id, String nums){
		// ȡ��id��Ӧ��Book
		Book book = bookHashMap.get(id);
		book.setShoppingNum(Integer.parseInt(nums));
	}
	
	// ����飬��չ��ﳵ
	public void clearBook(){
		bookHashMap.clear();
	}
	
	// ��ʾ�ù��ﳵ�е�������Ʒ��Ϣ
	public ArrayList<Book> showMyCart(){
		// ����ArrayList<Book>����
		ArrayList<Book> books = new ArrayList<Book>();
		// ����HashMap
		Iterator<String> it = bookHashMap.keySet().iterator();
		while(it.hasNext()){
			// ȡ��id
			String id = it.next();
			// ȡ��Book
			Book book = bookHashMap.get(id);
			// ����ArrayList<Book>����
			books.add(book);
		}
		// ����
		// System.out.println(books.size());
		return books;
	}
	
	// ���ع��ﳵ���ܼ�ֵ
	public float getTotalPrice(){
		// �ܼ�ֵ
		float totalPrice = 0.0f;
		Iterator<String> it = bookHashMap.keySet().iterator();
		while(it.hasNext()){
			// ȡ��id
			String id = it.next();
			// ȡ��Book
			Book book = bookHashMap.get(id);
			// �ۼӵ��ܼ���
			totalPrice += book.getShoppingNum() * book.getPrice();
		}
		return totalPrice;
	}
}

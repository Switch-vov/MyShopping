package com.pc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import oracle.net.aso.b;

import com.pc.domain.Book;

/**
 * 
 * @author Switch
 * 功能：表示购物车
 *
 */
public class MyCart {
	HashMap<String, Book> bookHashMap = new HashMap<String, Book>();
	
	// 添加书
	public void addBook(String id, Book book){
		if(bookHashMap.containsKey(id)){
			book = bookHashMap.get(id);
			// 如果购买过了，数量+1
			book.setShoppingNum(book.getShoppingNum() + 1);
		} else {
			// 未购买加入集合
			bookHashMap.put(id, book);
		}
	}
	
	// 添加书2
	public void addBook(String id){
		if(bookHashMap.containsKey(id)){
			Book book = bookHashMap.get(id);
			// 如果已经购买了，数量+1
			book.setShoppingNum(book.getShoppingNum() + 1);
		} else {
			bookHashMap.put(id, new BookService().getBookById(id));
		}
	}
	
	
	// 删除书
	public void deleteBook(String id){
		bookHashMap.remove(id);
	}

	// 更新书,对购物车来说，是指更新书的数量
	public void updateBook(String id, String nums){
		// 取出id对应的Book
		Book book = bookHashMap.get(id);
		book.setShoppingNum(Integer.parseInt(nums));
	}
	
	// 清空书，清空购物车
	public void clearBook(){
		bookHashMap.clear();
	}
	
	// 显示该购物车中的所有商品信息
	public ArrayList<Book> showMyCart(){
		// 定义ArrayList<Book>对象
		ArrayList<Book> books = new ArrayList<Book>();
		// 遍历HashMap
		Iterator<String> it = bookHashMap.keySet().iterator();
		while(it.hasNext()){
			// 取出id
			String id = it.next();
			// 取出Book
			Book book = bookHashMap.get(id);
			// 创建ArrayList<Book>对象
			books.add(book);
		}
		// 测试
		// System.out.println(books.size());
		return books;
	}
	
	// 返回购物车的总价值
	public float getTotalPrice(){
		// 总价值
		float totalPrice = 0.0f;
		Iterator<String> it = bookHashMap.keySet().iterator();
		while(it.hasNext()){
			// 取出id
			String id = it.next();
			// 取出Book
			Book book = bookHashMap.get(id);
			// 累加到总价上
			totalPrice += book.getShoppingNum() * book.getPrice();
		}
		return totalPrice;
	}
}

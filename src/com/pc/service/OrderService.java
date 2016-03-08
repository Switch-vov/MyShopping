package com.pc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pc.domain.Book;
import com.pc.domain.Users;

/**
 * 
 * @author Switch
 * 功能：处理和orders表相关的业务逻辑
 *
 */
public class OrderService {
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	// 下订单涉及两张表，并且两张表都有关系
	public void submitOrder(MyCart myCart, Users user){
		String sql = "insert into orders values(order_seq.nextval,?,?,sysdate)";
		// 因为添加订单的复杂性，故不适用SqlHelper
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:SWITCH", "scott", "123456");
			
			// 为了保证订单号稳定，将事务隔离级别升级为可串行化
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			ps = ct.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setFloat(2, myCart.getTotalPrice());
			ps.executeUpdate();
			
			// 得到刚刚插入订单记录的订单号
			sql = "select order_seq.currval from orders";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			if(rs.next()){
				// 取出刚刚生成的订单号
				orderId = rs.getInt(1);
			}
			// 生成订单细节表
			ArrayList<Book> books = myCart.showMyCart();
			for(Book book : books){
				sql = "insert into orderItem values(orderitem_seq.nextval,?,?,?)";
				ps = ct.prepareStatement(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, book.getId());
				ps.setInt(3, book.getShoppingNum());
				ps.executeUpdate();
			}
			
			// 整体提交
			ct.commit();
			
			
		} catch (Exception e) {
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					rs = null;
					e.printStackTrace();
				}
			}
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					ps = null;
					e.printStackTrace();
				}
			}
			if(ct != null){
				try {
					ct.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					ct = null;
					e.printStackTrace();
				}
			}
		}
	}
}

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
 * ���ܣ������orders����ص�ҵ���߼�
 *
 */
public class OrderService {
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	// �¶����漰���ű��������ű��й�ϵ
	public void submitOrder(MyCart myCart, Users user){
		String sql = "insert into orders values(order_seq.nextval,?,?,sysdate)";
		// ��Ϊ��Ӷ����ĸ����ԣ��ʲ�����SqlHelper
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:SWITCH", "scott", "123456");
			
			// Ϊ�˱�֤�������ȶ�����������뼶������Ϊ�ɴ��л�
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			ps = ct.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setFloat(2, myCart.getTotalPrice());
			ps.executeUpdate();
			
			// �õ��ող��붩����¼�Ķ�����
			sql = "select order_seq.currval from orders";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			if(rs.next()){
				// ȡ���ո����ɵĶ�����
				orderId = rs.getInt(1);
			}
			// ���ɶ���ϸ�ڱ�
			ArrayList<Book> books = myCart.showMyCart();
			for(Book book : books){
				sql = "insert into orderItem values(orderitem_seq.nextval,?,?,?)";
				ps = ct.prepareStatement(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, book.getId());
				ps.setInt(3, book.getShoppingNum());
				ps.executeUpdate();
			}
			
			// �����ύ
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
			// �ر���Դ
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

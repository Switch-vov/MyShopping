package com.pc.service;

import java.util.ArrayList;

import com.pc.domain.Users;
import com.pc.utils.SqlHelper;

/**
 * 
 * @author Switch
 * 功能：处理和users表相关的业务逻辑
 *
 */
public class UsersService {
	
	
	
	// 验证用户是否合法
	public boolean checkUser(Users user) {
		
		// 数据库验证合法性
		String sql = "select * from users where id=? and pwd=?";
		String[] parameters = {user.getId() + "", user.getPwd()};
		ArrayList userArrayList = SqlHelper.executeQuery(sql, parameters);
		// 不合法
		if(userArrayList.size() == 0) {
			return false;
		} else {
			Object[] o = (Object[])userArrayList.get(0);
			// 封装到Users对象
			user.setName((String)o[1]);
			user.setEmail((String)o[3]);
			user.setGrade(Integer.parseInt(o[5].toString()));
			return true;
		}
	}
	
}

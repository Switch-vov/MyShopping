package com.pc.service;

import java.util.ArrayList;

import com.pc.domain.Users;
import com.pc.utils.SqlHelper;

/**
 * 
 * @author Switch
 * ���ܣ������users����ص�ҵ���߼�
 *
 */
public class UsersService {
	
	
	
	// ��֤�û��Ƿ�Ϸ�
	public boolean checkUser(Users user) {
		
		// ���ݿ���֤�Ϸ���
		String sql = "select * from users where id=? and pwd=?";
		String[] parameters = {user.getId() + "", user.getPwd()};
		ArrayList userArrayList = SqlHelper.executeQuery(sql, parameters);
		// ���Ϸ�
		if(userArrayList.size() == 0) {
			return false;
		} else {
			Object[] o = (Object[])userArrayList.get(0);
			// ��װ��Users����
			user.setName((String)o[1]);
			user.setEmail((String)o[3]);
			user.setGrade(Integer.parseInt(o[5].toString()));
			return true;
		}
	}
	
}

package com.just.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.just.dao.UserDao;
import com.just.dao.impl.UserDaoImpl;
import com.just.entity.User;

public class UserService {
	private UserDao mUserDao = new UserDaoImpl();
	public User userLogin(User user){
		return mUserDao.login(user);
	}
	public User userRegister(User user){
		return mUserDao.insert(user);
	}
	
	public List<User> search(String userName){
		Map<String, String> map =  new HashMap<String, String>();
		map.put("userName", userName);
		
		return mUserDao.query(map);
	}
}

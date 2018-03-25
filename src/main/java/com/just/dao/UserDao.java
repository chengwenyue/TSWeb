package com.just.dao;

import java.util.List;
import java.util.Map;

import com.just.entity.User;

public interface UserDao {
	public User login(User user);
	public User login(long id);
	public User insert(User user);
	public List<User> query(Map<String , String> map);
}

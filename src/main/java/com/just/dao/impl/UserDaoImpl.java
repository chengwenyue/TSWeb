package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.just.dao.UserDao;
import com.just.entity.User;
import com.just.util.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class UserDaoImpl implements UserDao{

	public synchronized User login(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn  = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user where userName=? and userPwd=?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			rs = ps.executeQuery();
			if(rs.first()){
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserEmail(rs.getString("userEmail"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		return null;
	}

	public synchronized User insert(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long id = 0;
		try {
			conn  = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select id from user order by id desc");
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getLong("id");
			}
			ps = conn.prepareStatement("insert into user values (null,?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserEmail());
			ps.executeUpdate();
			user.setId(id+1);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		return null;
	}

	public synchronized User login(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn  = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.first()){
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserEmail(rs.getString("userEmail"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		return null;
	}

	public synchronized List<User> query(Map<String, String> map) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select * from user where ");
			List<String> values = new ArrayList<String>();
			for(Map.Entry<String, String> entry : map.entrySet()){
				values.add(entry.getValue());
				builder.append(entry.getKey() +" like ? and ");
			}
			String sql = builder.substring(0, builder.length() - 5);
		
			conn  = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i =0;i<values.size();i++){
				ps.setString(i+1, "%"+values.get(i)+"%");
			}
			
			rs = ps.executeQuery();
			
			List<User> userList = new ArrayList<User>();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmail(rs.getString("userEmail"));
				userList.add(user);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		return null;
	}
}

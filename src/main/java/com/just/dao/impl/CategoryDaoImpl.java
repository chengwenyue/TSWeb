package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.just.dao.CategoryDao;
import com.just.entity.Category;
import com.just.util.JDBCUtils;


public class CategoryDaoImpl implements CategoryDao{

	public long addCategory(Category category) {
		Connection conn  = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("insert into category values(null,?,?,?,?)");
			ps.setLong(1, category.get_id());
			ps.setLong(2, category.getUserId());
			ps.setString(3, category.getCategoryName());
			ps.setInt(4, category.getCategoryColor());
			
			long update = ps.executeUpdate();
			//如果插入成功  则获取刚刚插入数据的id
			if(update!=0){
				ps=conn.prepareStatement("select last_insert_id()");
				rs=ps.executeQuery();
				if(rs.first())
				{
					update=rs.getLong("LAST_INSERT_ID()");
				}
			}
			return update;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
	}

	public boolean delCategory(long userId, long _id) {
		Connection conn  = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("delete from category where userId = ? and _id = ?");
			ps.setLong(1, userId);
			ps.setLong(2, _id);
			if(ps.executeUpdate()==1)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			JDBCUtils.close(null, ps, conn);
		}
	}

	public List<Category> querybyUserId(long userId) {
		
		List<Category> categories = new ArrayList<Category>();
		Connection conn  = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from category where userId = ?");
			ps.setLong(1, userId);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Category category = new Category();
				category.setId(rs.getLong("id"));
				category.set_id(rs.getLong("_id"));
				category.setUserId(rs.getLong("userId"));
				category.setCategoryName(rs.getString("categoryName"));
				category.setCategoryColor(rs.getInt("categoryColor"));
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			return categories;
		}finally{
			JDBCUtils.close(null, ps, conn);
		}
	}

	public boolean updateCategory(Category category) {
		
		return false;
	}

}

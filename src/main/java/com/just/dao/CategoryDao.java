package com.just.dao;

import java.util.List;

import com.just.entity.Category;

public interface CategoryDao {
	
	public long addCategory(Category category);
	
	public boolean delCategory(long userId,long categoryId);

	public boolean updateCategory(Category category);

	public List<Category> querybyUserId(long userId);
}

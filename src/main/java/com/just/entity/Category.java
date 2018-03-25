package com.just.entity;

public class Category {
	private long id; //此字段为服务器本地的id
	private long _id; //此字段记录来自客户端事件的id
	private long userId;
	private String categoryName;
	private int categoryColor;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public long get_id() {
		return _id;
	}
	public void setCategoryColor(int categoryColor) {
		this.categoryColor = categoryColor;
	}
	public int getCategoryColor() {
		return categoryColor;
	}
}

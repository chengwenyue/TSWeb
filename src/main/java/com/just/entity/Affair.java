package com.just.entity;


public class Affair {
	private long id; //此字段为服务器本地的id
	private long _id; //此字段记录来自客户端事件的id
	private long userId;
	private String affairName;
	private String affairType;
	private String affairCgy;
	private String affairRemark;
	private String affairPsn;
	private String affairTime;
	private int affairState;
	
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
	public String getAffairName() {
		return affairName;
	}
	public void setAffairName(String affairName) {
		this.affairName = affairName;
	}
	public String getAffairType() {
		return affairType;
	}
	public void setAffairType(String affairType) {
		this.affairType = affairType;
	}
	public String getAffairCgy() {
		return affairCgy;
	}
	public void setAffairCgy(String affairCgy) {
		this.affairCgy = affairCgy;
	}
	public String getAffairRemark() {
		return affairRemark;
	}
	public void setAffairRemark(String affairRemark) {
		this.affairRemark = affairRemark;
	}
	public String getAffairPsn() {
		return affairPsn;
	}
	public void setAffairPsn(String affairPsn) {
		this.affairPsn = affairPsn;
	}
	public String getAffairTime() {
		return affairTime;
	}
	public void setAffairTime(String affairTime) {
		this.affairTime = affairTime;
	}
	public int getAffairState() {
		return affairState;
	}
	public void setAffairState(int affairState) {
		this.affairState = affairState;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public long get_id() {
		return _id;
	}
}

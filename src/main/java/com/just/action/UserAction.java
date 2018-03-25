package com.just.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.just.dao.UserDao;
import com.just.entity.User;
import com.just.service.UserService;
import com.just.util.ResponseUtil;
import com.just.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String login() throws Exception{
		JSONObject o = null;
		try {
			UserService us = new UserService();
			o = new JSONObject();
			String userInfo = request.getParameter("userLogin");
			JSONObject in = JSONObject.fromObject(userInfo);
			User user = (User) JSONObject.toBean(in,User.class);
			User userTemp = us.userLogin(user);
			if(userTemp!=null){
				o.put("success", true);
			}else{
				o.put("success", false);
			}
			o.put("user", userTemp);
		} catch (Exception e) {
			e.printStackTrace();
			o.put("user", null);
			o.put("success", false);
		}
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), o);
		return null;
	}
	
	public String register() throws Exception{
		JSONObject o = null;
		try {
			UserService us = new UserService();
			o = new JSONObject();
			String userInfo = request.getParameter("userRegister");
			JSONObject in = JSONObject.fromObject(userInfo);
			User user = (User) JSONObject.toBean(in,User.class);
			us.userRegister(user);
			System.out.println(user);
			o.put("success", true);
			o.put("user", JSONObject.fromObject(user));
		} catch (Exception e) {
			e.printStackTrace();
			o.put("success", false);
		}
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), o);
		return null;
	}
	
	public String search() throws Exception{
		JSONObject o = null;
		try {
			UserService us = new UserService();
			o = new JSONObject();
			String userInfo = request.getParameter("userSearch");
			JSONObject in = JSONObject.fromObject(userInfo);
			String userName =  in.getString("userName");
			List<User> users = us.search(userName);
			o.put("success", true);
			if(!users.isEmpty()){
				o.put("user", JSONObject.fromObject(users.get(0)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			o.put("success", false);
		}
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), o);
		return null;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}

}

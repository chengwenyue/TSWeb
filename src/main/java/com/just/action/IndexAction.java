package com.just.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.just.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String login() throws Exception{
		JSONObject o = new JSONObject();
		o.put("message", "success");
		System.out.println("hello");
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), o);
		return null;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}

}

package com.just.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.just.service.AffairService;
import com.just.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AffairAction extends ActionSupport implements ServletRequestAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//尽量少用全局变量   主要是为了防止程序并发访问出错  能用局部变量就不要用全局变量
	JSONObject inputJSON  = null;
	JSONObject outputJSON  = null;
	
	private final String parameter = "data";
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	private AffairService myAffairService=new AffairService();
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	//添加事件到服务器
	public String addAffair() throws Exception {
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			//得到输出JSON字符串
			outputJSON=myAffairService.addAffair(inputJSON);
			
			
			//不在此处输出到屏幕的原因是     当程序在这段代码前出现异常时，这段代码不执行
			//ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		} catch (Exception e) {
			e.printStackTrace();
			//当程序在inputJSON=JSONObject.fromObject(in);出现异常时，此时的outputJSON=null;
			if(outputJSON == null)
				outputJSON= new JSONObject();
			outputJSON.put("success", false);
		}
		
		//写出到屏幕         在此处输出到屏幕的原因是     无论程序在那出现异常都能向客户端输出
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}

	public String addAffairs() throws Exception{
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			//得到输出JSON字符串
			outputJSON=myAffairService.addAffairs(inputJSON);
			
		} catch (Exception e) {
			e.printStackTrace();
			if(outputJSON == null)
				outputJSON= new JSONObject();
			outputJSON.put("success", false);
		}
		//写出到屏幕
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}
	public String delAffair() throws Exception{
		outputJSON=new JSONObject();
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			boolean isSuccess=myAffairService.delAffair(inputJSON);
			
			//得到输出JSON字符串
			if (isSuccess) {
				outputJSON.put("success", true);
			}else {
				outputJSON.put("success", false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outputJSON.put("success", false);
		}
		//写出到屏幕
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}
	public String delAffairs() throws Exception{
		outputJSON =new JSONObject();
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			//得到输出JSON字符串
			if(myAffairService.delAffairs(inputJSON)){
				outputJSON.put("success", true);
			}else{
				outputJSON.put("success", false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outputJSON.put("success", false);
		}
		//写出到屏幕
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}
	public String updateAffair() throws Exception{
		outputJSON =new JSONObject();
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			//得到输出JSON字符串
			if(myAffairService.updateAffair(inputJSON)){
				outputJSON.put("success", true);
			}else{
				outputJSON.put("success", false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outputJSON.put("success", false);
		}
		//写出到屏幕
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}
	public String updateAffairs() throws Exception{
		outputJSON =new JSONObject();
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			//得到输出JSON字符串
			if(myAffairService.updateAffairs(inputJSON)){
				outputJSON.put("success", true);
			}else{
				outputJSON.put("success", false);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			outputJSON.put("success", false);
		}
		//写出到屏幕
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}
	public String queryByUserId() throws Exception{
		try {
			//通过请求参数affairAdd获取输入json字符串
			String in=request.getParameter(parameter);
			inputJSON=JSONObject.fromObject(in);
			
			//得到输出JSON字符串
			outputJSON=myAffairService.queryByUserId(inputJSON);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(outputJSON == null)
				outputJSON = new JSONObject();
			outputJSON.put("success", false);
		}
		//写出到屏幕
		ResponseUtil.writeToClient(ServletActionContext.getResponse(), outputJSON);
		return null;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}
}

package com.just.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {
	public static void writeToClient(HttpServletResponse response,JSONObject o)throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String json = o.toString();
		System.out.println(json);
		response.setContentLength(json.getBytes("utf-8").length);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}
}

package com.just.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UserTest {
	public static void main(String[] args) {
		Map<String, String> map =  new HashMap<String, String>();
		map.put("userName", "cwy");
		
		StringBuilder builder = new StringBuilder();
		builder.append("select * from user where ");
		List<String> values = new ArrayList<String>();
		for(Map.Entry<String, String> entry : map.entrySet()){
			values.add(entry.getValue());
			builder.append(entry.getKey() +"=? and ");
		}
		String sql = builder.substring(0, builder.length() - 5);
		System.out.println(sql);
	}
}

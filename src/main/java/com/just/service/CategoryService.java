package com.just.service;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.just.dao.AffairDao;
import com.just.dao.CategoryDao;
import com.just.dao.impl.AffairDaoImpl;
import com.just.dao.impl.CategoryDaoImpl;
import com.just.entity.Affair;
import com.just.entity.Category;
import com.sun.media.sound.JavaSoundAudioClip;

public class CategoryService {
	
	JSONObject outputJSON  = null;
	JSONArray inputArray=null;
	JSONArray outputArray=null;
	
	private CategoryDao mCategoryDao=new CategoryDaoImpl();

	public JSONObject addCategory(JSONObject inputJSON) {
		
		outputJSON=new JSONObject();
		
		//记录事件插入数据库后的ID，若为0则不成功
		long affairID=0;
		
		//从JSONObject对象转换成Affair对象
		Category category = (Category) JSONObject.toBean(inputJSON,Category.class);
		
		//把新事件传给AffairDao，并返回ID值
		affairID=mCategoryDao.addCategory(category);
		
		//判断是否插入成功，创建输出json 
		if(affairID!=0){
			outputJSON.put("categoryId", affairID);
			outputJSON.put("success", true);
		}else {
			outputJSON.put("success", false);
		}
		return outputJSON;
	}
	
	public JSONObject queryByUserId(JSONObject inputJSON) {
		
		long userId=inputJSON.getLong("userId");
		//通过AffairDao获得所有userId的事件的集合
		List<Category> categoryList=mCategoryDao.querybyUserId(userId);

		//把所有affair写入到JSONObject并加入JSONArray
		outputJSON=new JSONObject();
		outputArray=new JSONArray();
		JSONObject out=new JSONObject();
		for (Category category:categoryList) {
			out = JSONObject.fromObject(category);
			out.put("categoryId", out.getLong("id"));
			out.remove("id");
//			out.remove("_Id");
			out.remove("userId");
			outputArray.add(out);
		}
		outputJSON.put("userId", userId);
		outputJSON.put("categories", outputArray);
		outputJSON.put("success", true);
		return outputJSON; 
	}
	
	public boolean delCategory(JSONObject inputJSON) {
		long userId=inputJSON.getLong("userId");
		long _id=inputJSON.getLong("_id");
		return mCategoryDao.delCategory(userId, _id);
	}
	
	
	
	public boolean updateCategory(JSONObject inputJSON) {
		
		return false;
		
//		Category category = (Category) JSONObject.toBean(inputJSON,Category.class);
//		category.setId(inputJSON.getLong("categoryId"));
//		return mCategoryDao.updateCategory(category);
	}
}
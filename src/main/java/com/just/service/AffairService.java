package com.just.service;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.just.dao.AffairDao;
import com.just.dao.impl.AffairDaoImpl;
import com.just.entity.Affair;
import com.sun.media.sound.JavaSoundAudioClip;

public class AffairService {
	
	JSONObject outputJSON  = null;
	JSONArray inputArray=null;
	JSONArray outputArray=null;
	
	private AffairDao myAffairDao=new AffairDaoImpl();

	public JSONObject addAffair(JSONObject inputJSON) {
		
		outputJSON=new JSONObject();
		
		//记录事件插入数据库后的ID，若为0则不成功
		long affairID=0;
		
		//从JSONObject对象转换成Affair对象
		Affair affair = (Affair) JSONObject.toBean(inputJSON,Affair.class);
		
		//把新事件传给AffairDao，并返回ID值
		affairID=myAffairDao.addAffair(affair);
		
		//判断是否插入成功，创建输出json 
		if(affairID!=0){
			outputJSON.put("affairId", affairID);
			outputJSON.put("success", true);
		}else {
			outputJSON.put("success", false);
		}
		return outputJSON;
	}
	
	public JSONObject addAffairs(JSONObject inputJSON) {
		outputJSON=new JSONObject();
		outputArray=new JSONArray();
		
		long userId=inputJSON.getLong("userId");
		inputArray=inputJSON.getJSONArray("affairs");
		
		Iterator iterator = inputArray.iterator();
		
		while (iterator.hasNext()) {
			
			JSONObject in=(JSONObject)iterator.next();
			JSONObject out=new JSONObject();
			
			long affairID=0;
			
			Affair affair = (Affair) JSONObject.toBean(in,Affair.class);
			affair.setUserId(userId);
			
			//把新事件传给AffairDao，并返回ID值
			affairID=myAffairDao.addAffair(affair);
			
			//判断是否插入成功，创建输出json 
			if(affairID!=0){
				out.put("_id", in.getLong("_id"));
				out.put("affairId", affairID);
			}else {
				out.put("success", false);
			}
			
			outputArray.add(out);
		}
		
		outputJSON.put("affairs", outputArray);
		outputJSON.put("userId", inputJSON.getLong("userId"));
		outputJSON.put("success", true);
		
		return outputJSON;
	}
	

	public JSONObject queryByUserId(JSONObject inputJSON) {
		
		long userId=inputJSON.getLong("userId");
		//通过AffairDao获得所有userId的事件的集合
		List<Affair> affairList=myAffairDao.querybyUserId(userId);

		//把所有affair写入到JSONObject并加入JSONArray
		outputJSON=new JSONObject();
		outputArray=new JSONArray();
		JSONObject out=new JSONObject();
		for (Affair affair:affairList) {
			out = JSONObject.fromObject(affair);
			out.put("affairId", out.getLong("id"));
			out.remove("id");
//			out.remove("_Id");
			out.remove("userId");
			outputArray.add(out);
		}
		outputJSON.put("userId", userId);
		outputJSON.put("affairs", outputArray);
		outputJSON.put("success", true);
		return outputJSON; 
	}
	
	public boolean delAffair(JSONObject inputJSON) {
		long userId=inputJSON.getLong("userId");
		long affairId=inputJSON.getLong("affairId");
		long _id=inputJSON.getLong("_id");
		return myAffairDao.delAffair(userId, _id);
	}
	
	public boolean delAffairs(JSONObject inputJSON) {
		long userId=inputJSON.getLong("userId");
		inputArray=inputJSON.getJSONArray("affairs");
		
		Iterator iterator = inputArray.iterator();
		boolean isSuccesss=true;
		
		while (iterator.hasNext()) {
			
			JSONObject in=(JSONObject)iterator.next();
			
			long affairId=in.getLong("affairId");
			
			isSuccesss=myAffairDao.delAffair(userId, affairId);
			
			if(isSuccesss!=true){
				return isSuccesss;
			}
		}
		return isSuccesss;
	}
	
	public boolean updateAffair(JSONObject inputJSON) {
		Affair affair = (Affair) JSONObject.toBean(inputJSON,Affair.class);
		affair.setId(inputJSON.getLong("affairId"));
		return myAffairDao.updateAffair(affair);
	}
	
	public boolean updateAffairs(JSONObject inputJSON) {
		long userId=inputJSON.getLong("userId");
		inputArray=inputJSON.getJSONArray("affairs");
		
		Iterator iterator = inputArray.iterator();
		boolean isSuccesss=true;
		
		while (iterator.hasNext()) {
			
			JSONObject in=(JSONObject)iterator.next();
			
			Affair affair = (Affair) JSONObject.toBean(in,Affair.class);
			affair.setId(in.getLong("affairId"));
			affair.setUserId(userId);
			
			//把新事件传给AffairDao，并返回ID值
			isSuccesss=myAffairDao.updateAffair(affair);
			
			//判断是否插入成功，创建输出json 
			if(isSuccesss!=true){
				return isSuccesss;
			}
			
		}
		return isSuccesss;
	}
}
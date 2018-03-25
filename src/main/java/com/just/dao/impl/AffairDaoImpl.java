package com.just.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import net.sf.json.JSONArray;

import com.just.dao.AffairDao;
import com.just.entity.Affair;
import com.just.entity.User;
import com.just.util.JDBCUtils;

public class AffairDaoImpl implements AffairDao {

	//连接，路
	Connection conn = null;
	//准备状态，车
	Statement statement = null;
	PreparedStatement prStatement = null;
	//结果集，货物
	ResultSet resultSet = null;

	public long addAffair(Affair affair) {
		//记录插入后数据库的id,如果为0则插入不成功
		long affairID=0;
		try {
			conn  = JDBCUtils.getConnection();
			//通过连接执行插入语句
			prStatement = conn.prepareStatement("insert into affair values (null,?,?,?,?,?,?,?,?,?)");
			prStatement.setLong(1, affair.get_id());
			prStatement.setLong(2, affair.getUserId());
			prStatement.setString(3, affair.getAffairType());
			prStatement.setString(4, affair.getAffairCgy());
			prStatement.setString(5, affair.getAffairRemark());
			prStatement.setString(6, affair.getAffairPsn());
			prStatement.setString(7, affair.getAffairTime());
			prStatement.setInt(8,affair.getAffairState());
			prStatement.setString(9, affair.getAffairName());
			//执行插入 返回是否成功
			affairID = prStatement.executeUpdate();
			
			//如果插入成功  则获取刚刚插入数据的id
			if(affairID!=0){
				prStatement=conn.prepareStatement("select last_insert_id()");
				resultSet=prStatement.executeQuery();
				if(resultSet.first())
				{
					affairID=resultSet.getLong("LAST_INSERT_ID()");
				}
			}
			return affairID;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtils.close(resultSet, prStatement, conn);
		}
	}

	public boolean delAffair(long userId,long _id) {
		try {
			conn  = JDBCUtils.getConnection();
			prStatement = conn.prepareStatement("update affair set affairState=5 where _id=? and userId = ?");
			prStatement.setLong(1, _id);
			prStatement.setLong(2, userId);
			int i  = prStatement.executeUpdate();
	
			if(i == 0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(resultSet, prStatement, conn);
		}
	}

	public boolean updateAffair(Affair affair) {
		try {
			conn  = JDBCUtils.getConnection();
			prStatement=conn.prepareStatement("update affair set affairType=?,affairCgy=?,affairRemark=?,affairPsn=?,affairTime=?,affairState=?,affairName=? where _id=? and userId = ?");
			prStatement.setString(1, affair.getAffairType());
			prStatement.setString(2, affair.getAffairCgy());
			prStatement.setString(3, affair.getAffairRemark());
			prStatement.setString(4, affair.getAffairPsn());
			prStatement.setString(5, affair.getAffairTime());
			prStatement.setInt(6, affair.getAffairState());
			prStatement.setString(7, affair.getAffairName());
			prStatement.setLong(8, affair.get_id());
			prStatement.setLong(9, affair.getUserId());
			int i =prStatement.executeUpdate();
			if(i ==0){
				return false;
			}
			else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(resultSet, prStatement, conn);
		}
	}
	
	public List<Affair> querybyUserId(long userId) {
		
		List<Affair> affairList=new ArrayList<Affair>();
		Affair affair=null;
		try {
			conn  = JDBCUtils.getConnection();
			//查询指定userId的所有事件，得到结果集
			prStatement = conn.prepareStatement("select * from affair where userId=? and affairState <> 5");
			prStatement.setLong(1, userId);
			resultSet=prStatement.executeQuery();
			
			//遍历结果集，以affair的形式放到ArrayList中
			while(resultSet.next())
			{
				affair=new Affair();
				affair.setId(resultSet.getLong("id"));
				affair.set_id(resultSet.getLong("_id"));
				affair.setAffairName(resultSet.getString("affairName"));
				affair.setAffairType(resultSet.getString("affairType"));
				affair.setAffairCgy(resultSet.getString("affairCgy"));
				affair.setAffairRemark(resultSet.getString("affairRemark"));
				affair.setAffairPsn(resultSet.getString("affairPsn"));
				affair.setAffairTime(resultSet.getString("affairTime"));
				affair.setAffairState(resultSet.getInt("affairState"));
				affair.setUserId(resultSet.getLong("userId"));
				affairList.add(affair);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(resultSet, prStatement, conn);
		}
		return affairList;
	}

}

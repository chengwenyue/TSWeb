package com.just.dao;

import java.util.List;
import java.util.Map;

import com.just.entity.Affair;

public interface AffairDao {
	
	public long addAffair(Affair affair);
	
	public boolean delAffair(long userId,long _id);

	public boolean updateAffair(Affair affair);

	public List<Affair> querybyUserId(long userId);
}

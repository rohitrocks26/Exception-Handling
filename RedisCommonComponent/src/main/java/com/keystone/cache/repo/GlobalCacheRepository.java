package com.keystone.cache.repo;

import java.util.Map;

import com.keystone.cache.model.Customer;

public interface GlobalCacheRepository {
	
	void save(Object global, long id , String KEY);
	Object find(int id, String KEY);
	Map<Long, Object> findAll(String KEY);
	void update(Object global ,long id, String KEY);
	void delete(Long id, String KEY);

}

package com.keystone.cache.repo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.keystone.cache.model.Customer;

@Repository
public class GlobalCacheRepositoryImpl implements GlobalCacheRepository {
	 
	private RedisTemplate<String, Object> redisTemplate;
	
	/*RedisTemplate provides DefaultHashOperations instance that can do hash-related operations for data manipulation.
	  To get DefaultHashOperations instance, we call RedisTemplate.opsForHash(): */
	
	private HashOperations<String, Long, Object> hashOperations;
 
	@Autowired
	public GlobalCacheRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
 
	@Override
	public void save(Object global, long id, String KEY) {
		hashOperations.put(KEY,  id,global);
		
	}
 
	@Override
	public Object find(int id, String KEY) {
		return hashOperations.get(KEY, (long)id);
	}
 
	@Override
	public Map<Long, Object> findAll(String KEY) {
		if(redisTemplate.hasKey(KEY))
		return hashOperations.entries(KEY);
		else
		{
			System.out.println("Key Not Available");
			//Perform DB Operation and Put it in Redis
			return null;
		}
	}
 
	@Override
	public void update(Object global, long id, String KEY) {
		hashOperations.put(KEY,id,global);
	}
 
	@Override
	public void delete(Long id,String KEY) {
		hashOperations.delete(KEY, id);
	}
 
}

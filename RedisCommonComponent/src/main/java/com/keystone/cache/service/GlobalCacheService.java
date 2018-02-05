package com.keystone.cache.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.keystone.cache.repo.GlobalCacheRepository;

@Component
public class GlobalCacheService {
	
	@Autowired
	private GlobalCacheRepository globalRepository;
 
	
	public String save(Object obj, long id , String KEY) {
		
		globalRepository.save(obj,id,KEY);
 
		return "Done";
	}
 
	
	public String findAll(@RequestParam("Key") String KEY) {
		String result = "";
		Map<Long, Object> global = globalRepository.findAll(KEY);
 
		for (Object customer : global.values()) {
			result += customer.toString() + "<br>";
		}
		return result;
	}
 
	
	public String findById( int id ,String KEY) {
		String result = "";
		result = globalRepository.find(id, KEY).toString();
		return result;
	}
 
	
	public String update(Object globalObj ,int id,String KEY) {
		
		globalRepository.update(globalObj,id,KEY);
 
		return "Done";
	}
 
	
	public String deleteById( Long id,String KEY) {
		globalRepository.delete(id, KEY);
 
		return "Done";
	}

}

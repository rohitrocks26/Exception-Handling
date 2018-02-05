package com.keystone.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keystone.cache.service.GlobalCacheService;
import com.keystone.controller.SampleController;
import com.keystone.daoimpl.SampleDAOimpl;
import com.keystone.dto.AdminPlanDTO;
import com.keystone.model.AdminPlan;

import javassist.NotFoundException;

@Component
public class sampleService {
	
	private static Logger log = Logger.getLogger(SampleController.class);
	
	@Autowired
	private GlobalCacheService globalService;
	
	@Autowired
	private SampleDAOimpl smpl;
	
	public String saveObject(AdminPlanDTO dto){	
	
		AdminPlan a1 = new AdminPlan(dto.getId(), dto.getName(), dto.getProviderId());
		
		log.info("Saving the object into Database");
		//Save in Database
		smpl.insert(a1);
		//Save in Redis (object , Unique Key for each object , KEY)
		globalService.save(a1,1,"AdminPlan");
		
		return "Saved";
	}
	
	public List<AdminPlanDTO> getAdminPlan() throws NotFoundException
	{
		List<AdminPlanDTO> adminPlan = smpl.findAll();
		
		if (adminPlan!=null && !adminPlan.isEmpty())
			return adminPlan;
		else
		{		
			throw new NotFoundException("No Admin Plan Available");
		}	
	}

}

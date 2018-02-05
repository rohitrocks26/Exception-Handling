package com.keystone.dao;

import java.util.List;
import java.util.Map;

import com.keystone.dto.AdminPlanDTO;
import com.keystone.model.AdminPlan;

public interface SampleDAO {
	
	public void insert(AdminPlan adminPlan);
	public List<AdminPlanDTO> findAll();

}

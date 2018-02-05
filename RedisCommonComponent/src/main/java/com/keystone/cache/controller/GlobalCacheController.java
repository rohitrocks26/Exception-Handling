/*package com.keystone.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keystone.cache.model.AdminPlan;
import com.keystone.cache.model.Customer;
import com.keystone.cache.repo.GlobalCacheRepository;
import com.keystone.cache.service.GlobalCacheService;

@RestController
public class GlobalCacheController {
	
	@Autowired
	private GlobalCacheService globalService;
	
	@RequestMapping("/saveAdminPlan")
	public String saveObject()
	{	
		AdminPlan a1 = new AdminPlan(1, "BlueShield", 1000);
		AdminPlan a2 = new AdminPlan(2, "BlueCross", 1001);
		AdminPlan a3 = new AdminPlan(3, "Major Medical", 1002);
		
		globalService.save(a1,1,"AdminPlan");
		globalService.save(a2,2,"AdminPlan");
		globalService.save(a3,3 ,"AdminPlan");
		
		return "done";
	}
	
	@RequestMapping("/saveCustomer")
	public String saveCustomer()
	{
		
		Customer c1 = new Customer(1, "Jack", "Smith");
		Customer c2 = new Customer(2, "Tom", "Smith");
		Customer c3 = new Customer(3, "David", "Smith");
		Customer c4 = new Customer(4, "Harry", "Smith");
		Customer c5 = new Customer(5, "Tommy", "Smith");
		
		globalService.save(c1, 1, "customer");
		globalService.save(c2, 2, "customer");
		globalService.save(c3, 3, "customer");
		globalService.save(c4, 4, "customer");
		globalService.save(c5, 5, "customer");
		
		return "done";
	}
	
	@RequestMapping("/findall")
	public String findAll(@RequestParam("Key") String KEY) {
		
		String result = globalService.findAll(KEY);
		return result;
	}
	
	@RequestMapping("/findById")
	public String findbyID(@RequestParam("id") int id , @RequestParam("Key") String KEY)
	{
		String result = globalService.findById(id, KEY);
		return result;
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("id") int id,@RequestParam("Key") String KEY)
	{
		Customer c6 = new Customer(5, "Rohit", "Choudhary");
		String result = globalService.update(c6 ,id, KEY);
		return result;
	}
	
	@RequestMapping("/delete")
	public String deleteById(@RequestParam("id") Long id, @RequestParam("Key") String KEY)
	{
		String result = globalService.deleteById(id, KEY);
		return result;
	}

}
*/
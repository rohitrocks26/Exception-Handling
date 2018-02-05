package com.keystone.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.keystone.common.exception.factory.ExceptionFactory;
import com.keystone.common.exception.types.BusinessValidationException;
import com.keystone.common.exception.types.DataAccessException;
import com.keystone.common.exception.types.ResourceNotFoundException;
import com.keystone.common.exception.types.SecurityException;
import com.keystone.dto.AdminPlanDTO;
import com.keystone.service.sampleService;

import javassist.NotFoundException;


@RestController
public class SampleController {
	
	private static Logger log = Logger.getLogger(SampleController.class);
	
	@Autowired
	private sampleService sr;
	
	//This method will save the object in Redis and in Mysql DB
	
	@RequestMapping(path = "/saveAdminPlan", method = RequestMethod.POST, consumes = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public String saveObject(@RequestBody AdminPlanDTO adminPlandto) throws Exception
	{	
		log.info("Saving the object into Redis");
		if(sr.saveObject(adminPlandto).isEmpty())
			throw new NotFoundException("Key Not Available");
		return "Saved";
	}
	
	@RequestMapping(path="/getAllAdminPlan",method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public List<AdminPlanDTO> getObject() throws NotFoundException{
		
		log.info("Fetching the Object from Database");
		return sr.getAdminPlan();
		
	}
	
	/**
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(path="/testRNFException",method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public String testRNFException() throws ResourceNotFoundException {
		
		log.info("Accessing a resource");
		throw ExceptionFactory.createResourceNotFoundException("Resource Not Found exception message", new Exception("Resource Not Found Exception"));
		/*Object accessResponse = "";
		
		try {
			accessResponse = restTemplate.getForObject("http://localhost:8091/rest/hello/server-not-available", String.class);
		}
		catch(Exception rce) {
			
			throw ExceptionFactory.createResourceNotFoundException("Resource Not Found exception message", rce);		
		}*/
		//return accessResponse.toString();
	}
	
	@RequestMapping(path="/testDAException",method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public String testDAException() throws DataAccessException {
		
		log.info("Fetching the Object from Database");
		
		throw ExceptionFactory.createDataAccessException("Data access exception message", new Exception("Source exception message"));		
	}
	
	@RequestMapping(path="/testBVException",method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public String testBVException() throws BusinessValidationException {
		
		log.info("Validating the Business Data");
		
		throw ExceptionFactory.createBusinessValidationException(" Business Validation exception message" + new Exception("Source exception message").getMessage(), new Exception("Source exception message"));		
	}
	
	@RequestMapping(path="/testSecurityException",method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public String testSecurityException() throws SecurityException {
		
		log.info("Authenticating and authorizing");
		
		throw ExceptionFactory.createSecurityException("Security exception message", new Exception("Source exception message"));		
	}
	
	@RequestMapping(path="/testException",method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml; charset=utf-8"})
	public String testException() throws Exception {
		
		log.info("Handling potential general exceptions");
		
		throw new Exception("potential general exceptions");		
	}
}

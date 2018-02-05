package com.keystone.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminPlanDTO {

	@Min(1)
	private long id;
	@NotNull
	private String name;
	@Min(1)
	private long ProviderId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProviderId() {
		return ProviderId;
	}
	public void setProviderId(long providerId) {
		ProviderId = providerId;
	}
}

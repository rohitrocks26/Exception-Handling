package com.keystone.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AdminPlan")
public class AdminPlan implements Serializable{
	
	@Id
	private long id;
	private String name;
	private long ProviderId;
	
	public AdminPlan(long id, String name, long providerId) {
		super();
		this.id = id;
		this.name = name;
		ProviderId = providerId;
	}
	
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

	@Override
	public String toString() {
		return "AdminPlan [id=" + id + ", name=" + name + ", ProviderId=" + ProviderId + "]";
	}
	
	
	
	
	
}
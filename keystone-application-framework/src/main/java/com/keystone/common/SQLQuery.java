package com.keystone.common;

// This enum defines reusable SQL query Strings

public enum SQLQuery {
	
	insertAdminPlan("INSERT INTO " + TableEntries.AdminPlan.getTableName()
	+ "(id, "
	+ "name, "
	+ "ProviderId) "
	+ "VALUES (?, ?, ?)"),
	
	selectAdminPlan("SELECT * FROM " + TableEntries.AdminPlan.getTableName());
	
	
	
	private String sql;
	
	SQLQuery(String sql) {
		this.sql = sql;
	}

	
	public String getSql() {
		return sql;
	}
	
	
	
}

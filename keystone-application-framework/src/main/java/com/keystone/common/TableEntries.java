package com.keystone.common;

public enum TableEntries {
	
	AdminPlan("AdminPlan","AdminPlan_ID",com.keystone.model.AdminPlan.class);
		
	private final String tableName;
	private final String tableId;
	private final Class<?> tableClass;

	private TableEntries(String tableName, String tableId, Class<?> tableClass) {
		this.tableName = tableName;
		this.tableId = tableId;
		this.tableClass = tableClass;
	}
	
	public String getTableName() {
		return tableName;
	}
	public String getTableId() {
		return tableId;
	}
	public Class<?> getTableClass() {
		return tableClass;
	}
	
}
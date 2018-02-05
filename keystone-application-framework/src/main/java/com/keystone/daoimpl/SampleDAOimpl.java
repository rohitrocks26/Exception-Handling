package com.keystone.daoimpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.keystone.common.SQLQuery;
import com.keystone.common.TableEntries;
import com.keystone.controller.SampleController;
import com.keystone.dao.SampleDAO;
import com.keystone.dto.AdminPlanDTO;
import com.keystone.model.AdminPlan;

@Repository
public class SampleDAOimpl implements SampleDAO{
	
	private static Logger log = Logger.getLogger(SampleController.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public void insert(AdminPlan adminPlan)
	{
		final String GENERATED_COLUMNS[] = { TableEntries.AdminPlan.getTableId() };
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQLQuery.insertAdminPlan.getSql(), GENERATED_COLUMNS);
				ps.setLong(1, adminPlan.getId());
				ps.setString(2, adminPlan.getName());
				ps.setLong(3, adminPlan.getProviderId());
				return ps;
			}
		});
		
		log.info("Data inserted into Database Successfully");
		
	}
	
	@Override
	public List<AdminPlanDTO> findAll()
	{
		
		String SQL = "select * from adminplan";
	    List <AdminPlanDTO> adminplan = jdbcTemplate.query(SQL, new AdminPlanMapper());
		return 	adminplan;
	}

}

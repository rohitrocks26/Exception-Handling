package com.keystone.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.keystone.dto.AdminPlanDTO;

public class AdminPlanMapper implements RowMapper<AdminPlanDTO> {
   public AdminPlanDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
      AdminPlanDTO adminplandto = new AdminPlanDTO();
      adminplandto.setId(rs.getLong("id"));
      adminplandto.setName(rs.getString("name"));
      adminplandto.setId(rs.getLong("ProviderId"));
      
      return adminplandto;
   }
}
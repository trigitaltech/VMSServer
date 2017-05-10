package org.mifosplatform.details.empinfo.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.details.empinfo.data.EmpData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


@Service
public class EmpInfoReadPlatformServiceImp implements EmpInfoReadPlatformService {

private final JdbcTemplate jdbcTemplate;

	@Autowired
	public EmpInfoReadPlatformServiceImp(final TenantAwareRoutingDataSource dataSource) {
		
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@Override
	public Collection<EmpData> retrieveAllEmp() {
		final EmpMapper mapper= new EmpMapper();
		final String sql="select "+mapper.empPeriodSchema()+" where dp.is_deleted='N'";
		return this.jdbcTemplate.query(sql,mapper, new Object[]{});
	}

	@Override
	public Collection<EmpData> retrieveEmpDetails() {

		final EmpMapper depositProductMapper= new EmpMapper();
		final String sql="select "+depositProductMapper.empPeriodSchema ();
		return this.jdbcTemplate.query(sql,depositProductMapper, new Object[]{});

	}
	
	
	@Override
	public EmpData retrieveEmpData(final Long empId) {
		final EmpMapper depositProductMapper=new EmpMapper();
		final String sql = "select "+ depositProductMapper.empPeriodSchema() +" where dp.id = ? and dp.is_deleted= 'N'";

		return this.jdbcTemplate.queryForObject(sql, depositProductMapper, new Object[]{empId});
	}
	
	
	

	private static final class EmpMapper implements RowMapper<EmpData>{

		public String empPeriodSchema(){
			return " dp.id as id,dp.name as name,dp.dob as dob,dp.sal as sal from b_empinfo dp ";

		}

		@Override
		public EmpData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String name = rs.getString("name");
			final Date dob = rs.getDate("dob");
			final BigDecimal sal = rs.getBigDecimal("sal");
			return new EmpData(id,name,dob,sal);
		}
	}
	
	@Override
	public List<EmpData>retrieveEmpDatabyOrder(final Long orderId) {
		
	    final EmpMapper mapper= new EmpMapper();
		final String sql="select "+mapper.empPeriodSchema()+" , b_empinfo o where o.name= dp.id and o.id=? and dp.is_deleted='N'";
		return this.jdbcTemplate.query(sql,mapper, new Object[]{ orderId });
	}
}

/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.vendormanagement.vendor.data.VendorManagementData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class VendorManagementReadPlatformServiceImpl implements VendorManagementReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;

    @Autowired
    public VendorManagementReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<VendorManagementData> retrieveAllVendorManagements() {
		try {
			this.context.authenticatedUser();
			final RetrieveMapper mapper = new RetrieveMapper();
			final String sql = "SELECT  " + mapper.schema() + "where vm.is_deleted = 'N'";

			return this.jdbcTemplate.query(sql, mapper, new Object[] { });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	private static final class RetrieveMapper implements RowMapper<VendorManagementData> {

		public String schema() {
			return " vm.id as id, vm.vendor_name as vendorName, vm.entity_type as entityType, vm.other_entity as otherEntity, "+
					" vm.contact_name as contactName, vm.address_1 as address1, vm.address_2 as address2, "+
					" vm.address_3 as address3, vm.country as country,vm.state as state,vm.city as city, "+
					" vm.postal_code as postalCode,vm.residential_status as residentialStatus,vm.other_residential as otherResidential, " +
					" vm.landline_no as landlineNo,vm.mobile_no as mobileNo,vm.fax as fax,vm.email_id as emailId,vbd.id as detailId, vbd.bank_name as bankName, " +
					" vbd.account_no as accountNo,vbd.branch as branch,vbd.ifsc_code as ifscCode,vbd.swift_code as swiftCode,vbd.iban_code as ibanCode, " +
					" vbd.account_name as accountName,vbd.cheque_no as chequeNo, " +
					" cv.code_value as entityName,cvv.code_value as resiStatusName " +
					" from b_vendor_management vm "+
					" left join b_vendor_bank_details vbd ON vm.id = vbd.vendor_id "+
					" join m_code_value cv on vm.entity_type = cv.id "+
					" join m_code_value cvv on vm.residential_status = cvv.id ";

		}

		@Override
		public VendorManagementData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {
			
			final Long id = rs.getLong("id");
			final String vendorName = rs.getString("vendorName");
			final Long entityType = rs.getLong("entityType");
			final String otherEntity = rs.getString("otherEntity");
			final String contactName = rs.getString("contactName");
			final String address1 = rs.getString("address1");
			final String address2 = rs.getString("address2");
			final String address3 = rs.getString("address3");
			final String country = rs.getString("country");
			final String state = rs.getString("state");
			final String city = rs.getString("city");
			final String postalCode = rs.getString("postalCode");
			final Long residentialStatus = rs.getLong("residentialStatus");
			final String otherResidential = rs.getString("otherResidential");
			final String landLineNo = rs.getString("landLineNo");
			final String mobileNo = rs.getString("mobileNo");
			final String fax = rs.getString("fax");
			final String emailId = rs.getString("emailId");
			final Long detailId = rs.getLong("detailId");
			final String bankName = rs.getString("bankName");
			final String accountNo = rs.getString("accountNo");
			final String branch = rs.getString("branch");
			final String ifscCode = rs.getString("ifscCode");
			final String swiftCode = rs.getString("swiftCode");
			final String ibanCode = rs.getString("ibanCode");
			final String accountName = rs.getString("accountName");
			final String chequeNo = rs.getString("chequeNo");
			final String entityName = rs.getString("entityName");
			final String resiStatusName = rs.getString("resiStatusName");
			
			return new VendorManagementData(id, vendorName, entityType, otherEntity, contactName, address1, address2,
					address3, country, state, city,postalCode,residentialStatus,otherResidential,landLineNo,mobileNo,fax,emailId,
					detailId,bankName,accountNo,branch,ifscCode,swiftCode,ibanCode,accountName,chequeNo,entityName,resiStatusName);
			
		}
	}
	
	@Override
	public VendorManagementData retrieveSigleVendorManagement(Long vendorId) {
		try {
			context.authenticatedUser();
			RetrieveMapper mapper = new RetrieveMapper();
			final String sql = "SELECT  " + mapper.schema() +" where vm.id = "+vendorId +" and vm.is_deleted = 'N'";

			return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

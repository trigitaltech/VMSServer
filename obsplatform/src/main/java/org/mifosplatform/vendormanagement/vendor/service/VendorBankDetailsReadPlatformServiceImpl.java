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
import org.mifosplatform.vendormanagement.vendor.data.VendorBankDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class VendorBankDetailsReadPlatformServiceImpl implements VendorBankDetailsReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;

    @Autowired
    public VendorBankDetailsReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<VendorBankDetailsData> retrieveAllVendorbankDetails() {
		try {
			this.context.authenticatedUser();
			final RetrieveMapper mapper = new RetrieveMapper();
			final String sql = "SELECT  " + mapper.schema() + "where vbd.is_deleted = 'N'";

			return this.jdbcTemplate.query(sql, mapper, new Object[] { });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	private static final class RetrieveMapper implements RowMapper<VendorBankDetailsData> {

		public String schema() {
			return " vbd.id as id, vbd.bank_name as bankName, vbd.account_no as accountNo, vbd.branch as branch, "+
					" vbd.ifsc_code as ifscCode, vbd.swift_code as swiftCode, vbd.iban_code as ibanCode, "+
					" vbd.account_name as accountName, vbd.cheque_no as chequeNo" +
					" from b_vendor_bank_details vbd ";
					

		}

		@Override
		public VendorBankDetailsData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {
			
			final Long id = rs.getLong("id");
			final String bankName = rs.getString("bankName");
			final String accountNo = rs.getString("accountNo");
			final String branch = rs.getString("branch");
			final String ifscCode = rs.getString("ifscCode");
			final String swiftCode = rs.getString("swiftCode");
			final String ibanCode = rs.getString("ibanCode");
			final String accountName = rs.getString("accountName");
			final String chequeNo = rs.getString("chequeNo");
			
			return new VendorBankDetailsData(id, bankName, accountNo, branch, ifscCode, swiftCode, ibanCode,
					accountName, chequeNo);
			
		}
	}
	
	@Override
	public VendorBankDetailsData retrieveSigleVendorBankDetails(Long vendorId) {
		try {
			this.context.authenticatedUser();
			final RetrieveMapper mapper = new RetrieveMapper();
			final String sql = "SELECT  " + mapper.schema() +" where vbd.id = "+vendorId +" and vbd.is_deleted = 'N'";

			return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

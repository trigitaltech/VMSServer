/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.vendormanagement.vendor.data.VendorOtherDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class VendorOtherDetailsReadPlatformServiceImpl implements VendorOtherDetailsReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;

    @Autowired
    public VendorOtherDetailsReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<VendorOtherDetailsData> retrieveAllVendorOtherDetails() {
		try {
			this.context.authenticatedUser();
			final RetrieveMapper mapper = new RetrieveMapper();
			final String sql = "SELECT  " + mapper.schema();/* + "where vod.is_deleted = 'N'";*/

			return this.jdbcTemplate.query(sql, mapper, new Object[] { });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	private static final class RetrieveMapper implements RowMapper<VendorOtherDetailsData> {

		public String schema() {
			return " vod.id as id, vod.pan_no as panNo, vod.pan_file_name as panFileName, vod.incur_certification as incurCertification, "+
					" vod.certificate_file_name as certificateFileName, vod.st_no as stNo, vod.st_file_name as stFileName, "+
					" vod.msm_status as msmStatus, vod.msm_reg_no as msmRegNo,vod.msm_reg_date as msmRegDate,vod.msm_file_name as msmFileName," +
					" vod.vat_no as vatNo, vod.vat_file_name as vatFileName,vod.gst_no as gstNo,vod.gst_file_name as gstFileName,vod.cst_no as cstNo," +
					" vod.cst_file_name as cstFileName" +
					" from b_vendor_other_details vod " +
					" join b_vendor_management vm on vm.id = vod.vendor_id ";
					

		}

		@Override
		public VendorOtherDetailsData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {
			
			final Long id = rs.getLong("id");
			final String panNo = rs.getString("panNo");
			final String panFileName = rs.getString("panFileName");
			final String incurCertification = rs.getString("incurCertification");
			final String certificateFileName = rs.getString("certificateFileName");
			final String stNo = rs.getString("stNo");
			final String stFileName = rs.getString("stFileName");
			final Long msmStatus = rs.getLong("msmStatus");
			final String msmRegNo = rs.getString("msmRegNo");
			final Date msmRegDate = rs.getDate("msmRegDate");
			final String msmFileName = rs.getString("msmFileName");
			final String vatNo = rs.getString("vatNo");
			final String vatFileName = rs.getString("vatFileName");
			final String gstNo = rs.getString("gstNo");
			final String gstFileName = rs.getString("gstFileName");
			final String cstNo = rs.getString("cstNo");
			final String cstFileName = rs.getString("cstFileName");
			
			return new VendorOtherDetailsData(id, panNo, panFileName, incurCertification, certificateFileName, stNo, stFileName,
					msmStatus, msmRegNo,msmRegDate,msmFileName,vatNo,vatFileName,gstNo,gstFileName,cstNo,cstFileName);
			
		}
	}
	
	@Override
	public VendorOtherDetailsData retrieveSigleVendorOtherDetails(Long vendorId) {
		try {
			this.context.authenticatedUser();
			final RetrieveMapper mapper = new RetrieveMapper();
			final String sql = "SELECT  " + mapper.schema() +" where vod.vendor_id = "+vendorId +" ";

			return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

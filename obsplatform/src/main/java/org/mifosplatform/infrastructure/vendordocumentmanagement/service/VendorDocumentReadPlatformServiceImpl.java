/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.vendordocumentmanagement.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.infrastructure.vendordocumentmanagement.data.VendorDocumentData;
import org.mifosplatform.infrastructure.vendordocumentmanagement.exception.VendorFileDocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * @author hugo
 *
 */
@Service
public class VendorDocumentReadPlatformServiceImpl implements VendorDocumentReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
  

    @Autowired
    public VendorDocumentReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
      
    }

    @Override
    public Collection<VendorDocumentData> retrieveAllDocuments(final String entityType) {

        this.context.authenticatedUser();

        // TODO verify if the entities are valid and a user
        // has data
        // scope for the particular entities
        final VendorDocumentMapper mapper = new VendorDocumentMapper(true,true);
        final String sql = "select " + mapper.schema() + " and d.is_delete='N' order by d.id";
        return this.jdbcTemplate.query(sql, mapper, new Object[] { entityType });
    }

    @Override
    public VendorDocumentData retrieveDocument(final String entityType, final Long documentId) {

        try {
            this.context.authenticatedUser();

            // TODO verify if the entities are valid and a
            // user has data
            // scope for the particular entities
            final VendorDocumentMapper mapper = new VendorDocumentMapper(true,true);
            final String sql = "select " + mapper.schema() + " and d.id=? ";
            return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { entityType, documentId });
        } catch (final EmptyResultDataAccessException e) {
            throw new VendorFileDocumentNotFoundException(entityType, documentId);
        }
    }
    
    
    @Override
    public FileData retrieveFileData(final String entityType, final Long documentId, final String name) {
    try {
    final VendorDocumentMapper mapper = new VendorDocumentMapper(false,false);
    final VendorDocumentData documentData = fetchDocumentDetails(entityType, documentId, mapper,name);
    //final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository(documentData.storageType());
    return this.fetchFile(documentData);
   
    } catch (final EmptyResultDataAccessException e) {
    throw new VendorFileDocumentNotFoundException(entityType, documentId);
    }
    }

	private VendorDocumentData fetchDocumentDetails(final String entityType, final Long documentId,
    		final VendorDocumentMapper mapper,String name) {
    		final String sql = "select " + mapper.schema() + " where d.parent_entity_type=? and d.name= '"+name+"' and d.id=? ";
    		return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { entityType, documentId });
    		}

    private static final class VendorDocumentMapper implements RowMapper<VendorDocumentData> {

        private final boolean hideLocation;
        private final boolean hideStorageType;

        public VendorDocumentMapper(final boolean hideLocation,final boolean hideStorageType) {
            this.hideLocation = hideLocation;
            this.hideStorageType = hideStorageType;
        }

        public String schema() {
            return "d.id as id, d.parent_entity_type as parentEntityType, d.name as name, "
                    + " d.file_name as fileName, d.size as fileSize, d.type as fileType, "
                    + " d.location as location,d.storage_type_enum as storageType"
                    + " from m_document d ";
        }

        @Override
        public VendorDocumentData mapRow(final ResultSet rs, final int rowNum) throws SQLException {

            final Long id = JdbcSupport.getLong(rs, "id");
            final Long fileSize = JdbcSupport.getLong(rs, "fileSize");
            final String parentEntityType = rs.getString("parentEntityType");
            final String name = rs.getString("name");
            final String fileName = rs.getString("fileName");
            final String fileType = rs.getString("fileType");
            String location = null;
            Integer storageType = null;
            if (!this.hideLocation) {
                location = rs.getString("location");
            }
            if (!this.hideStorageType) {
            	storageType = rs.getInt("storageType");
            	}

            return new VendorDocumentData(id, parentEntityType, name, fileName, fileSize, fileType, location,storageType);
        }
    }
    
	public FileData fetchFile(VendorDocumentData documentData) {
		
		 final File file = new File(documentData.fileLocation());
		 return new FileData(file, documentData.fileName(), documentData.contentType());
	}
	
    
}
package org.mifosplatform.scheduledjobs.dataupload.service;

import org.mifosplatform.infrastructure.core.service.Page;
import org.mifosplatform.organisation.office.service.SearchSqlQuery;
import org.mifosplatform.scheduledjobs.dataupload.data.UploadStatusData;

public interface DataUploadReadPlatformService {

    Page<UploadStatusData> retrieveAllUploadStatusData(SearchSqlQuery searchUploads);
}

/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.vendordocumentmanagement.service;

import java.util.Collection;

import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.infrastructure.vendordocumentmanagement.data.VendorDocumentData;

public interface VendorDocumentReadPlatformService {

    Collection<VendorDocumentData> retrieveAllDocuments(String entityType);

    VendorDocumentData retrieveDocument(String entityType, Long documentId);

	FileData retrieveFileData(String entityType, Long documentId,String name);
	
}
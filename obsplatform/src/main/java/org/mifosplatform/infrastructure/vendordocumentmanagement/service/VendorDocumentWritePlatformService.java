/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.vendordocumentmanagement.service;

import java.io.InputStream;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.vendordocumentmanagement.command.VendorDocumentCommand;
import org.springframework.security.access.prepost.PreAuthorize;

public interface VendorDocumentWritePlatformService {

    @PreAuthorize(value = "hasAnyRole('ALL_FUNCTIONS', 'CREATE_VENDORDOCUMENT')")
    Long createDocument(VendorDocumentCommand documentCommand, InputStream inputStream);

    @PreAuthorize(value = "hasAnyRole('ALL_FUNCTIONS', 'UPDATE_VENDORDOCUMENT')")
    CommandProcessingResult updateDocument(VendorDocumentCommand documentCommand, InputStream inputStream);

    @PreAuthorize(value = "hasAnyRole('ALL_FUNCTIONS', 'DELETE_VENDORDOCUMENT')")
    CommandProcessingResult deleteDocument(VendorDocumentCommand documentCommand);

}
/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.vendordocumentmanagement.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.core.service.FileUtils;
import org.mifosplatform.infrastructure.documentmanagement.exception.DocumentManagementException;
import org.mifosplatform.infrastructure.documentmanagement.exception.InvalidEntityTypeForDocumentManagementException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.infrastructure.vendordocumentmanagement.command.VendorDocumentCommand;
import org.mifosplatform.infrastructure.vendordocumentmanagement.command.VendorDocumentCommandValidator;
import org.mifosplatform.infrastructure.vendordocumentmanagement.domain.VendorDocument;
import org.mifosplatform.infrastructure.vendordocumentmanagement.domain.VendorDocumentRepository;
import org.mifosplatform.infrastructure.vendordocumentmanagement.exception.VendorDocumentManagementException;
import org.mifosplatform.infrastructure.vendordocumentmanagement.exception.VendorFileDocumentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendorDocumentWritePlatformServiceJpaRepositoryImpl implements VendorDocumentWritePlatformService {

    private final static Logger logger = LoggerFactory.getLogger(VendorDocumentWritePlatformServiceJpaRepositoryImpl.class);

    private final PlatformSecurityContext context;
    private final VendorDocumentRepository vendordocumentRepository;

    @Autowired
    public VendorDocumentWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context, final VendorDocumentRepository vendordocumentRepository) {
        this.context = context;
        this.vendordocumentRepository = vendordocumentRepository;
    }

    @Transactional
    @Override
    public Long createDocument(final VendorDocumentCommand documentCommand, final InputStream inputStream) {
        try {
            this.context.authenticatedUser();

            final VendorDocumentCommandValidator validator = new VendorDocumentCommandValidator(documentCommand);

            validateParentEntityType(documentCommand);

            validator.validateForCreate();

            final String fileUploadLocation = FileUtils.generateVendorFileParentDirectory(documentCommand.getParentEntityType());

            /** Recursively create the directory if it does not exist **/
            if (!new File(fileUploadLocation).isDirectory()) {
                new File(fileUploadLocation).mkdirs();
            }

            final String fileLocation = FileUtils.saveToFileSystem(inputStream, fileUploadLocation, documentCommand.getFileName());

            final VendorDocument document = VendorDocument.createNew(documentCommand.getParentEntityType(), 
                    documentCommand.getName(), documentCommand.getFileName(), documentCommand.getSize(), documentCommand.getType(),
                    fileLocation);

            this.vendordocumentRepository.save(document);

            return document.getId();
        } catch (final DataIntegrityViolationException dve) {
            logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("error.msg.document.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource.");
        } catch (final IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
            throw new DocumentManagementException(documentCommand.getName());
        }
    }

    @Transactional
    @Override
    public CommandProcessingResult updateDocument(final VendorDocumentCommand documentCommand, final InputStream inputStream) {
        try {
            this.context.authenticatedUser();

            String oldLocation = null;
            final VendorDocumentCommandValidator validator = new VendorDocumentCommandValidator(documentCommand);
            validator.validateForUpdate();
            // TODO check if entity id is valid and within data scope for the
            // user
            final VendorDocument documentForUpdate = this.vendordocumentRepository.findOne(documentCommand.getId());
            if (documentForUpdate == null) { throw new VendorFileDocumentNotFoundException(documentCommand.getParentEntityType(),
                     documentCommand.getId()); }
            oldLocation = documentForUpdate.getLocation();
            // if a new file is also passed in
            if (inputStream != null && documentCommand.isFileNameChanged()) {
                final String fileUploadLocation = FileUtils.generateVendorFileParentDirectory(documentCommand.getParentEntityType());

                /** Recursively create the directory if it does not exist **/
                if (!new File(fileUploadLocation).isDirectory()) {
                    new File(fileUploadLocation).mkdirs();
                }

                // TODO provide switch to toggle between file system appender
                // and Amazon S3 appender
                final String fileLocation = FileUtils.saveToFileSystem(inputStream, fileUploadLocation, documentCommand.getFileName());
                documentCommand.setLocation(fileLocation);
            }

            documentForUpdate.update(documentCommand);

            if (inputStream != null && documentCommand.isFileNameChanged()) {
                // delete previous file
                deleteFile(documentCommand.getName(), oldLocation);
            }

            this.vendordocumentRepository.saveAndFlush(documentForUpdate);

            return new CommandProcessingResult(documentForUpdate.getId());
        } catch (final DataIntegrityViolationException dve) {
            logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("error.msg.document.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource.");
        } catch (final IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
            throw new DocumentManagementException(documentCommand.getName());
        }
    }

    @Transactional
    @Override
    public CommandProcessingResult deleteDocument(final VendorDocumentCommand documentCommand) {
        this.context.authenticatedUser();

        validateParentEntityType(documentCommand);
        // TODO: Check document is present under this entity Id
        final VendorDocument document = this.vendordocumentRepository.findOne(documentCommand.getId());
        if (document == null) { throw new VendorFileDocumentNotFoundException(documentCommand.getParentEntityType(),
                                documentCommand.getId()); }

        this.vendordocumentRepository.delete(document);
        deleteFile(document.getName(), document.getLocation());
        return new CommandProcessingResult(document.getId());
    }

    private void deleteFile(final String documentName, final String location) {
        final File fileToBeDeleted = new File(location);
        final boolean fileDeleted = fileToBeDeleted.delete();
        if (!fileDeleted) { throw new VendorDocumentManagementException(documentName); }
    }

    private void validateParentEntityType(final VendorDocumentCommand documentCommand) {
        if (!checkValidEntityType(documentCommand.getParentEntityType())) { throw new InvalidEntityTypeForDocumentManagementException(
                documentCommand.getParentEntityType()); }
    }

    private static boolean checkValidEntityType(final String entityType) {

        for (final DOCUMENT_MANAGEMENT_ENTITY entities : DOCUMENT_MANAGEMENT_ENTITY.values()) {
            if (entities.name().equalsIgnoreCase(entityType)) { return true; }
        }
        return false;
    }

    /*** Entities for Vendor Document Management **/
    public static enum DOCUMENT_MANAGEMENT_ENTITY {
        VENDOR;

        @Override
        public String toString() {
            return name().toString().toLowerCase();
        }
    }
}
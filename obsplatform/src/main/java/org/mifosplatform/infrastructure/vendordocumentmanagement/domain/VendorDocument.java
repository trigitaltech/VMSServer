/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.vendordocumentmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.vendordocumentmanagement.command.VendorDocumentCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author hugo
 *
 */
@Entity
@Table(name = "m_vendor_document")
public class VendorDocument extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "parent_entity_type", length = 50)
    private String parentEntityType;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "file_name", length = 250)
    private String fileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "location", length = 500)
    private String location;
    
    @Column(name="is_delete")
    private char deleted = 'N';
    
    public void delete() {
	    this.name=this.name+"_DEL_"+this.fileName;
		this.deleted = 'Y';
    }

    public VendorDocument() {}

    public static VendorDocument createNew(final String parentEntityType, final String name, final String fileName,
            final Long size, final String type, final String location) {
        return new VendorDocument(parentEntityType, name, fileName, size, type, location);
    }

    private VendorDocument(final String parentEntityType, final String name, final String fileName, final Long size,
            final String type, final String location) {
        this.parentEntityType = StringUtils.defaultIfEmpty(parentEntityType, null);
        this.name = StringUtils.defaultIfEmpty(name, null);
        this.fileName = StringUtils.defaultIfEmpty(fileName, null);
        this.size = size;
        this.type = StringUtils.defaultIfEmpty(type, null);
        this.location = StringUtils.defaultIfEmpty(location, null);
    }

    public void update(final VendorDocumentCommand command) {
        /*if (command.isDescriptionChanged()) {
            //this.description = command.getDescription();
        }*/
        if (command.isFileNameChanged()) {
            this.fileName = command.getFileName();
        }
        if (command.isFileTypeChanged()) {
            this.type = command.getType();
        }
        if (command.isLocationChanged()) {
            this.location = command.getLocation();
        }
        if (command.isNameChanged()) {
            this.name = command.getName();
        }
        if (command.isSizeChanged()) {
            this.size = command.getSize();
        }

    }

    public String getParentEntityType() {
        return this.parentEntityType;
    }

    public void setParentEntityType(final String parentEntityType) {
        this.parentEntityType = parentEntityType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(final Long size) {
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

	
}
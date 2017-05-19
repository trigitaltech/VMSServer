/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.vendordocumentmanagement.command;

import java.util.Set;

/**
 * Immutable command for creating or updating details of a client identifier.
 */
public class VendorDocumentCommand {

    private final Long id;
    private final String parentEntityType;
    private final String name;

    private String fileName;
    private Long size;
    private String type;
    private String location;

    private final Set<String> modifiedParameters;

    public VendorDocumentCommand(final Set<String> modifiedParameters, final Long id, final String parentEntityType, 
            final String name, final String fileName, final Long size, final String type, final String location) {
    	
        this.modifiedParameters = modifiedParameters;
        this.id = id;
        this.parentEntityType = parentEntityType;
        this.name = name;
        this.fileName = fileName;
        this.size = size;
        this.type = type;
        this.location = location;
    }

	public Long getId() {
        return this.id;
    }

    public String getParentEntityType() {
        return this.parentEntityType;
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Long getSize() {
        return this.size;
    }

    public String getType() {
        return this.type;
    }

    public String getLocation() {
        return this.location;
    }

    public Set<String> getModifiedParameters() {
        return this.modifiedParameters;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setSize(final Long size) {
        this.size = size;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public boolean isNameChanged() {
        return this.modifiedParameters.contains("name");
    }

    public boolean isFileNameChanged() {
        return this.modifiedParameters.contains("fileName");
    }

    public boolean isSizeChanged() {
        return this.modifiedParameters.contains("size");
    }

    public boolean isFileTypeChanged() {
        return this.modifiedParameters.contains("type");
    }

    public boolean isLocationChanged() {
        return this.modifiedParameters.contains("location");
    }

}
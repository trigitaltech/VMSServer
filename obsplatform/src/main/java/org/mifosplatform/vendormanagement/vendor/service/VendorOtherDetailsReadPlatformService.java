/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.service;

import java.util.List;

import org.mifosplatform.vendormanagement.vendor.data.VendorOtherDetailsData;

public interface VendorOtherDetailsReadPlatformService {

	List<VendorOtherDetailsData> retrieveAllVendorOtherDetails();

	VendorOtherDetailsData retrieveSigleVendorOtherDetails(Long vendorId);

}
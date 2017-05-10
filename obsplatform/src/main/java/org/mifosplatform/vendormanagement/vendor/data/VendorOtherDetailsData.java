/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.data;

import java.util.Date;



/**
 * Immutable data object for application user data.
 */

public class VendorOtherDetailsData {
	
	private Long id;
    private String panNo;
    private String panFileName;
    private String incurCertification;
    private String certificateFileName;
    private String stNo;
    
    private String stFileName;
    private Long msmStatus;
    private String msmRegNo;
    private Date msmRegDate;
    
    private String msmFileName;
    private String vatNo;
    private String vatFileName;
    private String gstNo;
    
    private String gstFileName;
    private String cstNo;
    private String cstFileName;
    

	public VendorOtherDetailsData(final Long id,final String panNo,
			final String panFileName,final String incurCertification,final String certificateFileName,final String stNo,
			final String stFileName,final Long msmStatus,final String msmRegNo,final Date msmRegDate,final String msmFileName,
			final String vatNo,final String vatFileName,final String gstNo,final String gstFileName,
			final String cstNo,final String cstFileName) {
		
		this.id = id;
		this.panNo = panNo;
		this.panFileName = panFileName;
		this.incurCertification = incurCertification;
		this.certificateFileName = certificateFileName;
		this.stNo = stNo;
		this.stFileName = stFileName;
		this.msmStatus = msmStatus;
		this.msmRegNo = msmRegNo;
		this.msmRegDate = msmRegDate;
		this.msmFileName = msmFileName;
		this.vatNo = vatNo;
		this.vatFileName = vatFileName;
		this.gstNo = gstNo;
		this.gstFileName = gstFileName;
		this.cstNo = cstNo;
		this.cstFileName = cstFileName;
		
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPanFileName() {
		return panFileName;
	}

	public void setPanFileName(String panFileName) {
		this.panFileName = panFileName;
	}

	public String getIncurCertification() {
		return incurCertification;
	}

	public void setIncurCertification(String incurCertification) {
		this.incurCertification = incurCertification;
	}

	public String getCertificateFileName() {
		return certificateFileName;
	}

	public void setCertificateFileName(String certificateFileName) {
		this.certificateFileName = certificateFileName;
	}

	public String getStNo() {
		return stNo;
	}

	public void setStNo(String stNo) {
		this.stNo = stNo;
	}

	public String getStFileName() {
		return stFileName;
	}

	public void setStFileName(String stFileName) {
		this.stFileName = stFileName;
	}

	public Long getMsmStatus() {
		return msmStatus;
	}

	public void setMsmStatus(Long msmStatus) {
		this.msmStatus = msmStatus;
	}

	public String getMsmRegNo() {
		return msmRegNo;
	}

	public void setMsmRegNo(String msmRegNo) {
		this.msmRegNo = msmRegNo;
	}

	public Date getMsmRegDate() {
		return msmRegDate;
	}

	public void setMsmRegDate(Date msmRegDate) {
		this.msmRegDate = msmRegDate;
	}

	public String getMsmFileName() {
		return msmFileName;
	}

	public void setMsmFileName(String msmFileName) {
		this.msmFileName = msmFileName;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public String getVatFileName() {
		return vatFileName;
	}

	public void setVatFileName(String vatFileName) {
		this.vatFileName = vatFileName;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getGstFileName() {
		return gstFileName;
	}

	public void setGstFileName(String gstFileName) {
		this.gstFileName = gstFileName;
	}

	public String getCstNo() {
		return cstNo;
	}

	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getCstFileName() {
		return cstFileName;
	}

	public void setCstFileName(String cstFileName) {
		this.cstFileName = cstFileName;
	}


	
}


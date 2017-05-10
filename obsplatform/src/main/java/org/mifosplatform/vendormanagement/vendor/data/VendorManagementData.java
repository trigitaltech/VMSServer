/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.data;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.organisation.mcodevalues.data.MCodeData;
import org.mifosplatform.organisation.monetary.data.CurrencyData;


/**
 * Immutable data object for application user data.
 */

public class VendorManagementData {
	
	private Long id;
    private String vendorName;
    private Long entityType;
    private String otherEntity;
    private String contactName;
    private String address1;
    
    private String address2;
    private String address3;
    private String country;
    private String state;
    
    private String city;
    private String postalCode;
    private Long residentialStatus;
    private String otherResidential;
    
    private String landLineNo;
    private String mobileNo;
    private String fax;
    private String emailId;
    private Long detailId;
    private String bankName;
	private String accountNo;
    private String branch;
    private String ifscCode;
    private String swiftCode;
    private String ibanCode;
    private String accountName;
    private String chequeNo;
    private String entityName;
	private String resiStatusName;
    
	private List<String> countryData;
    private List<String> statesData;
	private List<String> citiesData;
	private Collection<MCodeData> entityTypeData;
	private Collection<MCodeData> residentialStatusData;
    

	public VendorManagementData(final Long id,final String vendorName,
			final Long entityType,final String otherEntity,final String contactName,final String address1,
			final String address2,final String address3,final String country,final String state,final String city,
			final String postalCode,final Long residentialStatus,final String otherResidential,final String landLineNo,
			final String mobileNo,final String fax,final String emailId,final Long detailId,final String bankName,
			final String accountNo,final String branch,final String ifscCode,final String swiftCode,final String ibanCode,
			final String accountName,final String chequeNo,final String entityName,final String resiStatusName) {
		
		this.id = id;
		this.vendorName = vendorName;
		this.entityType = entityType;
		this.otherEntity = otherEntity;
		this.contactName = contactName;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.country = country;
		this.state = state;
		this.city = city;
		this.postalCode = postalCode;
		this.residentialStatus = residentialStatus;
		this.otherResidential = otherResidential;
		this.landLineNo = landLineNo;
		this.mobileNo = mobileNo;
		this.fax = fax;
		this.emailId = emailId;
		this.detailId = detailId;
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.branch = branch;
		this.ifscCode = ifscCode;
		this.swiftCode = swiftCode;
		this.ibanCode = ibanCode;
		this.accountName = accountName;
		this.chequeNo = chequeNo;
		this.entityName = entityName;
		this.resiStatusName = resiStatusName;
		
	}

	public VendorManagementData(final List<String> countryData,
			final List<String> statesData, final List<String> citiesData,
			final Collection<MCodeData> entityTypeData,
			final Collection<MCodeData> residentialStatusData) {
		
		this.countryData = countryData;
		this.statesData = statesData;
		this.citiesData = citiesData;
		this.entityTypeData = entityTypeData;
		this.residentialStatusData = residentialStatusData;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Long getEntityType() {
		return entityType;
	}

	public void setEntityType(Long entityType) {
		this.entityType = entityType;
	}

	public String getOtherEntity() {
		return otherEntity;
	}

	public void setOtherEntity(String otherEntity) {
		this.otherEntity = otherEntity;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Long getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(Long residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	public String getOtherResidential() {
		return otherResidential;
	}

	public void setOtherResidential(String otherResidential) {
		this.otherResidential = otherResidential;
	}

	public String getLandLineNo() {
		return landLineNo;
	}

	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getIbanCode() {
		return ibanCode;
	}

	public void setIbanCode(String ibanCode) {
		this.ibanCode = ibanCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	
	public String getResiStatusName() {
		return resiStatusName;
	}

	public void setResiStatusName(String resiStatusName) {
		this.resiStatusName = resiStatusName;
	}
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setCountryData(List<String> countryData) {
		this.countryData = countryData;
		
	}
	
	public List<String> getCountryData() {
		return countryData;
	}
	
	public List<String> getStatesData() {
		return statesData;
	}

	public void setStatesData(List<String> statesData) {
		this.statesData = statesData;
	}

	public List<String> getCitiesData() {
		return citiesData;
	}

	public void setCitiesData(List<String> citiesData) {
		this.citiesData = citiesData;
	}
	
	public void setCurrencyOptions(Collection<CurrencyData> currencyOptions) {
		// TODO Auto-generated method stub
		
	}
	
	public Collection<MCodeData> getEntityTypeData() {
		return entityTypeData;
	}

	public void setEntityTypeData(Collection<MCodeData> entityTypeData) {
		this.entityTypeData = entityTypeData;
	}
	
	public Collection<MCodeData> getResidentialStatusData() {
		return residentialStatusData;
	}

	public void setResidentialStatusData(Collection<MCodeData> residentialStatusData) {
		this.residentialStatusData = residentialStatusData;
	}
	
}


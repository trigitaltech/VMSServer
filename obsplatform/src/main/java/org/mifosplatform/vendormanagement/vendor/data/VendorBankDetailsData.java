/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.data;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.organisation.address.data.CountryDetails;
import org.mifosplatform.organisation.monetary.data.CurrencyData;


/**
 * Immutable data object for application user data.
 */

public class VendorBankDetailsData {
	
	
	private Long id;
    private String bankName;
    private String accountNo;
    private String branch;
    private String ifscCode;
    private String swiftCode;
    
    private String ibanCode;
    private String accountName;
    private String chequeNo;
    
    
    
    /*public VendorBankDetailsData(List<CountryDetails> countryData,
			Collection<CurrencyData> currencyOptions) {
		
    	this.countryData = countryData;
    	this.currencyOptions = currencyOptions;
	}*/


	public VendorBankDetailsData(final Long id,final String bankName,
			final String accountNo,final String branch,final String ifscCode,
			final String swiftCode,final String ibanCode,
			final String accountName,final String chequeNo) {
		
		this.id = id;
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.branch = branch;
		this.ifscCode = ifscCode;
		this.swiftCode = swiftCode;
		this.ibanCode = ibanCode;
		this.accountName = accountName;
		this.chequeNo = chequeNo;
		
	}



	public VendorBankDetailsData(List<CountryDetails> countryData,
			Collection<CurrencyData> currencyOptions) {
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
}


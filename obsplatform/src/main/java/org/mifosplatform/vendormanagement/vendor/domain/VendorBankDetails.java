package org.mifosplatform.vendormanagement.vendor.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "b_vendor_bank_details")
public class VendorBankDetails extends  AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id", insertable = true, updatable = true, nullable = true, unique = true)
	private VendorManagement vendorManagement;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "ifsc_code")
	private String ifscCode;
	
	@Column(name = "swift_code")
	private String swiftCode;
	
	@Column(name = "iban_code")
	private String ibanCode;
	
	@Column(name = "account_name")
	private String accountName;

	@Column(name = "cheque_no")
	private String chequeNo;
	
	@Column(name = "is_deleted")
	private String isDeleted="N";
	

	public  VendorBankDetails() {
		
	}
	
	public VendorBankDetails(final String bankName, final String accountNo,
			final String branch,final String ifscCode,final String swiftCode,
			final String ibanCode,final String accountName,final String chequeNo) {
		
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.branch = branch;
		this.ifscCode = ifscCode;
		this.swiftCode = swiftCode;
		this.ibanCode = ibanCode;
		this.accountName = accountName;
		this.chequeNo = chequeNo;
		
	}

	
	public static VendorBankDetails fromJson(final JsonCommand command) {
		
		 final String bankName = command.stringValueOfParameterNamed("bankName");
		 final String accountNo = command.stringValueOfParameterNamed("accountNo");
		 final String branch = command.stringValueOfParameterNamed("branch");
		 final String ifscCode = command.stringValueOfParameterNamed("ifscCode");
		 final String swiftCode = command.stringValueOfParameterNamed("swiftCode");
		 final String ibanCode = command.stringValueOfParameterNamed("ibanCode");
		 final String accountName = command.stringValueOfParameterNamed("accountName");
		 final String chequeNo = command.stringValueOfParameterNamed("chequeNo");

		 return new VendorBankDetails(bankName, accountNo, branch, ifscCode,
				 swiftCode, ibanCode, accountName, chequeNo);
	}
	
	public Map<String, Object> update(JsonCommand command){
	
		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		final String bankNameParamName = "bankName";
		if(command.isChangeInStringParameterNamed(bankNameParamName, this.bankName)){
			final String newValue = command.stringValueOfParameterNamed(bankNameParamName);
			actualChanges.put(bankNameParamName, newValue);
			this.bankName = StringUtils.defaultIfEmpty(newValue,null);
		}
		final String accountNoParamName = "accountNo";
		if(command.isChangeInStringParameterNamed(accountNoParamName, this.accountNo)){
			final String newValue = command.stringValueOfParameterNamed(accountNoParamName);
			actualChanges.put(accountNoParamName, newValue);
			this.accountNo = newValue;
		}
		
		final String branchParamName = "branch";
		if(command.isChangeInStringParameterNamed(branchParamName,this.branch)){
			final String newValue = command.stringValueOfParameterNamed(branchParamName);
			actualChanges.put(branchParamName, newValue);
			this.branch = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String ifscCodeParamName = "ifscCode";
		if(command.isChangeInStringParameterNamed(ifscCodeParamName,this.ifscCode)){
			final String newValue = command.stringValueOfParameterNamed(ifscCodeParamName);
			actualChanges.put(ifscCodeParamName, newValue);
			this.ifscCode = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String swiftCodeParamName = "swiftCode";
		if(command.isChangeInStringParameterNamed(swiftCodeParamName, this.swiftCode)){
			final String newValue = command.stringValueOfParameterNamed(swiftCodeParamName);
			actualChanges.put(swiftCodeParamName, newValue);
			this.swiftCode = StringUtils.defaultIfEmpty(newValue,null); 
		}
		
		final String ibanCodeParamName = "ibanCode";
		if(command.isChangeInStringParameterNamed(ibanCodeParamName, this.ibanCode)){
			final String newValue = command.stringValueOfParameterNamed(ibanCodeParamName);
			actualChanges.put(ibanCodeParamName, newValue);
			this.ibanCode = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String accountNameParamName = "accountName";
		if(command.isChangeInStringParameterNamed(accountNameParamName, this.accountName)){
			final String newValue = command.stringValueOfParameterNamed(accountNameParamName);
			actualChanges.put(accountNameParamName, newValue);
			this.accountName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String chequeNoParamName = "chequeNo";
		if(command.isChangeInStringParameterNamed(chequeNoParamName, this.chequeNo)){
			final String newValue = command.stringValueOfParameterNamed(chequeNoParamName);
			actualChanges.put(chequeNoParamName, newValue);
			this.chequeNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
	
		return actualChanges;
	
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

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	} 
	
	public void update (VendorManagement vendorManagement){
		this.vendorManagement = vendorManagement;
	}

	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete() {
		this.isDeleted = "Y";
		
	}


	
}
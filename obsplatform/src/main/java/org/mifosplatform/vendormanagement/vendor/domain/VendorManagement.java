package org.mifosplatform.vendormanagement.vendor.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "b_vendor_management")
public class VendorManagement extends  AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "vendor_name")
	private String vendorName;
	
	@Column(name = "entity_type")
	private Long entityType;
	
	@Column(name = "other_entity")
	private String otherEntity;
	
	@Column(name = "contact_name")
	private String contactName;
	
	@Column(name = "address_1")
	private String address1;
	
	@Column(name = "address_2")
	private String address2;
	
	@Column(name = "address_3")
	private String address3;

	@Column(name = "country")
	private String country;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "residential_status")
	private Long residentialStatus;
	
	@Column(name = "other_residential")
	private String otherResidential;
	
	@Column(name = "landline_no")
	private String landLineNo;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "is_deleted")
	private String isDeleted="N";
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorManagement", orphanRemoval = true)
	private List<VendorBankDetails> vendorBankDetails = new ArrayList<VendorBankDetails>();

	public  VendorManagement() {
		
	}
	
	public VendorManagement(final String vendorName, final Long entityType,
			final String otherEntity,final String contactName,final String address1,
			final String address2,final String address3,final String country,final String state,
			final String city,final String postalCode,final Long residentialStatus,
			final String otherResidential,final String landLineNo,final String mobileNo,final String fax,
			final String emailId) {
		
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
		
	}

	
	public static VendorManagement fromJson(final JsonCommand command) {
		
		 final String vendorName = command.stringValueOfParameterNamed("vendorName");
		 final Long entityType = command.longValueOfParameterNamed("entityType");
		 final String otherEntity = command.stringValueOfParameterNamed("otherEntity");
		 final String contactName = command.stringValueOfParameterNamed("contactName");
		 final String address1 = command.stringValueOfParameterNamed("address1");
		 final String address2 = command.stringValueOfParameterNamed("address2");
		 final String address3 = command.stringValueOfParameterNamed("address3");
		 final String country = command.stringValueOfParameterNamed("country");
		 final String state = command.stringValueOfParameterNamed("state");
		 final String city = command.stringValueOfParameterNamed("city");
		 final String postalCode = command.stringValueOfParameterNamed("postalCode");
		 final Long residentialStatus = command.longValueOfParameterNamed("residentialStatus");
		 final String otherResidential = command.stringValueOfParameterNamed("otherResidential");
		 final String landLineNo = command.stringValueOfParameterNamed("landLineNo");
		 final String mobileNo = command.stringValueOfParameterNamed("mobileNo");
		 final String fax = command.stringValueOfParameterNamed("fax");
		 final String emailId = command.stringValueOfParameterNamed("emailId");

		 return new VendorManagement(vendorName, entityType, otherEntity, contactName,
				 address1, address2, address3, country, state,city,postalCode,residentialStatus,
				 otherResidential,landLineNo,mobileNo,fax,emailId);
	}
	
	public Map<String, Object> update(JsonCommand command){
	
		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		final String vendorNameParamName = "vendorName";
		if(command.isChangeInStringParameterNamed(vendorNameParamName, this.vendorName)){
			final String newValue = command.stringValueOfParameterNamed(vendorNameParamName);
			actualChanges.put(vendorNameParamName, newValue);
			this.vendorName = StringUtils.defaultIfEmpty(newValue,null);
		}
		final String entityTypeParamName = "entityType";
		if(command.isChangeInLongParameterNamed(entityTypeParamName, this.entityType)){
			final Long newValue = command.longValueOfParameterNamed(entityTypeParamName);
			actualChanges.put(entityTypeParamName, newValue);
			this.entityType = newValue;
		}
		
		final String otherEntityParamName = "otherEntity";
		if(command.isChangeInStringParameterNamed(otherEntityParamName,this.otherEntity)){
			final String newValue = command.stringValueOfParameterNamed(otherEntityParamName);
			actualChanges.put(otherEntityParamName, newValue);
			this.otherEntity = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String contactNameParamName = "contactName";
		if(command.isChangeInStringParameterNamed(contactNameParamName,this.contactName)){
			final String newValue = command.stringValueOfParameterNamed(contactNameParamName);
			actualChanges.put(contactNameParamName, newValue);
			this.contactName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String address1ParamName = "address1";
		if(command.isChangeInStringParameterNamed(address1ParamName, this.address1)){
			final String newValue = command.stringValueOfParameterNamed(address1ParamName);
			actualChanges.put(address1ParamName, newValue);
			this.address1 = StringUtils.defaultIfEmpty(newValue,null); 
		}
		
		final String address2ParamName = "address2";
		if(command.isChangeInStringParameterNamed(address2ParamName, this.address2)){
			final String newValue = command.stringValueOfParameterNamed(address2ParamName);
			actualChanges.put(address2ParamName, newValue);
			this.address1 = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String address3ParamName = "address3";
		if(command.isChangeInStringParameterNamed(address3ParamName, this.address3)){
			final String newValue = command.stringValueOfParameterNamed(address3ParamName);
			actualChanges.put(address3ParamName, newValue);
			this.address3 = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String countryParamName = "country";
		if(command.isChangeInStringParameterNamed(countryParamName, this.country)){
			final String newValue = command.stringValueOfParameterNamed(countryParamName);
			actualChanges.put(countryParamName, newValue);
			this.country = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String stateParamName = "state";
		if(command.isChangeInStringParameterNamed(stateParamName, this.state)){
			final String newValue = command.stringValueOfParameterNamed(stateParamName);
			actualChanges.put(stateParamName, newValue);
			this.state = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String cityParamName = "city";
		if(command.isChangeInStringParameterNamed(cityParamName, this.city)){
			final String newValue = command.stringValueOfParameterNamed(cityParamName);
			actualChanges.put(cityParamName, newValue);
			this.city = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String postalCodeParamName = "postalCode";
		if(command.isChangeInStringParameterNamed(postalCodeParamName, this.postalCode)){
			final String newValue = command.stringValueOfParameterNamed(postalCodeParamName);
			actualChanges.put(postalCodeParamName, newValue);
			this.postalCode = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String residentialStatusParamName = "residentialStatus";
		if(command.isChangeInLongParameterNamed(residentialStatusParamName, this.residentialStatus)){
			final Long newValue = command.longValueOfParameterNamed(residentialStatusParamName);
			actualChanges.put(residentialStatusParamName, newValue);
			this.residentialStatus = newValue;
		}
		
		final String otherResidentialParamName = "otherResidential";
		if(command.isChangeInStringParameterNamed(otherResidentialParamName, this.otherResidential)){
			final String newValue = command.stringValueOfParameterNamed(otherResidentialParamName);
			actualChanges.put(otherResidentialParamName, newValue);
			this.otherResidential = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String landLineNoParamName = "landLineNo";
		if(command.isChangeInStringParameterNamed(landLineNoParamName, this.landLineNo)){
			final String newValue = command.stringValueOfParameterNamed(landLineNoParamName);
			actualChanges.put(landLineNoParamName, newValue);
			this.landLineNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String mobileNoParamName = "mobileNo";
		if(command.isChangeInStringParameterNamed(mobileNoParamName, this.mobileNo)){
			final String newValue = command.stringValueOfParameterNamed(mobileNoParamName);
			actualChanges.put(mobileNoParamName, newValue);
			this.mobileNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String faxParamName = "fax";
		if(command.isChangeInStringParameterNamed(faxParamName, this.fax)){
			final String newValue = command.stringValueOfParameterNamed(faxParamName);
			actualChanges.put(faxParamName, newValue);
			this.fax = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String emailIdParamName = "emailId";
		if(command.isChangeInStringParameterNamed(emailIdParamName, this.emailId)){
			final String newValue = command.stringValueOfParameterNamed(emailIdParamName);
			actualChanges.put(emailIdParamName, newValue);
			this.emailId = StringUtils.defaultIfEmpty(newValue,null);
		}
	
		return actualChanges;
	
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void delete() {
		this.isDeleted = "Y";
	}
	
	 public boolean isDeleted() { 
		 return isDeleted.equalsIgnoreCase("Y")?true:false;
	 }

	 
	public void addVendorBankDetails(VendorBankDetails vendorBankDetails) {
		
		vendorBankDetails.update(this);
		this.vendorBankDetails.add(vendorBankDetails);
	}

	
}
package org.mifosplatform.organisation.office.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "b_office_address", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_number" }, name = "phonenumber_org"),
@UniqueConstraint(columnNames = { "email_id" }, name = "emailid_org")})
public class OfficeAddress extends AbstractPersistable<Long> {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Column(name = "address_name")
	private String addressName;
	
	
	@Column(name = "line_1")
	private String line1;

	@Column(name = "line_2")
	private String line2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "office_number")
	private String officeNumber;

	@Column(name = "email_id")
	private String email;

	@Column(name = "zip")
	private String zip;

	@Column(name = "company_logo")
	private String companyLogo;

	@Column(name = "VRN")
	private String vrn;

	@Column(name = "TIN")
	private String tin;

	@OneToOne
	@JoinColumn(name = "office_id", insertable = true, updatable = true, nullable = true, unique = true)
	private Office office;

	public OfficeAddress() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static OfficeAddress fromJson(final JsonCommand command, Office office) {
		
		final String officeNumber = command.stringValueOfParameterNamed("officeNumber");
		final String phoneNumber = command.stringValueOfParameterNamed("phoneNumber");
		final String addressName = command.stringValueOfParameterNamed("addressName");
		final String line1 =command.stringValueOfParameterNamed("line1");
		final String line2 =command.stringValueOfParameterNamed("line2");
		final String zip =command.stringValueOfParameterNamed("zip");
		final String email = command.stringValueOfParameterNamed("email");
		final String city = command.stringValueOfParameterNamed("city");
		final String state = command.stringValueOfParameterNamed("state");
		final String country = command.stringValueOfParameterNamed("country");
		String companyLogo=null;
		if(command.parameterExists("companyLogo")){
			companyLogo  = command.stringValueOfParameterNamed("companyLogo");
		}
		
		return new OfficeAddress(officeNumber,phoneNumber,addressName,line1,line2,zip,email,city,state,country,companyLogo,office);
	}
	
	
	public Map<String, Object> update(final JsonCommand command) {
		final Map<String, Object> actualChanges = new ConcurrentHashMap<String, Object>(1);
		final String organizationParamName = "officeNumber";
		if (command.isChangeInStringParameterNamed(organizationParamName,this.officeNumber)) {
			final String newValue = command.stringValueOfParameterNamed(organizationParamName);
			actualChanges.put(organizationParamName, newValue);
			this.officeNumber = StringUtils.defaultIfEmpty(newValue, null);
		}

		final String phoneParamName = "phoneNumber";
		if (command.isChangeInStringParameterNamed(phoneParamName,this.phoneNumber)) {
			final String newValue = command.stringValueOfParameterNamed(phoneParamName);
			actualChanges.put(phoneParamName, newValue);
			this.phoneNumber = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String addressParamName = "addressName";
		if(command.isChangeInStringParameterNamed(addressParamName, this.addressName)){
		   final String newValue=command.stringValueOfParameterNamed(addressParamName);
		   actualChanges.put(addressParamName, newValue);
		   this.addressName=StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String line1ParamName= "line1";
		if(command.isChangeInStringParameterNamed(line1ParamName, this.line1)){
		   final String newValue=command.stringValueOfParameterNamed(line1ParamName);
		   actualChanges.put(line1ParamName, newValue);
		   this.line1=StringUtils.defaultIfEmpty(newValue,null);
		}

		final String line2ParamName = "line2";
		if(command.isChangeInStringParameterNamed(addressParamName, this.line2)){
		   final String newValue=command.stringValueOfParameterNamed(line2ParamName);
		   actualChanges.put(line2ParamName, newValue);
		   this.line2=StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String zipParamName = "zip";
		if(command.isChangeInStringParameterNamed(zipParamName, this.zip)){
		   final String newValue=command.stringValueOfParameterNamed(zipParamName);
		   actualChanges.put(zipParamName, newValue);
		   this.zip=StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String emailParamName = "email";
		if (command.isChangeInStringParameterNamed(emailParamName,this.email)) {
			final String newValue = command.stringValueOfParameterNamed(emailParamName);
			actualChanges.put(emailParamName, newValue);
			this.email = StringUtils.defaultIfEmpty(newValue, null);
		}

		final String cityParamName = "city";
		if (command.isChangeInStringParameterNamed(cityParamName,this.city)) {
			final String newValue = command.stringValueOfParameterNamed(cityParamName);
			actualChanges.put(cityParamName, newValue);
			this.city = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		final String stateParamName = "state";
		if (command.isChangeInStringParameterNamed(stateParamName,this.state)) {
			final String newValue = command.stringValueOfParameterNamed(stateParamName);
			actualChanges.put(stateParamName, newValue);
			this.state = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		final String countryParamName = "country";
		if (command.isChangeInStringParameterNamed(countryParamName,this.country)) {
			final String newValue = command.stringValueOfParameterNamed(countryParamName);
			actualChanges.put(countryParamName, newValue);
			this.country = StringUtils.defaultIfEmpty(newValue, null);
		}

		if(command.parameterExists("companyLogo")){
			final String companyLogoParamName = "companyLogo";
			if (command.isChangeInStringParameterNamed(companyLogoParamName,this.companyLogo)) {
				final String newValue = command.stringValueOfParameterNamed(companyLogoParamName);
				actualChanges.put(companyLogoParamName, newValue);
				this.companyLogo = StringUtils.defaultIfEmpty(newValue, null);
			}
		}

		return actualChanges;

	}

	public OfficeAddress(final String officeNumber,final String phoneNumber,final String addressName,final String line1,final String line2,final String zip ,final String email,final String city, 
			final String state, final String country, final String companyLogo,final Office office) {

		this.officeNumber = officeNumber;
		this.phoneNumber = phoneNumber;
		this.addressName=addressName;
		this.line1=line1;
		this.line2=line2;
		this.zip=zip;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
		this.companyLogo = (companyLogo!=null)? companyLogo : null;
		this.office = office;
		
	}
	

	public String getAddressName() {
		return addressName;
	}
	
	public String getLine1(){
		return line1;
	}
	
	public String getLine2(){
		return line2;
	}
	
	public String getZip(){
		return zip;
	}
	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getOfficeNumber() {
		return officeNumber;
	}

	public String getEmail() {
		return email;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(final String imageLocation) {
		
		this.companyLogo = imageLocation;
	}

}
	


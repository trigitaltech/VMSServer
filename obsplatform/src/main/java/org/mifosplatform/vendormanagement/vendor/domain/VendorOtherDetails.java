package org.mifosplatform.vendormanagement.vendor.domain;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "b_vendor_other_details")
public class VendorOtherDetails extends  AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "vendor_id")
	private Long vendorId;
	
	@Column(name = "pan_no")
	private String panNo;
	
	@Column(name = "pan_file_name")
	private String panFileName;
	
	@Column(name = "incur_certification")
	private String incurCertification;
	
	@Column(name = "certificate_file_name")
	private String certificateFileName;
	
	@Column(name = "st_no")
	private String stNo;
	
	@Column(name = "st_file_name")
	private String stFileName;
	
	@Column(name = "msm_status")
	private String msmStatus;

	@Column(name = "msm_reg_no")
	private String msmRegNo;
	
	@Column(name = "msm_reg_date")
	private Date msmRegDate;
	
	@Column(name = "msm_file_name")
	private String msmFileName;

	@Column(name = "vat_no")
	private String vatNo;
	
	@Column(name = "vat_file_name")
	private String vatFileName;
	
	@Column(name = "gst_no")
	private String gstNo;
	
	@Column(name = "gst_file_name")
	private String gstFileName;
	
	@Column(name = "cst_no")
	private String cstNo;
	
	@Column(name = "cst_file_name")
	private String cstFileName;
	

	public  VendorOtherDetails() {
		
	}
	
	public VendorOtherDetails(final Long vendorId,final String panNo, final String panFileName,
			final String incurCertification,final String certificateFileName,final String stNo,
			final String stFileName,final String msmStatus,final String msmRegNo,final Date msmRegDate,
			final String msmFileName,final String vatNo,final String vatFileName,final String gstNo,
			final String gstFileName,final String cstNo,final String cstFileName) {
			
		this.vendorId = vendorId;
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

	
	public static VendorOtherDetails fromJson(final JsonCommand command) {
		
		 final Long vendorId = command.longValueOfParameterNamed("vendorId");
		 final String panNo = command.stringValueOfParameterNamed("panNo");
		 final String panFileName = command.stringValueOfParameterNamed("panFileName");
		 final String incurCertification = command.stringValueOfParameterNamed("incurCertification");
		 final String certificateFileName = command.stringValueOfParameterNamed("certificateFileName");
		 final String stNo = command.stringValueOfParameterNamed("stNo");
		 final String stFileName = command.stringValueOfParameterNamed("stFileName");
		 final String msmStatus = command.stringValueOfParameterNamed("msmStatus");
		 final String msmRegNo = command.stringValueOfParameterNamed("msmRegNo");
		 final Date msmRegDate = command.DateValueOfParameterNamed("msmRegDate");
		 final String msmFileName = command.stringValueOfParameterNamed("msmFileName");
		 final String vatNo = command.stringValueOfParameterNamed("vatNo");
		 final String vatFileName = command.stringValueOfParameterNamed("vatFileName");
		 final String gstNo = command.stringValueOfParameterNamed("gstNo");
		 final String gstFileName = command.stringValueOfParameterNamed("gstFileName");
		 final String cstNo = command.stringValueOfParameterNamed("cstNo");
		 final String cstFileName = command.stringValueOfParameterNamed("cstFileName");

		 return new VendorOtherDetails(vendorId,panNo, panFileName, incurCertification, certificateFileName,
				 stNo, stFileName, msmStatus, msmRegNo,msmRegDate,msmFileName,vatNo,vatFileName,gstNo,
				 gstFileName,cstNo,cstFileName);
	}
	
	public Map<String, Object> update(JsonCommand command){
	
		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		final String panNoParamName = "panNo";
		if(command.isChangeInStringParameterNamed(panNoParamName, this.panNo)){
			final String newValue = command.stringValueOfParameterNamed(panNoParamName);
			actualChanges.put(panNoParamName, newValue);
			this.panNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		final String panFileNameParamName = "panFileName";
		if(command.isChangeInStringParameterNamed(panFileNameParamName, this.panFileName)){
			final String newValue = command.stringValueOfParameterNamed(panFileNameParamName);
			actualChanges.put(panFileNameParamName, newValue);
			this.panFileName = newValue;
		}
		
		final String incurCertificationParamName = "incurCertification";
		if(command.isChangeInStringParameterNamed(incurCertificationParamName,this.incurCertification)){
			final String newValue = command.stringValueOfParameterNamed(incurCertificationParamName);
			actualChanges.put(incurCertificationParamName, newValue);
			this.incurCertification = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String certificateFileNameParamName = "certificateFileName";
		if(command.isChangeInStringParameterNamed(certificateFileNameParamName,this.certificateFileName)){
			final String newValue = command.stringValueOfParameterNamed(certificateFileNameParamName);
			actualChanges.put(certificateFileNameParamName, newValue);
			this.certificateFileName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String stNoParamName = "stNo";
		if(command.isChangeInStringParameterNamed(stNoParamName, this.stNo)){
			final String newValue = command.stringValueOfParameterNamed(stNoParamName);
			actualChanges.put(stNoParamName, newValue);
			this.stNo = StringUtils.defaultIfEmpty(newValue,null); 
		}
		
		final String stFileNameParamName = "stFileName";
		if(command.isChangeInStringParameterNamed(stFileNameParamName, this.stFileName)){
			final String newValue = command.stringValueOfParameterNamed(stFileNameParamName);
			actualChanges.put(stFileNameParamName, newValue);
			this.stFileName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String msmStatusParamName = "msmStatus";
		if(command.isChangeInStringParameterNamed(msmStatusParamName, this.msmStatus)){
			final String newValue = command.stringValueOfParameterNamed(msmStatusParamName);
			actualChanges.put(msmStatusParamName, newValue);
			this.msmStatus = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String msmRegNoParamName = "msmRegNo";
		if(command.isChangeInStringParameterNamed(msmRegNoParamName, this.msmRegNo)){
			final String newValue = command.stringValueOfParameterNamed(msmRegNoParamName);
			actualChanges.put(msmRegNoParamName, newValue);
			this.msmRegNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String msmRegDateParamName = "msmRegDate";
		if(command.isChangeInDateParameterNamed(msmRegDateParamName, this.msmRegDate)){
			final Date newValue = command.DateValueOfParameterNamed(msmRegDateParamName);
			actualChanges.put(msmRegDateParamName, newValue);
			this.msmRegDate = newValue;
		}
		
		final String msmFileNameParamName = "msmFileName";
		if(command.isChangeInStringParameterNamed(msmFileNameParamName, this.msmFileName)){
			final String newValue = command.stringValueOfParameterNamed(msmFileNameParamName);
			actualChanges.put(msmFileNameParamName, newValue);
			this.msmFileName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String vatNoParamName = "vatNo";
		if(command.isChangeInStringParameterNamed(vatNoParamName, this.vatNo)){
			final String newValue = command.stringValueOfParameterNamed(vatNoParamName);
			actualChanges.put(vatNoParamName, newValue);
			this.vatNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String vatFileNameParamName = "vatFileName";
		if(command.isChangeInStringParameterNamed(vatFileNameParamName, this.vatFileName)){
			final String newValue = command.stringValueOfParameterNamed(vatFileNameParamName);
			actualChanges.put(vatFileNameParamName, newValue);
			this.vatFileName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String gstNoParamName = "gstNo";
		if(command.isChangeInStringParameterNamed(gstNoParamName, this.gstNo)){
			final String newValue = command.stringValueOfParameterNamed(gstNoParamName);
			actualChanges.put(gstNoParamName, newValue);
			this.gstNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String gstFileNameParamName = "gstFileName";
		if(command.isChangeInStringParameterNamed(gstFileNameParamName, this.gstFileName)){
			final String newValue = command.stringValueOfParameterNamed(gstFileNameParamName);
			actualChanges.put(gstFileNameParamName, newValue);
			this.gstFileName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String cstNoParamName = "cstNo";
		if(command.isChangeInStringParameterNamed(cstNoParamName, this.cstNo)){
			final String newValue = command.stringValueOfParameterNamed(cstNoParamName);
			actualChanges.put(cstNoParamName, newValue);
			this.cstNo = StringUtils.defaultIfEmpty(newValue,null);
		}
		
		final String cstFileNameParamName = "cstFileName";
		if(command.isChangeInStringParameterNamed(cstFileNameParamName, this.cstFileName)){
			final String newValue = command.stringValueOfParameterNamed(cstFileNameParamName);
			actualChanges.put(cstFileNameParamName, newValue);
			this.cstFileName = StringUtils.defaultIfEmpty(newValue,null);
		}
		
	
		return actualChanges;
	
	}
	
	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	
}
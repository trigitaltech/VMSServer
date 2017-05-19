package org.mifosplatform.vendormanagement.vendor.service;

import java.util.Map;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.vendoragreement.exception.VendorNotFoundException;
import org.mifosplatform.vendormanagement.vendor.domain.VendorOtherDetails;
import org.mifosplatform.vendormanagement.vendor.domain.VendorOtherDetailsRepository;
import org.mifosplatform.vendormanagement.vendor.serialization.VendorOtherDetailsCommandFromApiJsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	
@Service
public class VendorOtherDetailsWritePlatformServiceImpl implements VendorOtherDetailsWritePlatformService{
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(VendorOtherDetailsWritePlatformServiceImpl.class);	
	
	private PlatformSecurityContext context;
	private VendorOtherDetailsRepository vendorOtherDetailsRepository; 
	private VendorOtherDetailsCommandFromApiJsonDeserializer fromApiJsonDeserializer;
	 
	@Autowired
	public VendorOtherDetailsWritePlatformServiceImpl(final PlatformSecurityContext context, 
			final VendorOtherDetailsRepository vendorOtherDetailsRepository, 
			final VendorOtherDetailsCommandFromApiJsonDeserializer fromApiJsonDeserializer) {
		
		this.context = context;
		this.vendorOtherDetailsRepository = vendorOtherDetailsRepository;
		this.fromApiJsonDeserializer = fromApiJsonDeserializer;
	}
	
	@Transactional
	@Override
	public CommandProcessingResult createVendorOtherDetails(JsonCommand command) {
		
		try{
			
			this.context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			final VendorOtherDetails vendor = VendorOtherDetails.fromJson(command);
			
			this.vendorOtherDetailsRepository.save(vendor);
			
			//final String filesUploadArray[] = command.arrayValueOfParameterNamed("fileArrayData");
			
			return new CommandProcessingResult(vendor.getId());
		} catch (DataIntegrityViolationException dve) {
			 handleCodeDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}
	}
	

	private void handleCodeDataIntegrityIssues(JsonCommand command,
			DataIntegrityViolationException dve) {

		final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("vendor_name_UNIQUE")) {
			final String vendorName = command.stringValueOfParameterNamed("vendorName");
			throw new PlatformDataIntegrityException("error.msg.vendor.name.duplicate", "A name with this '" + vendorName + "' already exists");
		} /*else if (realCause.getMessage().contains("uvendor_mobileno_key")) {
			final String vendormobileNo = command.stringValueOfParameterNamed("vendormobileNo");
			throw new PlatformDataIntegrityException("error.msg.vendor.mobileno.duplicate", "A code with name '" + vendormobileNo + "' already exists");
		} else if (realCause.getMessage().contains("uvendor_landlineno_key")) {
			final String vendorTelephoneNo = command.stringValueOfParameterNamed("vendorLandlineNo");
			throw new PlatformDataIntegrityException("error.msg.vendor.landlineno.duplicate", "A code with name '" + vendorTelephoneNo + "' already exists");
		} else if (realCause.getMessage().contains("uvendor_emailid_key")) {
			final String vendorEmailId = command.stringValueOfParameterNamed("vendorEmailId");
			throw new PlatformDataIntegrityException("error.msg.vendor.emailid.duplicate", "A code with name '" + vendorEmailId + "' already exists");
		}*/

		LOGGER.error(dve.getMessage(), dve);
		throw new PlatformDataIntegrityException("error.msg.cund.unknown.data.integrity.issue",
				"Unknown data integrity issue with resource: " + realCause.getMessage());

	}

	@Transactional
	@Override
	public CommandProcessingResult updateVendorOtherDetails(Long vendorId, JsonCommand command) {
		try{
		this.context.authenticatedUser();
		this.fromApiJsonDeserializer.validateForCreate(command.json());
		VendorOtherDetails vendor=retrieveCodeBy(vendorId);
		
		final Map<String, Object> changes = vendor.update(command);
		if(!changes.isEmpty()){
			this.vendorOtherDetailsRepository.saveAndFlush(vendor);
		}
		return new CommandProcessingResultBuilder() //
	       .withCommandId(command.commandId()) //
	       .withEntityId(vendorId) //
	       .with(changes) //
	       .build();
		}catch (DataIntegrityViolationException dve) {
			 handleCodeDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}
	}
	
	private VendorOtherDetails retrieveCodeBy(final Long vendorId) {
        final VendorOtherDetails vendor = this.vendorOtherDetailsRepository.findOne(vendorId);
        if (vendor == null || vendor.isDeleted()) { throw new VendorNotFoundException(vendorId.toString()); }
        return vendor;
    }
	
	 @Transactional
	 @Override
	 public CommandProcessingResult deleteVendorOtherDetails(final Long vendorId) {

		 final VendorOtherDetails vendor = this.vendorOtherDetailsRepository.findOne(vendorId);
	     if (vendor == null || vendor.isDeleted()) { 
	    	 throw new VendorNotFoundException(vendorId.toString());
	     }
	     vendor.delete();
	     this.vendorOtherDetailsRepository.save(vendor);

	     return new CommandProcessingResultBuilder().withEntityId(vendorId).build();
	  }

}

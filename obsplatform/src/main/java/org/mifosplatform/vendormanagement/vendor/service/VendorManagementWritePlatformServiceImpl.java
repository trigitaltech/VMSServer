package org.mifosplatform.vendormanagement.vendor.service;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.mifosplatform.commands.domain.CommandWrapper;
import org.mifosplatform.commands.service.CommandWrapperBuilder;
import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.configuration.domain.Configuration;
import org.mifosplatform.infrastructure.configuration.domain.ConfigurationConstants;
import org.mifosplatform.infrastructure.configuration.domain.ConfigurationRepository;
import org.mifosplatform.infrastructure.configuration.exception.ConfigurationPropertyNotFoundException;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.vendoragreement.exception.VendorNotFoundException;
import org.mifosplatform.vendormanagement.vendor.data.VendorManagementData;
import org.mifosplatform.vendormanagement.vendor.domain.VendorBankDetails;
import org.mifosplatform.vendormanagement.vendor.domain.VendorBankDetailsRepository;
import org.mifosplatform.vendormanagement.vendor.domain.VendorManagement;
import org.mifosplatform.vendormanagement.vendor.domain.VendorManagementRepository;
import org.mifosplatform.vendormanagement.vendor.serialization.VendorManagementCommandFromApiJsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	
@Service
public class VendorManagementWritePlatformServiceImpl implements VendorManagementWritePlatformService{
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(VendorManagementWritePlatformServiceImpl.class);	
	
	private final PlatformSecurityContext context;
	private final VendorManagementRepository vendormanagementRepository; 
	private final VendorManagementCommandFromApiJsonDeserializer fromApiJsonDeserializer;
	private final VendorBankDetailsRepository vendorBankDetailsRepository;
	private final VendorManagementReadPlatformService vendorManagementReadPlatformService;
	private final ConfigurationRepository configurationRepository;
	private final PortfolioCommandSourceWritePlatformService  portfolioCommandSourceWritePlatformService;
	 
	@Autowired
	public VendorManagementWritePlatformServiceImpl(final PlatformSecurityContext context, 
			final VendorManagementRepository vendormanagementRepository, 
			final VendorManagementCommandFromApiJsonDeserializer fromApiJsonDeserializer,
			final VendorBankDetailsRepository vendorBankDetailsRepository,
			final VendorManagementReadPlatformService vendorManagementReadPlatformService,
			final ConfigurationRepository configurationRepository,
			final PortfolioCommandSourceWritePlatformService  portfolioCommandSourceWritePlatformService) {
		
		this.context = context;
		this.vendormanagementRepository = vendormanagementRepository;
		this.fromApiJsonDeserializer = fromApiJsonDeserializer;
		this.vendorBankDetailsRepository = vendorBankDetailsRepository;
		this.vendorManagementReadPlatformService = vendorManagementReadPlatformService;
		this.configurationRepository = configurationRepository;
		this.portfolioCommandSourceWritePlatformService = portfolioCommandSourceWritePlatformService;
	}
	
	@Transactional
	@Override
	public CommandProcessingResult createVendorManagement(JsonCommand command) {
		
		try{
			
			this.context.authenticatedUser();
			
			Configuration configuration=this.configurationRepository.findOneByName(ConfigurationConstants.CONFIG_IS_SELFCAREUSER);
			
			if(configuration == null){
            	throw new ConfigurationPropertyNotFoundException(ConfigurationConstants.CONFIG_IS_SELFCAREUSER);
            }
			
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			
			final String bankName = command.stringValueOfParameterNamed("bankName");
			final String accountNo = command.stringValueOfParameterNamed("accountNo");
			final String branch = command.stringValueOfParameterNamed("branch");
			final String ifscCode = command.stringValueOfParameterNamed("ifscCode");
			final String swiftCode = command.stringValueOfParameterNamed("swiftCode");
			final String ibanCode = command.stringValueOfParameterNamed("ibanCode");
			final String accountName = command.stringValueOfParameterNamed("accountName");
			final String chequeNo = command.stringValueOfParameterNamed("chequeNo");
			
			final VendorManagement vendor = VendorManagement.fromJson(command);
			
			VendorBankDetails vendorBankDetails = new VendorBankDetails(bankName,accountNo,branch,ifscCode,swiftCode,ibanCode,accountName,chequeNo);
			
			vendor.addVendorBankDetails(vendorBankDetails);
			
			this.vendormanagementRepository.save(vendor);

			if (configuration.isEnabled()) {

				final JSONObject selfcarecreation = new JSONObject();
				selfcarecreation.put("userName", vendor.getVendorName());
				selfcarecreation.put("uniqueReference", vendor.getEmailId());
				selfcarecreation.put("clientId", vendor.getId());
				//selfcarecreation.put("device", command.stringValueOfParameterNamed("device"));
				selfcarecreation.put("mailNotification", true);
				selfcarecreation.put("password", vendor.getPassword());

				final CommandWrapper selfcareCommandRequest = new CommandWrapperBuilder().createSelfCare().withJson(selfcarecreation.toString()).build();
				this.portfolioCommandSourceWritePlatformService.logCommandSource(selfcareCommandRequest);
			}
			
			return new CommandProcessingResult(vendor.getId());
		} catch (DataIntegrityViolationException dve) {
			 handleCodeDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return CommandProcessingResult.empty();
	}
	

	private void handleCodeDataIntegrityIssues(JsonCommand command,
			DataIntegrityViolationException dve) {

		final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("vendor_name_UNIQUE")) {
			final String vendorName = command.stringValueOfParameterNamed("vendorName");
			throw new PlatformDataIntegrityException("error.msg.vendor.name.duplicate", "A name with this '" + vendorName + "' already exists");
		} 

		LOGGER.error(dve.getMessage(), dve);
		throw new PlatformDataIntegrityException("error.msg.cund.unknown.data.integrity.issue",
				"Unknown data integrity issue with resource: " + realCause.getMessage());

	}

	@Transactional
	@Override
	public CommandProcessingResult updateVendorManagement(Long vendorId, JsonCommand command) {
		try{
		this.context.authenticatedUser();
		this.fromApiJsonDeserializer.validateForCreate(command.json());
		VendorManagement vendor = retrieveCodeBy(vendorId);
		
		final VendorManagementData vendorDetails = this.vendorManagementReadPlatformService.retrieveSigleVendorManagement(vendorId);
		
		final Long detailId  = vendorDetails.getDetailId();
		
		VendorBankDetails vendorBankDetails = retrieveCodeByVendorId(detailId);
		
		final Map<String, Object> changes = vendor.update(command);
		
		final Map<String, Object> detailChanges = vendorBankDetails.update(command);
		
		
		if(!detailChanges.isEmpty()){
			this.vendorBankDetailsRepository.saveAndFlush(vendorBankDetails);
		}
		if(!changes.isEmpty()){
			this.vendormanagementRepository.saveAndFlush(vendor);
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
	
	/*@Transactional
	@Override
	public CommandProcessingResult updateVendorManagement(Long vendorId, JsonCommand command) {
		try{
		this.context.authenticatedUser();
		this.fromApiJsonDeserializer.validateForCreate(command.json());
		VendorManagement vendor = retrieveCodeBy(vendorId);
		
		final VendorManagementData vendorDetails = this.vendorManagementReadPlatformService.retrieveSigleVendorManagement(vendorId);
		
		VendorBankDetails abc = new VendorBankDetails();
		final Map<String, Object> changes = vendor.update(command);
		
		final Map<String, Object> changess = abc.update(command);
		
		final String bankName = vendorDetails.getBankName();
		final String accountNo = vendorDetails.getAccountNo();
		final String branch = vendorDetails.getBranch();
		final String ifscCode = vendorDetails.getIfscCode();
		final String swiftCode = vendorDetails.getSwiftCode();
		final String ibanCode = vendorDetails.getIbanCode();
		final String accountName = vendorDetails.getAccountName();
		final String chequeNo = vendorDetails.getChequeNo();
		
		
		VendorBankDetails vendorBankDetails = new VendorBankDetails(bankName,accountNo,branch,ifscCode,swiftCode,ibanCode,accountName,chequeNo);
		
		vendor.addVendorBankDetails(vendorBankDetails);
		
		this.vendorBankDetailsRepository.saveAndFlush(vendorBankDetails);
		if(!changes.isEmpty()){
			this.vendormanagementRepository.saveAndFlush(vendor);
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
	}*/
	
	private VendorManagement retrieveCodeBy(final Long vendorId) {
        final VendorManagement vendor = this.vendormanagementRepository.findOne(vendorId);
        if (vendor == null || vendor.isDeleted()) { throw new VendorNotFoundException(vendorId.toString()); }
        return vendor;
    }
	
	private VendorBankDetails retrieveCodeByVendorId(final Long detailId) {
		
		final VendorBankDetails vendorBankDetails = this.vendorBankDetailsRepository.findOne(detailId);
        if (vendorBankDetails == null || vendorBankDetails.isDeleted()) { throw new VendorNotFoundException(detailId); }
        return vendorBankDetails;
    }
	
	 @Transactional
	 @Override
	 public CommandProcessingResult deleteVendorManagement(final Long vendorId) {

		 final VendorManagement vendor = this.vendormanagementRepository.findOne(vendorId);
	     if (vendor == null || vendor.isDeleted()) { 
	    	 throw new VendorNotFoundException(vendorId.toString());
	     }
	     
	     final VendorManagementData vendorDetails = this.vendorManagementReadPlatformService.retrieveSigleVendorManagement(vendorId);
			
		 final Long detailId  = vendorDetails.getDetailId();
		  
	     final VendorBankDetails vendorDetailss = this.vendorBankDetailsRepository.findOne(detailId);
	     
	     vendor.delete();
	     vendorDetailss.delete();
	     
	     this.vendormanagementRepository.save(vendor);

	     return new CommandProcessingResultBuilder().withEntityId(vendorId).build();
	  }
}

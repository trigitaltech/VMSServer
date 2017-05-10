package org.mifosplatform.organisation.monetary.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.mifosplatform.infrastructure.codes.exception.CodeNotFoundException;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.monetary.domain.ApplicationCurrency;
import org.mifosplatform.organisation.monetary.domain.ApplicationCurrencyRepository;

import org.mifosplatform.organisation.monetary.serialization.CurrencyCommandFromApiJsonDeserializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationCurrencyWritePlatformServiceImpl implements ApplicationCurrencyWritePlatformService {
	private final PlatformSecurityContext context;
	private final ApplicationCurrencyRepository saveRepository;
	private final CurrencyCommandFromApiJsonDeserializer fromApiJsonDeserializer;
	
	
	@Autowired
	public ApplicationCurrencyWritePlatformServiceImpl(
			final PlatformSecurityContext context,
			final CurrencyCommandFromApiJsonDeserializer fromApiJsonDeserializer,
			final ApplicationCurrencyRepository applicationCurrencyRepository) {
		this.context = context;
		this.fromApiJsonDeserializer = fromApiJsonDeserializer;
		this.saveRepository = applicationCurrencyRepository;
		
	}
	@Transactional
	@Override
	public CommandProcessingResult createApplicationCurrency(final JsonCommand command) {

		context.authenticatedUser();
		

		this.fromApiJsonDeserializer.validateForCreate(command.json());
		

		ApplicationCurrency currency =  ApplicationCurrency.fromJson(command);

		//this.applicationCurrencyRepository.deleteAll();
	
		this.saveRepository.save(currency);
		
		
		return new CommandProcessingResult(currency.getId());


		/*return new CommandProcessingResultBuilder() //
				.withCommandId(command.commandId()) //
				.build();*/
	}
	
	private void handleCodeDataIntegrityIssues(final JsonCommand command,final DataIntegrityViolationException dve) {

		final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("applicationcurrency")) {
			final String code = command.stringValueOfParameterNamed("code");
			throw new PlatformDataIntegrityException("error.msg.contract.code.duplicate.name","A name with code '" + code + "' already exists", code);
		} 
     throw new PlatformDataIntegrityException("error.msg.cund.unknown.data.integrity.issue","Unknown data integrity issue with resource: "+ realCause.getMessage());

	}
	
	@Transactional
	@Override
	public CommandProcessingResult updateApplicationCurrency(Long id,final JsonCommand command) {

		try {
			this.fromApiJsonDeserializer.validateForUpateCurrency(command.json());
			final ApplicationCurrency  applicationcurrency = retrieveCodeBy(id);
			final Map<String, Object> changes = applicationcurrency.update(command);
			if (!changes.isEmpty()) {
				this.saveRepository.saveAndFlush(applicationcurrency);
			}
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(id).with(changes).build();

		} catch (DataIntegrityViolationException dve) {
			handleCodeDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}

	}

	private ApplicationCurrency retrieveCodeBy(final Long id) {
		final ApplicationCurrency applicationcurrency = this.saveRepository.findOne(id);
		if (applicationcurrency == null) {
			throw new CodeNotFoundException(id.toString());
		}
		return applicationcurrency;
	}
	
}

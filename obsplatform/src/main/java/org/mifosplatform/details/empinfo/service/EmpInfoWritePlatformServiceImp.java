package org.mifosplatform.details.empinfo.service;




import org.mifosplatform.infrastructure.codes.exception.CodeNotFoundException;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;

import org.mifosplatform.details.empinfo.domain.EmpInfo;
import org.mifosplatform.details.empinfo.serialization.EmpInfoCommandFromApiJsonDeserializer;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.mifosplatform.details.empinfo.domain.EmpInfoRepository;


@Service
public class EmpInfoWritePlatformServiceImp implements EmpInfoWritePlatformService {
	private final EmpInfoRepository saveRepository;
	private final EmpInfoCommandFromApiJsonDeserializer fromApiJsonDeserializer;
	@Autowired
	public EmpInfoWritePlatformServiceImp(final EmpInfoRepository saveRepository, EmpInfoCommandFromApiJsonDeserializer fromApiJsonDeserializer) 
	{
		this.saveRepository = saveRepository;
		this.fromApiJsonDeserializer = fromApiJsonDeserializer;
	}
	@Override
	public CommandProcessingResult createEmpInfo(final JsonCommand command) {
		try {
		
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			final EmpInfo empinfo = EmpInfo.fromJson(command);
			this.saveRepository.save(empinfo);
			
			return new CommandProcessingResult(empinfo.getId());

		} catch (DataIntegrityViolationException dve) {
			handleCodeDataIntegrityIssues(command, dve);
			return CommandProcessingResult.empty();
		}
	}


	private void handleCodeDataIntegrityIssues(final JsonCommand command,final DataIntegrityViolationException dve) {

		final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("EmpInfo")) {
			final String name = command.stringValueOfParameterNamed("name");
			throw new PlatformDataIntegrityException("error.msg.contract.code.duplicate.name","A code with name '" + name + "' already exists", name);
		} 
     throw new PlatformDataIntegrityException("error.msg.cund.unknown.data.integrity.issue","Unknown data integrity issue with resource: "+ realCause.getMessage());

	}
	@Override
	public CommandProcessingResult updateEmpInfo(final Long empinfoId,final JsonCommand command) {
		
		try {
			this.fromApiJsonDeserializer.validateForUpdate(command.json());
			final EmpInfo empinfo = retrieveCodeBy(empinfoId);
			final Map<String, Object> changes = empinfo.update(command);
			if (!changes.isEmpty()) {
				this.saveRepository.saveAndFlush(empinfo);
			}
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(empinfoId).with(changes).build();

		} catch (DataIntegrityViolationException dve) {
			handleCodeDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}

	}

	private EmpInfo retrieveCodeBy(final Long empinfoId) {
		final EmpInfo empinfo = this.saveRepository.findOne(empinfoId);
		if (empinfo == null) {
			throw new CodeNotFoundException(empinfoId.toString());
		}
		return empinfo;
	}

}

package org.mifosplatform.details.empinfo.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface EmpInfoWritePlatformService {

	CommandProcessingResult createEmpInfo(JsonCommand command);
	
	CommandProcessingResult updateEmpInfo(Long empinfoId,JsonCommand command);

}

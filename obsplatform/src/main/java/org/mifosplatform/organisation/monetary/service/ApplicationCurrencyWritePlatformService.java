package org.mifosplatform.organisation.monetary.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface ApplicationCurrencyWritePlatformService {
	
    CommandProcessingResult createApplicationCurrency(JsonCommand command);
	
	CommandProcessingResult updateApplicationCurrency(Long id,JsonCommand command);

}



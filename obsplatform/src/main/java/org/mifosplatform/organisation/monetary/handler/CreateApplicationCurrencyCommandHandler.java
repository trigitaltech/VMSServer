package org.mifosplatform.organisation.monetary.handler;

import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.organisation.monetary.service.ApplicationCurrencyWritePlatformService;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateApplicationCurrencyCommandHandler implements NewCommandSourceHandler{
	private final ApplicationCurrencyWritePlatformService writePlatformService;
	@Autowired
    public CreateApplicationCurrencyCommandHandler(final ApplicationCurrencyWritePlatformService  writePlatformService) {
        this.writePlatformService = writePlatformService;
    }
	@Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

		System.out.println("DEBUG11: Before createApplicationCurrency ");
		return this.writePlatformService.createApplicationCurrency(command);
    }

}

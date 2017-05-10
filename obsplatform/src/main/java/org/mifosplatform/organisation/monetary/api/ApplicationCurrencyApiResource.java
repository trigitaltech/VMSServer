package org.mifosplatform.organisation.monetary.api;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.commands.domain.CommandWrapper;
import org.mifosplatform.commands.service.CommandWrapperBuilder;
import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
//import org.mifosplatform.organisation.monetary.data.ApplicationCurrencyConfigurationData;
import org.mifosplatform.organisation.monetary.data.CurrencyData;
import org.mifosplatform.organisation.monetary.service.CurrencyReadPlatformService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/currency")
@Component
@Scope("singleton")
public class ApplicationCurrencyApiResource {
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<String>(
			Arrays.asList("id","code","name","decimal_places","internationalized_name_code","display_symbol","ResourceId","Type"));
	
	        private final String resourceNameForPermissions = "MCURRENCY";
	        
	        private final PlatformSecurityContext context;
	     	private final CurrencyReadPlatformService readPlatformService;
	     	private final DefaultToApiJsonSerializer<CurrencyData> toApiJsonSerializer;
	     	private final ApiRequestParameterHelper apiRequestParameterHelper;
	     	private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
	     	@Autowired
	    	public ApplicationCurrencyApiResource(
	    			final PlatformSecurityContext context,
	    			final CurrencyReadPlatformService readPlatformService,
	    			final DefaultToApiJsonSerializer<CurrencyData> toApiJsonSerializer,
	    			final ApiRequestParameterHelper apiRequestParameterHelper,
	    			final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
	    		this.context = context;
	    		this.readPlatformService = readPlatformService;
	    		this.toApiJsonSerializer = toApiJsonSerializer;
	    		this.apiRequestParameterHelper = apiRequestParameterHelper;
	    		this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
	    	}
	     	
	     	@GET
	    	@Consumes({ MediaType.APPLICATION_JSON })
	    	@Produces({ MediaType.APPLICATION_JSON })
	    	public String retrieveCurrency(@Context final UriInfo uriInfo) {

	    		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
	    		final Collection<CurrencyData> configurationData = this.readPlatformService.retrieveAllPlatformCurrencies();
	    		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	    		return this.toApiJsonSerializer.serialize(settings, configurationData, RESPONSE_DATA_PARAMETERS);
	    	}
	     	@POST
	    	@Consumes({ MediaType.APPLICATION_JSON })
	    	@Produces({ MediaType.APPLICATION_JSON })
	    	public String createCurrency(final String apiRequestBodyAsJson){
	     		System.out.println("DEBUG: Json input" + apiRequestBodyAsJson);
	    		final CommandWrapper commandRequest =new CommandWrapperBuilder().createApplicationCurrency().withJson(apiRequestBodyAsJson).build();
	    		System.out.println("DEBUG: Json input command" + commandRequest.toString());
	    		final CommandProcessingResult result =this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
	    		return this.toApiJsonSerializer.serialize(result);
	    	}
	     	
	     	@PUT
	     	@Path("{id}")
	    	@Consumes({ MediaType.APPLICATION_JSON })
	    	@Produces({ MediaType.APPLICATION_JSON })
	    	public String updateCurrency(@PathParam("id") final long id, final String apiRequestBodyAsJson) {
	    		
	    		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateApplicationCurrency(id).withJson(apiRequestBodyAsJson).build();
	    		final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
	    		return this.toApiJsonSerializer.serialize(result);
	    	}
}

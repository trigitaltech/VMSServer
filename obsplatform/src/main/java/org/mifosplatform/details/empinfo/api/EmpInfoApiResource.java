package org.mifosplatform.details.empinfo.api;



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
import org.mifosplatform.details.empinfo.data.EmpData;
import org.mifosplatform.details.empinfo.service.EmpInfoReadPlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/employee")
@Component
@Scope("singleton")
public class EmpInfoApiResource {
	
	private  final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id",
	           "name","dob","sal","is_deleted"));
	
	 private final DefaultToApiJsonSerializer<EmpData> toApiJsonSerializer;
	 private final ApiRequestParameterHelper apiRequestParameterHelper;
	 private final EmpInfoReadPlatformService empInfoReadPlatformService;
     private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
	    
	    
	    @Autowired
	    public EmpInfoApiResource(final DefaultToApiJsonSerializer<EmpData> toApiJsonSerializer,
	    		final ApiRequestParameterHelper apiRequestParameterHelper,
	    		final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
	    		final EmpInfoReadPlatformService empInfoReadPlatformService) 
	    	{
		        this.toApiJsonSerializer = toApiJsonSerializer;
		        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
		        this.apiRequestParameterHelper=apiRequestParameterHelper;
		        this.empInfoReadPlatformService=empInfoReadPlatformService;
		    }		
		
	
    /**
	 * using this method for Posting employee details
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String createNewEmpInfo(final String apiRequestBodyAsJson) {
		
		  final CommandWrapper commandRequest = new CommandWrapperBuilder().createEmpInfo().withJson(apiRequestBodyAsJson).build();
	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
	        return this.toApiJsonSerializer.serialize(result);
	}
	/**
	 * Using this method getting one contract list using by contractId
	 */
	@GET
	@Path("{empinfoId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveOneEmpInfoDetails(@PathParam("empinfoId") final Long empinfoId, @Context final UriInfo uriInfo) 
	{
		EmpData empData = this.empInfoReadPlatformService.retrieveEmpData(empinfoId);
		 final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	        return this.toApiJsonSerializer.serialize(settings,empData, RESPONSE_DATA_PARAMETERS);
	}
	/**
	 * Using this method getting all employee list
	 */
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveAllEmpInfo(@Context final UriInfo uriInfo) {
		final Collection<EmpData> empData=this.empInfoReadPlatformService.retrieveAllEmp();
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings,empData,RESPONSE_DATA_PARAMETERS);
	}
	@PUT
	@Path("{empinfoId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String updateEmployee(@PathParam("empinfoId") final Long empinfoId, final String apiRequestBodyAsJson){

		
		 final CommandWrapper commandRequest = new CommandWrapperBuilder().updateEmpInfo(empinfoId).withJson(apiRequestBodyAsJson).build();
		 final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
		 return this.toApiJsonSerializer.serialize(result);
	}
}

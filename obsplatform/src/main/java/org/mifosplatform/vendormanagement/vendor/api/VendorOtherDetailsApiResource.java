/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.mifosplatform.useradministration.data.AppUserData;
import org.mifosplatform.vendormanagement.vendor.data.VendorOtherDetailsData;
import org.mifosplatform.vendormanagement.vendor.service.VendorOtherDetailsReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/vendorotherdetails")
@Component
@Scope("singleton")
public class VendorOtherDetailsApiResource {

    /**
     * The set of parameters that are supported in response for
     * {@link AppUserData}.
     */
    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id"));

    private static final String RESOURCENAMEFORPERMISSIONS = "VENDOROTHERDETAILS";
    private final PlatformSecurityContext context;
    private final VendorOtherDetailsReadPlatformService vendorOtherDetailsReadPlatformService;
    private final DefaultToApiJsonSerializer<VendorOtherDetailsData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;

    @Autowired
    public VendorOtherDetailsApiResource(final PlatformSecurityContext context, final VendorOtherDetailsReadPlatformService vendorOtherDetailsReadPlatformService,
    		final DefaultToApiJsonSerializer<VendorOtherDetailsData> toApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
    	
        this.context = context;
        this.vendorOtherDetailsReadPlatformService = vendorOtherDetailsReadPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
    }
    
    
 /*   @GET
    @Path("template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String vendorBankDetailsTemplateDetails(@Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);
        VendorOtherDetailsData vendor = handleTemplateData();
        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    private VendorOtherDetailsData handleTemplateData() {
		
        final List<CountryDetails> countryData = this.addressReadPlatformService.retrieveCountries();
        final Collection<CurrencyData> currencyOptions = this.currencyReadPlatformService.retrieveAllPlatformCurrencies();
		return new VendorOtherDetailsData(countryData, currencyOptions);
			
	}*/
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createVendorOtherDetails(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().createVendorOtherDetails().withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveVendorOtherDetails(@Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);

        final List<VendorOtherDetailsData> vendorOtherDetails = this.vendorOtherDetailsReadPlatformService.retrieveAllVendorOtherDetails();

        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, vendorOtherDetails, RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveSingleVendorOtherDetails(@PathParam("vendorId") final Long vendorId, @Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);

        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        VendorOtherDetailsData vendor = this.vendorOtherDetailsReadPlatformService.retrieveSigleVendorOtherDetails(vendorId);
        
        
        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    /*@PUT
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateVendorOtherDetails(@PathParam("vendorId") final Long vendorId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().updateVendorManagement(vendorId).withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @DELETE
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteVendorOtherDetails(@PathParam("vendorId") final Long vendorId) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().deleteVendorManagement(vendorId).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }*/

}

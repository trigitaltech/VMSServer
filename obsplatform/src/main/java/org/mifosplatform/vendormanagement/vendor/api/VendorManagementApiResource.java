/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.vendormanagement.vendor.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.mifosplatform.organisation.address.service.AddressReadPlatformService;
import org.mifosplatform.organisation.mcodevalues.api.CodeNameConstants;
import org.mifosplatform.organisation.mcodevalues.data.MCodeData;
import org.mifosplatform.organisation.mcodevalues.service.MCodeReadPlatformService;
import org.mifosplatform.organisation.monetary.service.CurrencyReadPlatformService;
import org.mifosplatform.useradministration.data.AppUserData;
import org.mifosplatform.vendoragreement.exception.VendorNotFoundException;
import org.mifosplatform.vendormanagement.vendor.data.VendorManagementData;
import org.mifosplatform.vendormanagement.vendor.service.VendorManagementReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/vendormanagement")
@Component
@Scope("singleton")
public class VendorManagementApiResource {

    /**
     * The set of parameters that are supported in response for
     * {@link AppUserData}.
     */
    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id"));

    private static final String RESOURCENAMEFORPERMISSIONS = "VENDORMANAGEMENT";
    private final PlatformSecurityContext context;
    private final VendorManagementReadPlatformService readPlatformService;
    private final DefaultToApiJsonSerializer<VendorManagementData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final AddressReadPlatformService addressReadPlatformService;
    private final CurrencyReadPlatformService currencyReadPlatformService;
    private final MCodeReadPlatformService codeReadPlatformService;

    @Autowired
    public VendorManagementApiResource(final PlatformSecurityContext context, final VendorManagementReadPlatformService readPlatformService,
    		final DefaultToApiJsonSerializer<VendorManagementData> toApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final AddressReadPlatformService addressReadPlatformService,
            final CurrencyReadPlatformService currencyReadPlatformService,
            final MCodeReadPlatformService codeReadPlatformService) {
    	
        this.context = context;
        this.readPlatformService = readPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.addressReadPlatformService = addressReadPlatformService;
        this.currencyReadPlatformService = currencyReadPlatformService;
        this.codeReadPlatformService =codeReadPlatformService;
    }
    
    
    @GET
    @Path("template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String vendorManagementTemplateDetails(@Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);
        VendorManagementData vendor = handleTemplateData();
        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    private VendorManagementData handleTemplateData() {
		
    	final List<String> countryData = this.addressReadPlatformService.retrieveCountryDetails();
        final List<String> statesData = this.addressReadPlatformService.retrieveStateDetails();
        final List<String> citiesData = this.addressReadPlatformService.retrieveCityDetails();
        final Collection<MCodeData> entityTypeData = this.codeReadPlatformService.getCodeValue(CodeNameConstants.VENDOR_ENTITY_TYPES);
        final Collection<MCodeData> residentialStatusData = this.codeReadPlatformService.getCodeValue(CodeNameConstants.VENDOR_RESIDENTIAL_STATUS);

		return new VendorManagementData(countryData,statesData,citiesData,entityTypeData,residentialStatusData);
			
	}
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createVendor(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().createVendorManagement().withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveVendors(@Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);

        final List<VendorManagementData> vendor = this.readPlatformService.retrieveAllVendorManagements();

        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveSingleVendor(@PathParam("vendorId") final Long vendorId, @Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);

        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        VendorManagementData vendor = this.readPlatformService.retrieveSigleVendorManagement(vendorId);
        if(vendor == null){
        	throw new VendorNotFoundException(vendorId.toString());
        }
        
        if (settings.isTemplate()) {
        	
        	final List<String> countryData = this.addressReadPlatformService.retrieveCountryDetails();
            final List<String> statesData = this.addressReadPlatformService.retrieveStateDetails();
            final List<String> citiesData = this.addressReadPlatformService.retrieveCityDetails();
            final Collection<MCodeData> entityTypeData = this.codeReadPlatformService.getCodeValue(CodeNameConstants.VENDOR_ENTITY_TYPES);
            final Collection<MCodeData> residentialStatusData = this.codeReadPlatformService.getCodeValue(CodeNameConstants.VENDOR_RESIDENTIAL_STATUS);
            vendor.setCountryData(countryData);
            vendor.setStatesData(statesData);
            vendor.setCitiesData(citiesData);
            vendor.setEntityTypeData(entityTypeData);
            vendor.setResidentialStatusData(residentialStatusData);
            
        }

        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    @PUT
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateVendor(@PathParam("vendorId") final Long vendorId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().updateVendorManagement(vendorId).withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @DELETE
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteVendor(@PathParam("vendorId") final Long vendorId) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().deleteVendorManagement(vendorId).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }

}

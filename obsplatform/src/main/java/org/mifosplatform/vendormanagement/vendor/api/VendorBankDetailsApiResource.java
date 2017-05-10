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
import org.mifosplatform.organisation.address.data.CountryDetails;
import org.mifosplatform.organisation.address.service.AddressReadPlatformService;
import org.mifosplatform.organisation.monetary.data.CurrencyData;
import org.mifosplatform.organisation.monetary.service.CurrencyReadPlatformService;
import org.mifosplatform.useradministration.data.AppUserData;
import org.mifosplatform.vendoragreement.exception.VendorNotFoundException;
import org.mifosplatform.vendormanagement.vendor.data.VendorBankDetailsData;
import org.mifosplatform.vendormanagement.vendor.service.VendorBankDetailsReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/vendorbankdetails")
@Component
@Scope("singleton")
public class VendorBankDetailsApiResource {

    /**
     * The set of parameters that are supported in response for
     * {@link AppUserData}.
     */
    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id"));

    private static final String RESOURCENAMEFORPERMISSIONS = "VENDORBANKDETAILS";
    private final PlatformSecurityContext context;
    private final VendorBankDetailsReadPlatformService vendorBankDetailsReadPlatformService;
    private final DefaultToApiJsonSerializer<VendorBankDetailsData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final AddressReadPlatformService addressReadPlatformService;
    private final CurrencyReadPlatformService currencyReadPlatformService;

    @Autowired
    public VendorBankDetailsApiResource(final PlatformSecurityContext context, final VendorBankDetailsReadPlatformService vendorBankDetailsReadPlatformService,
    		final DefaultToApiJsonSerializer<VendorBankDetailsData> toApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final AddressReadPlatformService addressReadPlatformService,
            final CurrencyReadPlatformService currencyReadPlatformService) {
    	
        this.context = context;
        this.vendorBankDetailsReadPlatformService = vendorBankDetailsReadPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.addressReadPlatformService = addressReadPlatformService;
        this.currencyReadPlatformService = currencyReadPlatformService;
    }
    
    
    @GET
    @Path("template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String vendorBankDetailsTemplateDetails(@Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);
        VendorBankDetailsData vendor = handleTemplateData();
        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    private VendorBankDetailsData handleTemplateData() {
		
        final List<CountryDetails> countryData = this.addressReadPlatformService.retrieveCountries();
        final Collection<CurrencyData> currencyOptions = this.currencyReadPlatformService.retrieveAllPlatformCurrencies();
		return new VendorBankDetailsData(countryData, currencyOptions);
			
	}
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createVendorBankDetails(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().createVendorBankDetails().withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveVendorBankDetails(@Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);

        final List<VendorBankDetailsData> vendorBankDetails = this.vendorBankDetailsReadPlatformService.retrieveAllVendorbankDetails();

        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, vendorBankDetails, RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveSingleVendorBankDetails(@PathParam("vendorId") final Long vendorId, @Context final UriInfo uriInfo) {

        context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);

        final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        VendorBankDetailsData vendor = this.vendorBankDetailsReadPlatformService.retrieveSigleVendorBankDetails(vendorId);
        if(vendor == null){
        	throw new VendorNotFoundException(vendorId.toString());
        }
        
        if (settings.isTemplate()) {
        	final List<CountryDetails> countryData = this.addressReadPlatformService.retrieveCountries();
            final Collection<CurrencyData> currencyOptions = this.currencyReadPlatformService.retrieveAllPlatformCurrencies();
            //vendor.setCountryData(countryData);
            //vendor.setCurrencyOptions(currencyOptions);
        }

        return this.toApiJsonSerializer.serialize(settings, vendor, RESPONSE_DATA_PARAMETERS);
    }
    
    /*@PUT
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateVendorBankDetails(@PathParam("vendorId") final Long vendorId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().updateVendorManagement(vendorId).withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @DELETE
    @Path("{vendorId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteVendorBankDetails(@PathParam("vendorId") final Long vendorId) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().deleteVendorManagement(vendorId).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }*/

}

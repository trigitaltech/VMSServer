package org.mifosplatform.vendormanagement.vendor.serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.InvalidJsonException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Deserializer for code JSON to validate API request.
 */
@Component
public final class VendorManagementCommandFromApiJsonDeserializer {

    /**
     * The parameters supported for this command.
     */
    private final Set<String> supportedParameters = new HashSet<String>(Arrays.asList("id", "vendorName", "entityType",
    		  "otherEntity", "contactName", "address1", "address2", "address3", "country", "state","city","postalCode","residentialStatus",
    		  "otherResidential","landLineNo","mobileNo","fax","emailId","bankName","accountNo",
    		  "branch","ifscCode","swiftCode","ibanCode","accountName","chequeNo","dateFormat", "locale"));
    
    
    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public VendorManagementCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("vendormanagement");

        final JsonElement element = fromApiJsonHelper.parse(json);

        final String vendorName = fromApiJsonHelper.extractStringNamed("vendorName", element);
        baseDataValidator.reset().parameter("vendorName").value(vendorName).notBlank().notExceedingLengthOf(256);
        
        final Long entityType = fromApiJsonHelper.extractLongNamed("entityType", element);
        baseDataValidator.reset().parameter("entityType").value(entityType).notBlank().notExceedingLengthOf(30);
        
        final String otherEntity = fromApiJsonHelper.extractStringNamed("otherEntity", element);
        baseDataValidator.reset().parameter("otherEntity").value(otherEntity).notExceedingLengthOf(60);
        
        final String contactName = fromApiJsonHelper.extractStringNamed("contactName", element);
        baseDataValidator.reset().parameter("contactName").value(contactName).notBlank().notExceedingLengthOf(256);
        
        final String address1 = fromApiJsonHelper.extractStringNamed("address1", element);
        baseDataValidator.reset().parameter("address1").value(address1).notBlank().notExceedingLengthOf(256);
        
        final String address2 = fromApiJsonHelper.extractStringNamed("address2", element);
        baseDataValidator.reset().parameter("address2").value(address2).notExceedingLengthOf(256);
        
        final String address3 = fromApiJsonHelper.extractStringNamed("address3", element);
        baseDataValidator.reset().parameter("address3").value(address3).notExceedingLengthOf(256);
        
        final String country = fromApiJsonHelper.extractStringNamed("country", element);
        baseDataValidator.reset().parameter("country").value(country).notBlank().notExceedingLengthOf(60);
        
        final String state = fromApiJsonHelper.extractStringNamed("state", element);
        baseDataValidator.reset().parameter("state").value(state).notBlank().notExceedingLengthOf(60);
        
        final String city = fromApiJsonHelper.extractStringNamed("city", element);
        baseDataValidator.reset().parameter("city").value(city).notBlank().notExceedingLengthOf(60);
        
        final String postalCode = fromApiJsonHelper.extractStringNamed("postalCode", element);
        baseDataValidator.reset().parameter("postalCode").value(postalCode).notBlank().notExceedingLengthOf(10);
        
        final Long residentialStatus = fromApiJsonHelper.extractLongNamed("residentialStatus", element);
        baseDataValidator.reset().parameter("residentialStatus").value(residentialStatus).notBlank().notExceedingLengthOf(30);
        
        final String otherResidential = fromApiJsonHelper.extractStringNamed("otherResidential", element);
        baseDataValidator.reset().parameter("otherResidential").value(otherResidential).notExceedingLengthOf(60);
        
        final String landLineNo = fromApiJsonHelper.extractStringNamed("landLineNo", element);
        baseDataValidator.reset().parameter("landLineNo").value(landLineNo).notExceedingLengthOf(15);
        
        final String mobileNo = fromApiJsonHelper.extractStringNamed("mobileNo", element);
        baseDataValidator.reset().parameter("mobileNo").value(mobileNo).notBlank().notExceedingLengthOf(12);
        
        final String fax = fromApiJsonHelper.extractStringNamed("fax", element);
        baseDataValidator.reset().parameter("fax").value(fax).notExceedingLengthOf(15);
        
        /*final String extVendorId = fromApiJsonHelper.extractStringNamed("extVendorId", element);
        baseDataValidator.reset().parameter("extVendorId").value(extVendorId).notBlank().notExceedingLengthOf(20);*/
        
        final String emailId = fromApiJsonHelper.extractStringNamed("emailId", element);
        baseDataValidator.reset().parameter("emailId").value(emailId).notBlank().notExceedingLengthOf(45);
        
        final String bankName = fromApiJsonHelper.extractStringNamed("bankName", element);
        baseDataValidator.reset().parameter("bankName").value(bankName).notBlank().notExceedingLengthOf(60);
        
        final String accountNo = fromApiJsonHelper.extractStringNamed("accountNo", element);
        baseDataValidator.reset().parameter("accountNo").value(accountNo).notBlank().notExceedingLengthOf(20);
        
        final String branch = fromApiJsonHelper.extractStringNamed("branch", element);
        baseDataValidator.reset().parameter("branch").value(branch).notBlank().notExceedingLengthOf(256);
        
        final String ifscCode = fromApiJsonHelper.extractStringNamed("ifscCode", element);
        baseDataValidator.reset().parameter("ifscCode").value(ifscCode).notBlank().notExceedingLengthOf(60);
        
        final String swiftCode = fromApiJsonHelper.extractStringNamed("swiftCode", element);
        baseDataValidator.reset().parameter("swiftCode").value(swiftCode).notExceedingLengthOf(60);
        
        final String ibanCode = fromApiJsonHelper.extractStringNamed("ibanCode", element);
        baseDataValidator.reset().parameter("ibanCode").value(ibanCode).notExceedingLengthOf(60);
        
        final String accountName = fromApiJsonHelper.extractStringNamed("accountName", element);
        baseDataValidator.reset().parameter("accountName").value(accountName).notBlank().notExceedingLengthOf(60);
        
        final String chequeNo = fromApiJsonHelper.extractStringNamed("chequeNo", element);
        baseDataValidator.reset().parameter("chequeNo").value(chequeNo).notExceedingLengthOf(60);
        
        throwExceptionIfValidationWarningsExist(dataValidationErrors);
        
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }

}
